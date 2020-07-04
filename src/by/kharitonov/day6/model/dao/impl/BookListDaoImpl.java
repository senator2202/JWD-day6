package by.kharitonov.day6.model.dao.impl;

import by.kharitonov.day6.model.bo.Book;
import by.kharitonov.day6.model.bo.BookWarehouse;
import by.kharitonov.day6.model.dao.BookListDao;
import by.kharitonov.day6.model.exception.BookProjectException;

public class BookListDaoImpl implements BookListDao {
    private static BookListDaoImpl bookListDaoImplInstance;
    private BookWarehouse warehouse;

    private BookListDaoImpl() {
        warehouse = new BookWarehouse();
    }

    public static BookListDaoImpl getInstance() {
        if (bookListDaoImplInstance == null) {
            bookListDaoImplInstance = new BookListDaoImpl();
        }
        return bookListDaoImplInstance;
    }

    @Override
    public void addBook(Book book) throws BookProjectException {
        warehouse.add(book);
    }

    @Override
    public void removeBook(Book book) {
    }

    @Override
    public void findBookByTag(Book book) {
    }

    @Override
    public void sortBooksByTag(Book book) {
    }
}
