package by.kharitonov.day6.model.service;

import by.kharitonov.day6.model.dao.BookListDao;
import by.kharitonov.day6.model.dao.impl.BookListDaoImpl;
import by.kharitonov.day6.model.entity.Book;
import by.kharitonov.day6.model.exception.BookProjectException;

public class BookService {
    public void addBook(Book book) throws BookProjectException {
        BookListDao dao =new BookListDaoImpl();
        dao.addBook(book);
    }
}
