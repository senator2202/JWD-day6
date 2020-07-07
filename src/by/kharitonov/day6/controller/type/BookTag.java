package by.kharitonov.day6.controller.type;

import by.kharitonov.day6.model.dao.BookListDao;
import by.kharitonov.day6.model.dao.impl.BookListDaoImpl;
import by.kharitonov.day6.model.entity.Book;

import java.util.List;
import java.util.function.Function;

public enum BookTag {
    ID, TITLE, AUTHOR, YEAR, PAGES, PUBLISHING_HOUSE;

    public Function findFunction() {
        BookListDao bookListDao = new BookListDaoImpl();
        Function<String, List<Book>> findFunction = null;
        switch (this) {
            case ID:
                return value -> bookListDao.findBookById((String) value);
            case TITLE:
                findFunction = value -> bookListDao.findBooksByTitle(value);
                break;
            case AUTHOR:
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
            case AUTHOR:
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
