package by.kharitonov.day6.model.dao.impl;

import by.kharitonov.day6.controller.type.BookTag;
import by.kharitonov.day6.model.bo.Book;
import by.kharitonov.day6.model.bo.BookWarehouse;
import by.kharitonov.day6.model.dao.BookListDao;
import by.kharitonov.day6.model.exception.BookProjectException;

import java.util.List;

public class BookListDaoImpl implements BookListDao {
    private BookWarehouse warehouse;

    public BookListDaoImpl() {
        warehouse = BookWarehouse.getInstance();
    }

    @Override
    public void addBook(Book book) throws BookProjectException {
        warehouse.add(book);
    }

    @Override
    public void removeBook(Book book) throws BookProjectException {
        warehouse.remove(book);
    }

    @Override
    public List<Book> findBooksByTag(BookTag bookTag, String tagValue)
            throws BookProjectException {
        return warehouse.find(bookTag, tagValue);
    }

    @Override
    public void sortBooksByTag(Book book) {
    }

    @Override
    public String getAll() {
        return warehouse.toString();
    }
}
