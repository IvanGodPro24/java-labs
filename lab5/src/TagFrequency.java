import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.*;
import java.io.Serializable;

class TagData implements Serializable {
    private String tag;
    private int count;

    public TagData(String tag, int count) {
        this.tag = tag;
        this.count = count;
    }

    public String getTag() { return tag; }
    public int getCount() { return count; }

    @Override
    public String toString() {
        return tag + " -> " + count;
    }
}

public class TagFrequency {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        String url = "https://example.com";

        System.out.print("Введіть URL: ");
        String url = scanner.nextLine();

        System.out.print("Введіть ім'я файлу для збереження результату (наприклад tags.dat): ");
        String fileName = scanner.nextLine();

        try {
            Document doc = Jsoup.connect(url).get();

            Map<String, Integer> tagCount = new HashMap<>();

            for (Element element : doc.getAllElements()) {
                String tag = element.tagName();
                tagCount.put(tag, tagCount.getOrDefault(tag, 0) + 1);
            }

            List<TagData> tagList = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : tagCount.entrySet()) {
                tagList.add(new TagData(entry.getKey(), entry.getValue()));
            }

            FileManager<TagData> fm = new FileManager<>(fileName);
            fm.save(tagList);

            System.out.println("\nСортування тегів в лексикографічному порядку:");
            tagList.stream()
                    .sorted(Comparator.comparing(TagData::getTag))
                    .forEach(System.out::println);


            System.out.println("\nСортування тегів за частотою:");
            tagList.stream()
                    .sorted(Comparator.comparingInt(TagData::getCount))
                    .forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("Помилка при з'єднанні: " + e.getMessage());
        }
    }
}