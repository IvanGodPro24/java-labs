import java.util.*;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int nAccounts = 100;
        int nThreads = 5000;
        Random rand = new Random();

        List<Account> accounts = new ArrayList<>();
        Bank bank = new Bank();

        for (int i = 0; i < nAccounts; i++) {
            accounts.add(new Account(i, rand.nextInt(1000) + 500));
        }

        int initialTotal = accounts.stream().mapToInt(Account::getBalance).sum();
        System.out.println("Початкова сума: " + initialTotal);

        ExecutorService executor = Executors.newFixedThreadPool(20);

        for (int i = 0; i < nThreads; i++) {
            executor.submit(() -> {
                Account from = accounts.get(rand.nextInt(nAccounts));
                Account to = accounts.get(rand.nextInt(nAccounts));
                int amount = rand.nextInt(50);

                bank.transfer(from, to, amount);
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        int finalTotal = accounts.stream().mapToInt(Account::getBalance).sum();
        System.out.println("Фінальна сума: " + finalTotal);

        if (initialTotal == finalTotal) {
            System.out.println("Баланс збережено, помилок немає!");
        } else {
            System.out.println("Помилка! Баланс не зійшовся!");
        }
    }
}
