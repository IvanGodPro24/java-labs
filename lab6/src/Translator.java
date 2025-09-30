import java.util.HashMap;

public class Translator {
    private final HashMap<String, String> dictionary;

    public Translator() {
        dictionary = new HashMap<>();
    }

    public void addWord(String english, String ukrainian) {
        dictionary.put(english.toLowerCase(), ukrainian.toLowerCase());
    }

    public String translatePhrase(String phrase) {
        StringBuilder translated = new StringBuilder();
        String[] words = phrase.toLowerCase().split(" ");

        for (String word : words) {
            if (dictionary.containsKey(word)) {
                translated.append(dictionary.get(word)).append(" ");
            } else {
                System.out.println("Переклад не знайдено! Спробуйте додати нове слово.");
            }
        }

        return translated.toString().trim();
    }
}