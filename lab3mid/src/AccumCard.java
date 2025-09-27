public class AccumCard extends Card {
    private double balance;
    private static final double FARE = 10.0;

    public AccumCard(String id, double balance) {
        super(id, "Звичайна");
        this.balance = balance;
    }

    @Override
    public boolean use() {
        if (balance >= FARE) {
            balance -= FARE;
            return true;
        }
        return false;
    }
}