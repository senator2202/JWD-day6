package by.kharitonov.day6.controller.type;

import by.kharitonov.day6.model.dao.BookListDao;
import by.kharitonov.day6.model.dao.impl.BookListDaoImpl;
import by.kharitonov.day6.model.entity.Book;

import java.util.List;
import java.util.function.Function;

public enum BookTag {
    ID("id"), TITLE("title"), AUTHORS("authors"),
    YEAR("year"), PAGES("number of pages"),
    PUBLISHING_HOUSE("publishing house");

    private final String tagName;

    BookTag(String tagName) {
        this.tagName = tagName;
    }

    public String getTagName() {
        return tagName;
    }

    public Function findFunction() {
        BookListDao bookListDao = new BookListDaoImpl();
        Function<String, List<Book>> findFunction = null;
        switch (this) {
            case ID:
                return value -> bookListDao.findBookById((String) value);
            case TITLE:
                findFunction = value -> bookListDao.findBooksByTitle(value);
                break;
            case AUTHORS:
                findFunction = value -> bookListDao.findBooksByAuthor(value);
                break;
            case YEAR:
                findFunction = value -> bookListDao.findBooksByYear(value);
                break;
            case PAGES:
                findFunction = value -> bookListDao.findBooksByPages(value);
                break;
            case PUBLISHING_HOUSE:
                findFunction = value ->
                        bookListDao.findBooksByPublishingHouse(value);
                break;
        }
        return findFunction;
    }

    public Function sortFunction() {
        BookListDao bookListDao = new BookListDaoImpl();
        Function sortFucntion = null;
        switch (this) {
            case ID:
                sortFucntion = value -> bookListDao.sortBooksById();
                break;
            case TITLE:
                sortFucntion = value -> bookListDao.sortBooksByTitle();
                break;
            case AUTHORS:
                sortFucntion = value -> bookListDao.sortBooksByAuthors();
                break;
            case YEAR:
                sortFucntion = value -> bookListDao.sortBooksByYear();
                break;
            case PAGES:
                sortFucntion = value -> bookListDao.sortBooksByPages();
                break;
            case PUBLISHING_HOUSE:
                sortFucntion = value ->
                        bookListDao.sortBooksByPublishingHouse();
                break;
        }
        return sortFucntion;
    }
}
