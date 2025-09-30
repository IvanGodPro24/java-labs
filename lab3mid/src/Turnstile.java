import java.util.*;

public class Turnstile {
    private int allowed = 0;
    private int denied = 0;

    private Map<String, Integer> allowedByType = new HashMap<>();
    private Map<String, Integer> deniedByType = new HashMap<>();

    public void checkCard(Card card) {
        if (card != null && card.use()) {
            allowed++;
            allowedByType.put(card.getType(), allowedByType.getOrDefault(card.getType(), 0) + 1);
            System.out.println("Прохід дозволено для картки: " + card.getId());
        } else {
            denied++;
            if (card != null) {
                deniedByType.put(card.getType(), deniedByType.getOrDefault(card.getType(), 0) + 1);
            }
            System.out.println("Прохід заборонено!");
        }
    }

    public void printStats() {
        System.out.println("Дозволено: " + allowed);
        System.out.println("Заборонено: " + denied);
        System.out.println("По типах дозволено: " + allowedByType);
        System.out.println("По типах відмовлено: " + deniedByType);
    }
}