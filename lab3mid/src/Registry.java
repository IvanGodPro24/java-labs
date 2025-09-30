import java.time.LocalDate;
import java.util.*;

public class Registry {
    private List<Card> cards = new ArrayList<>();

    public Card issueTimeCard(String type, int days) {
        Card c = new TimeCard(UUID.randomUUID().toString(), type, LocalDate.now().plusDays(days));
        cards.add(c);
        return c;
    }

    public Card issueTripsCard(String type, int trips) {
        Card c = new TripsCard(UUID.randomUUID().toString(), type, trips);
        cards.add(c);
        return c;
    }

    public Card issueAccumCard(String type, double balance) {
        if (!"Звичайна".equals(type)) {
            throw new IllegalArgumentException("Накопичувальні картки можуть бути тільки звичайного типу!");
        }

        Card c = new AccumCard(UUID.randomUUID().toString(), balance);
        cards.add(c);
        return c;
    }
}
