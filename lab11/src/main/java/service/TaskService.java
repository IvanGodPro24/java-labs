package service;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class TaskService {
    private final MongoCollection<Document> tasks;

    public TaskService(MongoCollection<Document> tasks) {
        this.tasks = tasks;
    }

    public void showAll() {
        tasks.find().forEach(System.out::println);
    }

    public void showTasksOfEmployee(int empNum) {
        tasks.find(new Document("employeeNumber", empNum))
                .forEach(System.out::println);
    }

    public void addTask(int empNum, String desc) {
        int taskNum = (int) (Math.random() * 10000 + 1);
        tasks.insertOne(new Document("taskNumber", taskNum)
                .append("description", desc)
                .append("employeeNumber", empNum));
        System.out.println("Завдання додано!");
    }
}