import controller.BookController;
import model.Book;
import view.BookView;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Book> books = Arrays.asList(
                new Book("Пригоди Тома Сойєра", "Марк Твен", "Ранок", 2010, 320, 150.0),
                new Book("Кобзар", "Тарас Шевченко", "Освіта", 2015, 400, 200.0),
                new Book("Майстер і Маргарита", "Михайло Булгаков", "А-Ба-Ба-Га-Ла-Ма-Га", 2018, 450, 250.0),
                new Book("1984", "Джордж Орвелл", "Ранок", 2020, 350, 180.0),
                new Book("Мартін Іден", "Джек Лондон", "Фоліо", 2012, 380, 170.0),
                new Book("Айвенго", "Вальтер Скотт", "Фоліо", 2005, 500, 210.0),
                new Book("Гаррі Поттер", "Дж. Роулінг", "А-Ба-Ба-Га-Ла-Ма-Га", 2019, 600, 300.0),
                new Book("Лісова пісня", "Леся Українка", "Освіта", 2007, 280, 160.0),
                new Book("Собор", "Олесь Гончар", "Освіта", 2011, 370, 190.0),
                new Book("Фауст", "Йоганн Ґете", "Фоліо", 2016, 420, 220.0)
        );

        BookView view = new BookView();
        BookController controller = new BookController(books, view);
        controller.run();
    }
}
