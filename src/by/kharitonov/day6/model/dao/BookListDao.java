package by.kharitonov.day6.model.dao;

import by.kharitonov.day6.model.bo.Book;
import by.kharitonov.day6.model.exception.BookProjectException;

public interface BookListDao {
    void addBook(Book book) throws BookProjectException;

    void removeBook(Book book);

    void findBookByTag(Book book);

    void sortBooksByTag(Book book);
}
