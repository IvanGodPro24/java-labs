package service;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class EmployeeService {
    private final MongoCollection<Document> employees;

    public EmployeeService(MongoCollection<Document> employees) {
        this.employees = employees;
    }

    public void showAll() {
        employees.find().forEach(System.out::println);
    }

    public void showByDepartment(int departmentNumber) {
        employees.find(new Document("departmentNumber", departmentNumber))
                .forEach(System.out::println);
    }

    public void deleteEmployee(int empNum) {
        employees.deleteOne(new Document("employeeNumber", empNum));
        System.out.println("Співробітника видалено!");
    }
}