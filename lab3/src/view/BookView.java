package view;

import model.Book;

import java.util.List;

public class BookView {
    public void showMenu() {
        System.out.println("\n=== МЕНЮ ===");
        System.out.println("1. Список книг автора");
        System.out.println("2. Список книг видавництва");
        System.out.println("3. Список книг після певного року");
        System.out.println("4. Відсортувати книги за видавництвами");
        System.out.println("5. Вийти");
        System.out.print("Ваш вибір: ");
    }

    public void showBooks(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("Книг не знайдено.");
        } else {
            books.forEach(System.out::println);
        }
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }
}
