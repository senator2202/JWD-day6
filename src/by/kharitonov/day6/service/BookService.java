package by.kharitonov.day6.service;

import by.kharitonov.day6.model.dao.BookListDao;
import by.kharitonov.day6.model.dao.impl.BookListDaoImpl;
import by.kharitonov.day6.model.entity.Book;
import by.kharitonov.day6.model.exception.BookProjectException;
import by.kharitonov.day6.model.exception.BookServiceException;
import by.kharitonov.day6.model.exception.DaoException;
import by.kharitonov.day6.model.type.BookTag;
import by.kharitonov.day6.model.validator.BookValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static by.kharitonov.day6.model.validator.BookValidator.BOOK_TAG_INDEX;
import static by.kharitonov.day6.model.validator.BookValidator.TAG_VALUE_INDEX;

public class BookService {
    private Book prepareBook(String[] tagValues)
            throws BookServiceException {
        BookValidator validator = new BookValidator();
        if (!validator.validateAllBookTags(tagValues)) {
            throw new BookServiceException("Invalid book parameters!");
        }
        Book book;
        book = Book.newBuilder()
                .setId(tagValues[0])
                .setTitle(tagValues[1])
                .setAuthors(tagValues[2].split(", "))
                .setYear(Integer.parseInt(tagValues[3]))
                .setPages(Integer.parseInt(tagValues[4]))
                .setPublishingHouse(tagValues[5])
                .build();
        return book;
    }

    public List<Book> addBook(String[] tagValues) throws BookServiceException {
        Book book = prepareBook(tagValues);
        BookListDao dao = new BookListDaoImpl();
        List<Book> resultList = new ArrayList<>();
        try {
            dao.addBook(book);
            resultList.add(book);
            return resultList;
        } catch (DaoException e) {
            throw new BookServiceException(e.getMessage(), e.getCause());
        }
    }

    public List<Book> removeBook(String[] tagValues) throws BookServiceException {
        Book book = prepareBook(tagValues);
        BookListDao dao = new BookListDaoImpl();
        List<Book> resultList = new ArrayList<>();
        try {
            dao.removeBook(book);
            resultList.add(book);
            return resultList;
        } catch (DaoException e) {
            throw new BookServiceException(e.getMessage(), e.getCause());
        }
    }

    public List<Book> findBooks(String[] parameters)
            throws BookServiceException {
        BookValidator validator = new BookValidator();
        if (!validator.validateFindParameters(parameters)) {
            throw new BookServiceException("Invalid tag data!");
        }
        BookTag bookTag = BookTag
                .valueOf(parameters[BOOK_TAG_INDEX].toUpperCase());
        String tagValue = parameters[TAG_VALUE_INDEX];
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
        return foundList;
    }

    public List<Book> sortBooks(String[] parameters) throws BookServiceException {
        BookValidator validator = new BookValidator();
        if (!validator.validateSortParameters(parameters)) {
            throw new BookServiceException("Invalid tag data!");
        }
        BookTag bookTag = BookTag
                .valueOf(parameters[BOOK_TAG_INDEX].toUpperCase());
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
        return sortedList;
    }

    /*public String findAll() {
        BookListDao dao = new BookListDaoImpl();
        List<Book> list = dao.findAll();
        return listToString(list);
    }*/

    /*private String listToString(List<Book> list) {
        if (list.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder(list.get(0).toString());
        for (int i = 1; i < list.size(); i++) {
            sb.append("\n").append(list.get(i));
        }
        return sb.toString();
    }*/
}
