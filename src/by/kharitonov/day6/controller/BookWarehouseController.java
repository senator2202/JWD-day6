package by.kharitonov.day6.controller;

import by.kharitonov.day6.model.type.BookTag;
import by.kharitonov.day6.model.exception.BookProjectException;
import by.kharitonov.day6.service.BookService;

public class BookWarehouseController {
    private static final String ADD_MESSAGE = "Book was added to warehouse!";
    private static final String REMOVE_MESSAGE =
            "Book was removed form warehouse!";

    public String addBook(String[] tagValues) {
        BookService service = new BookService();
        try {
            service.addBook(tagValues);
            return ADD_MESSAGE;
        } catch (BookProjectException e) {
            return e.getMessage();
        }
    }

    public String removeBook(String[] tagValues) {
        BookService service = new BookService();
        try {
            service.removeBook(tagValues);
            return REMOVE_MESSAGE;
        } catch (BookProjectException e) {
            return e.getMessage();
        }
    }

    public String findBooks(BookTag bookTag, String tagValue) {
        BookService service = new BookService();
        try {
            return service.findBooks(bookTag, tagValue);
        } catch (BookProjectException e) {
            return e.getMessage();
        }
    }

    public String sortBooks(BookTag bookTag) {
        BookService service = new BookService();
        return service.sortBooks(bookTag);
    }

    public String findAll() {
        BookService service = new BookService();
        return service.findAll();
    }
}
