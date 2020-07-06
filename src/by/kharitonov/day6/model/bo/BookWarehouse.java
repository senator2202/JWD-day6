package by.kharitonov.day6.model.bo;

import by.kharitonov.day6.controller.type.BookTag;
import by.kharitonov.day6.model.bo.comparator.*;
import by.kharitonov.day6.model.exception.BookProjectException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    public List<Book> find(BookTag bookTag, String tagValue) {
        List<Book> list = null;
        switch (bookTag) {
            case ID:
                list = findById(tagValue);
                break;
            case TITLE:
                list = findByTitle(tagValue);
                break;
            case AUTHOR:
                list = findByAuthor(tagValue);
                break;
            case YEAR:
                list = findByYear(tagValue);
                break;
            case PAGES:
                list = findByPages(tagValue);
                break;
            case PUBLISHING_HOUSE:
                list = findByPublishingHouse(tagValue);
                break;
        }
        return list;
    }

    private List<Book> findById(String id) {
        List<Book> list = new ArrayList<>();
        for (Book book : books) {
            if (book.getId().equals(id)) {
                list.add(book);
                break;
            }
        }
        return list;
    }

    private List<Book> findByTitle(String title) {
        List<Book> list = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                list.add(book);
            }
        }
        return list;
    }

    private List<Book> findByAuthor(String title) {
        List<Book> list = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthors().contains(title)) {
                list.add(book);
            }
        }
        return list;
    }

    private List<Book> findByYear(String year) {
        List<Book> list = new ArrayList<>();
        for (Book book : books) {
            if (String.valueOf(book.getYear()).equals(year)) {
                list.add(book);
            }
        }
        return list;
    }

    private List<Book> findByPages(String pages) {
        List<Book> list = new ArrayList<>();
        for (Book book : books) {
            if (String.valueOf(book.getPages()).equals(pages)) {
                list.add(book);
            }
        }
        return list;
    }

    private List<Book> findByPublishingHouse(String publishingHouse) {
        List<Book> list = new ArrayList<>();
        for (Book book : books) {
            if (book.getPublishingHouse().equals(publishingHouse)) {
                list.add(book);
            }
        }
        return list;
    }

    public List<Book> sort(BookTag bookTag) {
        List<Book> list = new ArrayList<>(books);
        Comparator<Book> comparator = null;
        switch (bookTag) {
            case ID:
                comparator = new BookComparatorId();
                break;
            case TITLE:
                comparator = new BookComparatorTitle();
                break;
            case AUTHOR:
                comparator = new BookComparatorAuthors();
                break;
            case YEAR:
                comparator = new BookComparatorYear();
                break;
            case PAGES:
                comparator = new BookComparatorPages();
                break;
            case PUBLISHING_HOUSE:
                comparator = new BookComparatorPublishingHouse();
                break;
        }
        Collections.sort(list, comparator);
        return list;
    }

    public List<Book> getAll() {
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
