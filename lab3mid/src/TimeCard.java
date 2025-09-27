import java.time.LocalDate;

public class TimeCard extends Card {
    private LocalDate validUntil;

    public TimeCard(String id, String type, LocalDate validUntil) {
        super(id, type);
        this.validUntil = validUntil;
    }

    @Override
    public boolean use() {
        return LocalDate.now().isBefore(validUntil.plusDays(1));
    }
}
