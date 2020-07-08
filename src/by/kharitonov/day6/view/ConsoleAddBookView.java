package by.kharitonov.day6.view;

import by.kharitonov.day6.controller.BookWarehouseController;
import by.kharitonov.day6.controller.type.BookTag;
import by.kharitonov.day6.model.entity.Book;
import by.kharitonov.day6.view.service.UserCommunicationService;

public class ConsoleAddBookView {
    public void startView() {
        Book book = createBookConsole();
        BookWarehouseController controller = new BookWarehouseController();
        controller.addBook();
    }

    private Book createBookConsole() {
        Book book;
        UserCommunicationService communicator = new UserCommunicationService();
        BookTag[] bookTags = BookTag.values();
        String[] tagValues = new String[bookTags.length];
        for (int i = 1; i < tagValues.length; i++) {
            tagValues[i] = communicator.requestEnterTag(bookTags[i]);
        }
        book = Book.newBuilder()
                .setTitle(tagValues[1])
                .setAuthors(tagValues[2])
                .setYear(Integer.parseInt(tagValues[3]))
                .setPages(Integer.parseInt(tagValues[4]))
                .setPublishingHouse(tagValues[5])
                .build();
        return book;
    }
}
