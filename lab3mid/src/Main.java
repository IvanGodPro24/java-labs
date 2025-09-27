public class Main {
    public static void main(String[] args) {
        Registry registry = new Registry();
        Turnstile turnstile = new Turnstile();

        Card s1 = registry.issueTimeCard("Студентська", 30);
        Card s2 = registry.issueTripsCard("Учнівська", 5);
        Card s3 = registry.issueAccumCard(10);
        Card s4 = registry.issueTripsCard("Учнівська", 1);



        turnstile.checkCard(s1);
        turnstile.checkCard(s2);
        turnstile.checkCard(s3);
        turnstile.checkCard(s2);
        turnstile.checkCard(s4);

        turnstile.printStats();
    }
}