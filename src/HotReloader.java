import javax.tools.ToolProvider;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.nio.file.*;
import java.util.concurrent.TimeUnit;

public class HotReloader {
    private static final String SOURCE_FILE = "TestModule.java";
    private static final String CLASS_NAME = "TestModule";
    private static final Path WORK_DIR = Paths.get(".").toAbsolutePath().normalize();

    public static void main(String[] args) throws Exception {
        System.out.println("Working dir: " + WORK_DIR);

        compileAndLoadAndPrint();

        WatchService watcher = FileSystems.getDefault().newWatchService();
        WORK_DIR.register(watcher, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_CREATE);

        System.out.println("Watching for changes to " + SOURCE_FILE + " ... (change file and save then)");

        while (true) {
            WatchKey key = watcher.take();
            boolean shouldProcess = false;
            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();
                Path changed = (Path) event.context();

                if (changed != null && changed.getFileName().toString().equals(SOURCE_FILE)) {
                    System.out.println("Detected " + kind + " on " + changed + " at " + System.currentTimeMillis());
                    shouldProcess = true;
                }
            }

            if (shouldProcess) {
                TimeUnit.MILLISECONDS.sleep(200);
                compileAndLoadAndPrint();
            }


            boolean valid = key.reset();
            if (!valid) {
                System.out.println("WatchKey no longer valid, exiting.");
                break;
            }
        }
    }

    private static void compileAndLoadAndPrint() {
        try {
            boolean ok = compileSource(SOURCE_FILE);
            if (!ok) {
                System.err.println("Compilation failed for " + SOURCE_FILE);
                return;
            }


            Path classFile = WORK_DIR.resolve(CLASS_NAME + ".class");
            int attempts = 0;
            while ((!Files.exists(classFile) || Files.size(classFile) == 0) && attempts < 10) {
                TimeUnit.MILLISECONDS.sleep(100);
                attempts++;
            }


            try (DynamicClassLoader loader = new DynamicClassLoader(WORK_DIR)) {
                Class<?> clazz = loader.findClass(CLASS_NAME);

                Constructor<?> ctor = clazz.getDeclaredConstructor();
                Object instance = ctor.newInstance();
                System.out.println("Loaded instance: " + instance.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean compileSource(String javaFileName) throws IOException, InterruptedException {
        System.out.println("Compiling " + javaFileName + " ...");
        var compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            System.err.println("No system Java compiler available. Run with JDK, not JRE.");
            return false;
        }
        int result = compiler.run(null, System.out, System.err, WORK_DIR.resolve(javaFileName).toString());
        if (result == 0) {
            System.out.println("Compilation successful.");
            return true;
        } else {
            System.err.println("Compilation returned non-zero status: " + result);
            return false;
        }
    }

    static class DynamicClassLoader extends ClassLoader implements AutoCloseable {
        private final Path baseDir;

        DynamicClassLoader(Path baseDir) {
            super(null);
            this.baseDir = baseDir;
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                Path classFile = baseDir.resolve(name + ".class");
                if (!Files.exists(classFile)) {
                    throw new ClassNotFoundException("Class file not found: " + classFile);
                }
                byte[] bytes;
                try (InputStream in = Files.newInputStream(classFile)) {
                    bytes = in.readAllBytes();
                }

                return defineClass(name, bytes, 0, bytes.length);
            } catch (IOException e) {
                throw new ClassNotFoundException("Failed to load class " + name, e);
            }
        }

        @Override
        public void close() {}
    }
}
