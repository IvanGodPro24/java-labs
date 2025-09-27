package controller;

import model.Book;
import view.BookView;

import java.util.*;
import java.util.stream.Collectors;

public class BookController {
    private List<Book> books;
    private BookView view;
    private Scanner scanner = new Scanner(System.in);

    public BookController(List<Book> books, BookView view) {
        this.books = books;
        this.view = view;
    }

    public void run() {
        view.showMessage("Вихідний список книг:");
        view.showBooks(books);

        while (true) {
            view.showMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Введіть автора: ");
                    String author = scanner.nextLine();
                    List<Book> byAuthor = books.stream()
                            .filter(b -> b.getAuthor().equalsIgnoreCase(author))
                            .collect(Collectors.toList());
                    view.showBooks(byAuthor);
                    break;

                case "2":
                    System.out.print("Введіть видавництво: ");
                    String publisher = scanner.nextLine();
                    List<Book> byPublisher = books.stream()
                            .filter(b -> b.getPublisher().equalsIgnoreCase(publisher))
                            .collect(Collectors.toList());
                    view.showBooks(byPublisher);
                    break;

                case "3":
                    System.out.print("Введіть рік: ");
                    int year = Integer.parseInt(scanner.nextLine());
                    List<Book> afterYear = books.stream()
                            .filter(b -> b.getYear() > year)
                            .collect(Collectors.toList());
                    view.showBooks(afterYear);
                    break;

                case "4":
                    books.sort(Comparator.comparing(Book::getPublisher));
                    view.showMessage("Відсортовано за видавництвами:");
                    view.showBooks(books);
                    break;

                case "5":
                    view.showMessage("Вихід з програми...");
                    return;

                default:
                    view.showMessage("Невірний вибір. Спробуйте ще раз.");
            }
        }
    }
}
