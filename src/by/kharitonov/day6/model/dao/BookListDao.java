package by.kharitonov.day6.model.dao;

import by.kharitonov.day6.model.entity.Book;
import by.kharitonov.day6.model.exception.BookProjectException;

import java.util.List;
import java.util.Optional;

public interface BookListDao {
    void addBook(Book book) throws BookProjectException;

    void removeBook(Book book) throws BookProjectException;

    Optional<Book> findBookById(String id);

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
