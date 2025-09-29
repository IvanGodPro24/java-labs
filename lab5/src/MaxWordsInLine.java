import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MaxWordsInLine {
    public static void main(String[] args) {
        String fileName = "input.txt";
        String maxLine = "";
        int maxWords = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.trim().split("\\s+");

                int wordCount = (line.trim().isEmpty()) ? 0 : words.length;

                if (wordCount > maxWords) {
                    maxWords = wordCount;
                    maxLine = line;
                }
            }

            if (maxWords > 0) {
                System.out.println("Рядок з максимальною кількістю слів (" + maxWords + "):");
                System.out.println(maxLine);
            } else {
                System.out.println("Файл порожній або не містить слів.");
            }

        } catch (IOException e) {
            System.out.println("Помилка при читанні файлу: " + e.getMessage());
        }
    }
}
