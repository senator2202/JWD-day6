package by.kharitonov.day6.view;

import by.kharitonov.day6.controller.type.BookTag;
import by.kharitonov.day6.model.entity.Book;

import java.util.List;
import java.util.Optional;

public class ConsoleFindSortBooksView {
    private static final String TAG_MENU =
            "Choose a tag to find by:\n" +
                    "1 - id;\n" +
                    "2 - title;\n" +
                    "3 - author;\n" +
                    "4 - year;\n" +
                    "5 - pages;\n" +
                    "6 - publishing house.\n";
    private static final String ENTER_TAG_MESSAGE = "Enter %s:";

    public void printFindMenu() {
        System.out.println(TAG_MENU);
    }

    public void printEnterTagMessage(BookTag bookTag) {
        System.out.println(String.format(ENTER_TAG_MESSAGE, bookTag));
    }

    public void printFindSortResult(Object object) {
        if (object instanceof List) {
            List<Book> books = (List<Book>) object;
            for (Book book : books) {
                System.out.println(book);
            }
        }
        if (object instanceof Optional) {
            Optional<Book> optionalBook = (Optional<Book>) object;
            optionalBook.ifPresent(System.out::println);
        }
    }
}
