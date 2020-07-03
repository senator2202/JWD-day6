package by.kharitonov.day6.controller;

import by.kharitonov.day6.entity.Book;

public interface BookListDao {
    void addBook(Book book);

    void removeBook(Book book);

    void findBookByTag(Book book);

    void sortBooksByTag(Book book);
}
