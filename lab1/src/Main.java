import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть позитивне число N: ");
        int N = scanner.nextInt();

        System.out.println("Досконалі числа від 1 до " + N + ":");

        for (int num = 1; num <= N; num++) {
            int sum = 0;

            for (int i = 1; i < num; i++) {
                if (num % i == 0) {
                    sum += i;
                }
            }

            if (sum == num) {
                System.out.println(num);
            }
        }

        scanner.close();
    }
}
