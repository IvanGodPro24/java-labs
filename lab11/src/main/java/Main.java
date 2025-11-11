import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import service.EmployeeService;
import service.TaskService;
import org.bson.Document;
import db.MongoConnection;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MongoDatabase db = MongoConnection.getConnection();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        MongoCollection<Document> employees = db.getCollection("employees");
        MongoCollection<Document> tasks = db.getCollection("tasks");

        EmployeeService employeeService = new EmployeeService(employees);
        TaskService taskService = new TaskService(tasks);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== МЕНЮ ===");
            System.out.println("1. Список усіх співробітників");
            System.out.println("2. Список усіх завдань");
            System.out.println("3. Список співробітників зазначеного відділу");
            System.out.println("4. Додати завдання співробітнику");
            System.out.println("5. Показати завдання співробітника");
            System.out.println("6. Видалити співробітника");
            System.out.println("0. Вихід");
            System.out.print(">> ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> employeeService.showAll();
                case 2 -> taskService.showAll();

                case 3 -> {
                    System.out.print("Введіть номер відділу: ");
                    int depNum = Integer.parseInt(scanner.nextLine());
                    employeeService.showByDepartment(depNum);
                }

                case 4 -> {
                    System.out.print("Введіть номер співробітника: ");
                    int empNum = Integer.parseInt(scanner.nextLine());
                    System.out.print("Введіть опис завдання: ");
                    String desc = scanner.nextLine();
                    taskService.addTask(empNum, desc);
                }

                case 5 -> {
                    System.out.print("Введіть номер співробітника: ");
                    int empNum = Integer.parseInt(scanner.nextLine());
                    taskService.showTasksOfEmployee(empNum);
                }

                case 6 -> {
                    System.out.print("Введіть номер співробітника для видалення: ");
                    int empDel = Integer.parseInt(scanner.nextLine());
                    employeeService.deleteEmployee(empDel);
                }

                case 0 -> {
                    System.out.println("Вихід з програми...");
                    System.exit(0);
                }

                default -> System.out.println("Невідомий вибір!");
            }
        }
    }
}
