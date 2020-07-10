package by.kharitonov.day6.controller.entity;

import by.kharitonov.day6.model.entity.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommandResult {
    private Optional<Exception> exception;
    private List<Book> bookList;

    public CommandResult() {
        exception = Optional.empty();
        bookList = new ArrayList<>();
    }

    public CommandResult(List<Book> booksList, Optional<Exception> exception) {
        this.bookList = booksList;
        this.exception=exception;
    }

    public Optional<Exception> getException() {
        return exception;
    }

    public List<Book> getBookList() {
        return new ArrayList<>(bookList);
    }
}
