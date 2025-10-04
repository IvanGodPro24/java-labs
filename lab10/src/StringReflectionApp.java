import java.lang.reflect.Field;
import java.util.Scanner;

public class StringReflectionApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Спосіб 1: Рядок створений як строковий літерал
        System.out.println("СПОСІБ 1: Строковий літерал");
        String literalString = "Оригінальний рядок";
        System.out.println("Оригінальний рядок: " + literalString);

        System.out.print("Введіть нове значення для заміни: ");
        String newValue1 = scanner.nextLine();

        modifyString(literalString, newValue1);
        System.out.println("Рядок після зміни: " + literalString);
        System.out.println();

        // Спосіб 2: Рядок введений з клавіатури
        System.out.println("СПОСІБ 2: Рядок з клавіатури");
        System.out.print("Введіть початковий рядок: ");
        String inputString = scanner.nextLine();
        System.out.println("Оригінальний рядок: " + inputString);

        System.out.print("Введіть нове значення для заміни: ");
        String newValue2 = scanner.nextLine();

        modifyString(inputString, newValue2);
        System.out.println("Рядок після зміни: " + inputString);

        scanner.close();

        System.out.println("\n=== Демонстрація завершена ===");
    }

    private static void modifyString(String original, String newValue) {
        try {
            Class<?> stringClass = String.class;

            Field valueField;

            try {
                valueField = stringClass.getDeclaredField("value");
            } catch (NoSuchFieldException e) {
                throw new RuntimeException("Не вдалося знайти поле value у класі String", e);
            }

            valueField.setAccessible(true);

            Object currentValue = valueField.get(original);

            if (currentValue instanceof char[]) {
                char[] newChars = newValue.toCharArray();
                valueField.set(original, newChars);
            } else if (currentValue instanceof byte[]) {
                Field coderField = stringClass.getDeclaredField("coder");
                coderField.setAccessible(true);

                byte[] newBytes = newValue.getBytes();
                valueField.set(original, newBytes);

                coderField.set(original, (byte) 0);
            }

            System.out.println("Рядок успішно змінено через рефлексію!");

        } catch (NoSuchFieldException e) {
            System.err.println("Помилка: Поле не знайдено - " + e.getMessage());
        } catch (IllegalAccessException e) {
            System.err.println("Помилка доступу: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Загальна помилка: " + e.getMessage());
            e.printStackTrace();
        }
    }
}