package by.kharitonov.day6.model.service;

import by.kharitonov.day6.model.type.BookTag;
import by.kharitonov.day6.model.type.RemovingType;
import by.kharitonov.day6.model.dao.BookListDao;
import by.kharitonov.day6.model.dao.impl.BookListDaoImpl;
import by.kharitonov.day6.model.entity.Book;
import by.kharitonov.day6.model.exception.BookProjectException;
import by.kharitonov.day6.model.validator.BookValidator;

import java.util.List;
import java.util.function.Function;

public class BookService {
    private Book prepareBook(String[] tagValues)
            throws BookProjectException {
        BookValidator validator = new BookValidator();
        if (!validator.validateBookTags(tagValues)) {
            throw new BookProjectException("Invalid book parameters!");
        }
        Book book;
        book = Book.newBuilder()
                .setId(tagValues[0])
                .setTitle(tagValues[1])
                .setAuthors(tagValues[2])
                .setYear(Integer.parseInt(tagValues[3]))
                .setPages(Integer.parseInt(tagValues[4]))
                .setPublishingHouse(tagValues[5])
                .build();
        return book;
    }

    public void addBook(String[] tagValues) throws BookProjectException {
        Book book = prepareBook(tagValues);
        BookListDao dao = new BookListDaoImpl();
        dao.addBook(book);
    }

    public void removeBook(String[] tagValues) throws BookProjectException {
        Book book = prepareBook(tagValues);
        BookListDao dao = new BookListDaoImpl();
        dao.removeBook(book, RemovingType.IGNORE_ID);
    }

    public String findBooks(BookTag bookTag, String tagValue)
            throws BookProjectException {
        BookValidator validator = new BookValidator();
        if (!validator.validateTag(bookTag, tagValue)) {
            throw new BookProjectException("Invalid tag data!");
        }
        BookListDao dao = new BookListDaoImpl();
        List<Book> foundList;
        Function<String, List<Book>> findFunction = null;
        switch (bookTag) {
            case ID:
                findFunction = value -> dao.findBookById(value);
                break;
            case TITLE:
                findFunction = value -> dao.findBooksByTitle(value);
                break;
            case AUTHORS:
                findFunction = value -> dao.findBooksByAuthor(value);
                break;
            case YEAR:
                findFunction = value -> dao.findBooksByYear(value);
                break;
            case PAGES:
                findFunction = value -> dao.findBooksByPages(value);
                break;
            case PUBLISHING_HOUSE:
                findFunction = value ->
                        dao.findBooksByPublishingHouse(value);
                break;
        }
        foundList = findFunction.apply(tagValue);
        return listToString(foundList);
    }

    public String sortBooks(BookTag bookTag) {
        BookListDao dao = new BookListDaoImpl();
        List<Book> sortedList;
        Function<String, List<Book>> sortFunction = null;
        switch (bookTag) {
            case ID:
                sortFunction = value -> dao.sortBooksById();
                break;
            case TITLE:
                sortFunction = value -> dao.sortBooksByTitle();
                break;
            case AUTHORS:
                sortFunction = value -> dao.sortBooksByAuthors();
                break;
            case YEAR:
                sortFunction = value -> dao.sortBooksByYear();
                break;
            case PAGES:
                sortFunction = value -> dao.sortBooksByPages();
                break;
            case PUBLISHING_HOUSE:
                sortFunction = value -> dao.sortBooksByPublishingHouse();
                break;
        }
        sortedList = sortFunction.apply("");
        return listToString(sortedList);
    }

    public String findAll() {
        BookListDao dao = new BookListDaoImpl();
        List<Book> list = dao.findAll();
        return listToString(list);
    }

    private String listToString(List<Book> list) {
        if (list.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder(list.get(0).toString());
        for (int i = 1; i < list.size(); i++) {
            sb.append("\n").append(list.get(i));
        }
        return sb.toString();
    }
}
