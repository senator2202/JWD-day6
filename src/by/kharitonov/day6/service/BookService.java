package by.kharitonov.day6.service;

import by.kharitonov.day6.model.dao.BookListDao;
import by.kharitonov.day6.model.dao.impl.BookListDaoImpl;
import by.kharitonov.day6.model.entity.Book;
import by.kharitonov.day6.service.exception.ServiceException;
import by.kharitonov.day6.model.exception.DaoException;
import by.kharitonov.day6.model.type.BookTag;
import by.kharitonov.day6.model.validator.BookValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class BookService {
    private Book prepareBook(String[] tagValues)
            throws ServiceException {
        BookValidator validator = new BookValidator();
        if (!validator.validateAllBookTags(tagValues)) {
            throw new ServiceException("Invalid book parameters!");
        }
        Book book;
        book = Book.newBuilder()
                .setId(tagValues[BookValidator.ID_INDEX])
                .setTitle(tagValues[BookValidator.TITLE_INDEX])
                .setAuthors(tagValues[BookValidator.AUTHORS_INDEX].split(", "))
                .setYear(Integer.parseInt(tagValues[BookValidator.YEAR_INDEX]))
                .setPages(Integer
                        .parseInt(tagValues[BookValidator.PAGES_INDEX]))
                .setPublishingHouse
                        (tagValues[BookValidator.PUBLISHING_HOUSE_INDEX])
                .build();
        return book;
    }

    public List<Book> addBook(String[] tagValues) throws ServiceException {
        Book book = prepareBook(tagValues);
        BookListDao dao = new BookListDaoImpl();
        List<Book> resultList = new ArrayList<>();
        try {
            dao.addBook(book);
            resultList.add(book);
            return resultList;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    public List<Book> removeBook(String[] tagValues)
            throws ServiceException {
        Book book = prepareBook(tagValues);
        BookListDao dao = new BookListDaoImpl();
        List<Book> resultList = new ArrayList<>();
        try {
            dao.removeBook(book);
            resultList.add(book);
            return resultList;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    public List<Book> findBooks(String[] parameters)
            throws ServiceException {
        BookValidator validator = new BookValidator();
        if (!validator.validateFindParameters(parameters)) {
            throw new ServiceException("Invalid tag data!");
        }
        BookTag bookTag = BookTag
                .valueOf(parameters[BookValidator.BOOK_TAG_INDEX]
                        .toUpperCase());
        String tagValue = parameters[BookValidator.TAG_VALUE_INDEX];
        List<Book> foundList;
        Function<String, List<Book>> findFunction = defineFindFunction(bookTag);
        foundList = findFunction.apply(tagValue);
        return foundList;
    }

    private Function<String, List<Book>> defineFindFunction(BookTag bookTag) {
        BookListDao dao = new BookListDaoImpl();
        Function<String, List<Book>> findFunction = null;
        switch (bookTag) {
            case ID:
                findFunction = dao::findBookById;
                break;
            case TITLE:
                findFunction = dao::findBooksByTitle;
                break;
            case AUTHORS:
                findFunction = dao::findBooksByAuthor;
                break;
            case YEAR:
                findFunction = dao::findBooksByYear;
                break;
            case PAGES:
                findFunction = dao::findBooksByPages;
                break;
            case PUBLISHING_HOUSE:
                findFunction = dao::findBooksByPublishingHouse;
                break;
        }
        return findFunction;
    }

    public List<Book> sortBooks(String[] parameters)
            throws ServiceException {
        BookValidator validator = new BookValidator();
        if (!validator.validateSortParameters(parameters)) {
            throw new ServiceException("Invalid tag data!");
        }
        BookTag bookTag = BookTag
                .valueOf(parameters[BookValidator.BOOK_TAG_INDEX]
                        .toUpperCase());
        List<Book> sortedList;
        Supplier<List<Book>> sortSupplier = defineSortSupplier(bookTag);
        sortedList = sortSupplier.get();
        return sortedList;
    }

    private Supplier<List<Book>> defineSortSupplier(BookTag bookTag) {
        BookListDao dao = new BookListDaoImpl();
        Supplier<List<Book>> sortSupplier = null;
        switch (bookTag) {
            case ID:
                sortSupplier = dao::sortBooksById;
                break;
            case TITLE:
                sortSupplier = dao::sortBooksByTitle;
                break;
            case AUTHORS:
                sortSupplier = dao::sortBooksByAuthors;
                break;
            case YEAR:
                sortSupplier = dao::sortBooksByYear;
                break;
            case PAGES:
                sortSupplier = dao::sortBooksByPages;
                break;
            case PUBLISHING_HOUSE:
                sortSupplier = dao::sortBooksByPublishingHouse;
                break;
        }
        return sortSupplier;
    }
}
