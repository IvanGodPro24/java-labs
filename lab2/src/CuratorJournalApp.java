import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CuratorJournalApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static void main(String[] args) {
        List<CuratorRecord> records = new ArrayList<>();

        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Додати запис");
            System.out.println("2. Показати всі записи");
            System.out.println("3. Вийти");
            System.out.print("Ваш вибір: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    CuratorRecord record = createRecord();
                    if (record != null) {
                        records.add(record);
                        System.out.println("Запис успішно додано!");
                    }
                    break;
                case "2":
                    if (records.isEmpty()) {
                        System.out.println("Журнал порожній.");
                    } else {
                        System.out.println("Всі записи:");
                        records.forEach(System.out::println);
                    }
                    break;
                case "3":
                    System.out.println("Вихід з програми...");
                    return;
                default:
                    System.out.println("Невірний вибір, спробуйте ще раз.");
            }
        }
    }

    private static CuratorRecord createRecord() {
        System.out.println("\nВведення нового запису:");
        String lastName = inputNonEmpty("Прізвище студента: ");
        String firstName = inputNonEmpty("Ім'я студента: ");
        LocalDate birthDate = inputDate("Дата народження (dd.MM.yyyy): ");
        String phone = inputPhone("Телефон (+380XXXXXXXXX): ");
        String address = inputNonEmpty("Адреса (вул., будинок, квартира): ");

        return new CuratorRecord(lastName, firstName, birthDate, phone, address);
    }

    private static String inputNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) return input;
            System.out.println("Поле не може бути порожнім. Спробуйте ще раз.");
        }
    }

    private static LocalDate inputDate(String date) {
        while (true) {
            System.out.print(date);
            String input = scanner.nextLine().trim();
            try {
                return LocalDate.parse(input, DATE_FORMAT);
            } catch (DateTimeParseException e) {
                System.out.println("Невірний формат дати. Приклад: 15.09.2001");
            }
        }
    }

    private static String inputPhone(String tel) {
        while (true) {
            System.out.print(tel);
            String input = scanner.nextLine().trim();
            if (input.matches("\\+380\\d{9}")) return input;
            System.out.println("Невірний формат телефону. Приклад: +380931234567");
        }
    }
}
