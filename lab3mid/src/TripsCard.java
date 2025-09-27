public class TripsCard extends Card {
    private int tripsLeft;

    public TripsCard(String id, String type, int tripsLeft) {
        super(id, type);
        this.tripsLeft = tripsLeft;
    }

    @Override
    public boolean use() {
        if (tripsLeft > 0) {
            tripsLeft--;
            return true;
        }
        return false;
    }
}