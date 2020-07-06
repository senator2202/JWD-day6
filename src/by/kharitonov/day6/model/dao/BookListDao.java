package by.kharitonov.day6.model.dao;

import by.kharitonov.day6.controller.type.BookTag;
import by.kharitonov.day6.model.bo.Book;
import by.kharitonov.day6.model.exception.BookProjectException;
import com.sun.istack.internal.NotNull;

import java.util.List;

public interface BookListDao {
    void addBook(Book book) throws BookProjectException;

    void removeBook(Book book) throws BookProjectException;

    List<Book> findBooksByTag(@NotNull BookTag bookTag,
                              @NotNull String tagValue);

    List<Book> sortBooksByTag(@NotNull BookTag bookTag) ;

    List<Book> getAll();
}
