package by.kharitonov.day6.model.dao;

import by.kharitonov.day6.model.exception.DaoException;
import by.kharitonov.day6.model.type.RemovingType;
import by.kharitonov.day6.model.entity.Book;
import by.kharitonov.day6.model.exception.BookProjectException;

import java.util.List;

public interface BookListDao {
    void addBook(Book book) throws DaoException;

    void removeBook(Book book, RemovingType removingType)
            throws DaoException;

    List<Book> findBookById(String id);

    List<Book> findBooksByTitle(String title);

    List<Book> findBooksByAuthor(String author);

    List<Book> findBooksByYear(String year);

    List<Book> findBooksByPages(String pages);

    List<Book> findBooksByPublishingHouse(String publishingHouse);

    List<Book> findAll();

    List<Book> sortBooksById();

    List<Book> sortBooksByTitle();

    List<Book> sortBooksByAuthors();

    List<Book> sortBooksByYear();

    List<Book> sortBooksByPages();

    List<Book> sortBooksByPublishingHouse();
}
