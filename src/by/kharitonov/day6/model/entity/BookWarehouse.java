package by.kharitonov.day6.model.entity;

import by.kharitonov.day6.model.parser.BookParser;
import by.kharitonov.day6.model.exception.BookProjectException;
import by.kharitonov.day6.model.file.WarehouseFileReader;

import java.util.ArrayList;
import java.util.List;

public class BookWarehouse {
    private static BookWarehouse bookWarehouseInstance;
    private static final int CAPACITY = 100;
    private static final String DEFAULT_FILEPATH = "resources\\Warehouse.txt";
    private List<Book> books;

    private BookWarehouse() {
        try {
            WarehouseFileReader fileReader = new WarehouseFileReader();
            String data = fileReader.read(DEFAULT_FILEPATH);
            BookParser parser = new BookParser();
            books = parser.parseBookList(data);
        } catch (BookProjectException e) {
            books = new ArrayList<>();
        }
    }

    public static BookWarehouse getInstance() {
        if (bookWarehouseInstance == null) {
            bookWarehouseInstance = new BookWarehouse();
        }
        return bookWarehouseInstance;
    }

    public void add(Book book) throws BookProjectException {
        if (book == null) {
            throw new BookProjectException("Book has null pointer!");
        }
        if (books.indexOf(book) != -1) {
            throw new BookProjectException("This book already exists!");
        }
        if (books.size() == CAPACITY) {
            throw new BookProjectException("Warehouse is full!");
        }
        books.add(book);
    }

    public void remove(Book book) throws BookProjectException {
        if (book == null) {
            throw new BookProjectException("Book has null pointer!");
        }
        boolean flag = books.remove(book);
        if (!flag) {
            throw new BookProjectException("Such book doesn't exist!");
        }
    }

    public List<Book> findAll() {
        return new ArrayList<>(books);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BookWarehouse that = (BookWarehouse) o;
        return books.equals(that.books);
    }

    @Override
    public int hashCode() {
        int result = books.hashCode();
        result = 31 * result + ((Integer) CAPACITY).hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BookWarehouse{");
        for (Book book : books) {
            sb.append("\n").append(book);
        }
        sb.append("\n").append('}');
        return sb.toString();
    }
}
