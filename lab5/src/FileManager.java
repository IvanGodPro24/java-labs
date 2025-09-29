import java.io.*;
import java.util.List;

public class FileManager<T> {
    private final String fileName;

    public FileManager(String fileName) {
        this.fileName = fileName;
    }

    public void save(List<T> data) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(data);
            System.out.println("Дані збережені у файл: " + fileName);
        } catch (IOException e) {
            System.out.println("Помилка при збереженні: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public List<T> load() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            return (List<T>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Помилка при завантаженні: " + e.getMessage());
            return null;
        }
    }
}
