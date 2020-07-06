package by.kharitonov.day6.model.dao;

import by.kharitonov.day6.controller.type.BookTag;
import by.kharitonov.day6.model.bo.Book;
import by.kharitonov.day6.model.exception.BookProjectException;

import java.util.List;

public interface BookListDao {
    void addBook(Book book) throws BookProjectException;

    void removeBook(Book book) throws BookProjectException;

    List<Book> findBooksByTag(BookTag bookTag, String tagValue)
            throws BookProjectException;

    void sortBooksByTag(Book book);

    String getAll();
}
