import java.util.*;

public class Turnstile {
    private int allowed = 0;
    private int denied = 0;
    private Map<String, Integer> statsByType = new HashMap<>();

    public void checkCard(Card card) {
        if (card != null && card.use()) {
            allowed++;
            statsByType.put(card.getType(), statsByType.getOrDefault(card.getType(), 0) + 1);
            System.out.println("Прохід дозволено для картки: " + card.getId());
        } else {
            denied++;
            System.out.println("Прохід заборонено!");
        }
    }

    public void printStats() {
        System.out.println("Дозволено: " + allowed);
        System.out.println("Заборонено: " + denied);
        System.out.println("По типах: " + statsByType);
    }
}