package by.kharitonov.day6.dao;

import by.kharitonov.day6.bo.Book;

public interface BookListDao {
    void addBook(Book book);

    void removeBook(Book book);

    void findBookByTag(Book book);

    void sortBooksByTag(Book book);
}
