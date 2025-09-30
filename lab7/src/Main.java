import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть позитивне число N: ");
        int N = scanner.nextInt();

        System.out.println("Досконалі числа від 1 до " + N + ":");

        IntStream.rangeClosed(1, N)
                .filter(num -> IntStream.range(1, num)
                        .filter(i -> num % i == 0)
                        .sum() == num)
                .forEach(System.out::println);

        scanner.close();
    }
}
