package by.kharitonov.day6.model.bo;

import by.kharitonov.day6.model.exception.BookProjectException;

import java.util.ArrayList;
import java.util.List;

public class BookWarehouse {
    private static BookWarehouse bookWarehouseInstance;
    private static final int CAPACITY = 100;
    private List<Book> books;

    private BookWarehouse() {
        books = new ArrayList<>();
    }

    public static BookWarehouse getInstance() {
        if (bookWarehouseInstance == null) {
            bookWarehouseInstance = new BookWarehouse();
        }
        return bookWarehouseInstance;
    }

    public int size() {
        return books.size();
    }

    public void add(Book book) throws BookProjectException {
        if (book==null) {
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

    public Book get(int index) throws BookProjectException {
        if (index >= books.size()) {
            throw new BookProjectException("Index is too big!");
        }
        return books.get(index);
    }

    public void remove(Book book) throws BookProjectException {
        if (book==null) {
            throw new BookProjectException("Book has null pointer!");
        }
        boolean flag = books.remove(book);
        if (!flag) {
            throw new BookProjectException("Such book doesn't exist!");
        }
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
