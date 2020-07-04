package by.kharitonov.day6.model.bo;

import by.kharitonov.day6.model.exception.BookProjectException;

import java.util.ArrayList;
import java.util.List;

public class BookWarehouse {
    private static final int CAPACITY = 100;
    private List<Book> books;

    public BookWarehouse() {
        books = new ArrayList<>();
    }

    public void add(Book book) throws BookProjectException {
        if (books.indexOf(book) != -1) {
            throw new BookProjectException("This book already exists!");
        }
        if (books.size() == 100) {
            throw new BookProjectException("Warehouse is full!");
        }
        books.add(book);
    }

    public Book get(int index) {
        return books.get(index);
    }

    public Book remove(int index) {
        return books.remove(index);
    }
}
