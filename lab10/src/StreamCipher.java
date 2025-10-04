import java.io.*;
import java.util.*;
import java.util.logging.*;

public class StreamCipher {
    private static final Logger logger = Logger.getLogger(StreamCipher.class.getName());
    private static ResourceBundle bundle;

    static {
        try {
            Logger rootLogger = Logger.getLogger("");
            for (Handler h : rootLogger.getHandlers()) {
                rootLogger.removeHandler(h);
            }

            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.INFO);

            FileHandler fileHandler = new FileHandler("streamcipher.log", true);
            fileHandler.setLevel(Level.WARNING);
            fileHandler.setFormatter(new SimpleFormatter());

            logger.addHandler(consoleHandler);
            logger.addHandler(fileHandler);

            logger.setLevel(Level.ALL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1 - Українська");
        System.out.println("2 - English");
        System.out.print("Pick a language: ");
        int choice = scanner.nextInt();

        Locale locale = switch (choice) {
            case 1 -> new Locale("uk");
            case 2 -> Locale.ENGLISH;
            default -> Locale.ENGLISH;
        };

        bundle = ResourceBundle.getBundle("location.messages", locale);

        String inputFile = "input.txt";
        String encryptedFile = "encrypted.txt";
        String decryptedFile = "decrypted.txt";

        char keyChar = 'A';

        logger.info(bundle.getString("program.start"));

        try {
            logger.info(bundle.getString("encryption.start"));
            try (Writer writer = new EncryptWriter(new FileWriter(encryptedFile), keyChar);
                 BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    writer.write(line);
                    writer.write(System.lineSeparator());
                }
            }
            logger.info(bundle.getString("encryption.done") + encryptedFile);


            logger.info(bundle.getString("decryption.start"));
            try (Reader reader = new DecryptReader(new FileReader(encryptedFile), keyChar);
                 BufferedWriter writer = new BufferedWriter(new FileWriter(decryptedFile))) {
                int c;
                while ((c = reader.read()) != -1) {
                    writer.write(c);
                }
            }
            logger.info(bundle.getString("decryption.done") + decryptedFile);

            System.out.println(bundle.getString("process.finished"));
            System.out.println(bundle.getString("file.encrypted") + encryptedFile);
            System.out.println(bundle.getString("file.decrypted") + decryptedFile);
        } catch (IOException e) {
            logger.log(Level.SEVERE, bundle.getString("error.processing"), e);
        }

        logger.warning(bundle.getString("log.warning"));
    }
}