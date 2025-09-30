import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RedBlackTree tree = new RedBlackTree();

        System.out.println("Оберіть режим:");
        System.out.println("1. Згенерувати випадкові числа");
        System.out.println("2. Ввести числа вручну");
        int choice = scanner.nextInt();

        if (choice == 1) {
            Random rand = new Random();
            int[] arr = new int[10];
            System.out.print("Згенерований масив: ");
            for (int i = 0; i < 10; i++) {
                arr[i] = rand.nextInt(100); // від 0 до 99
                System.out.print(arr[i] + " ");
                tree.insert(arr[i]);
            }
            System.out.println();
        } else {
            System.out.println("Введіть 10 чисел:");
            for (int i = 0; i < 10; i++) {
                int num = scanner.nextInt();
                tree.insert(num);
            }
        }

        System.out.println("In-order обхід (з кольорами):");
        tree.inorder();
    }
}