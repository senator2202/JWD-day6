package by.kharitonov.day6.model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommandResult {
    private final Exception exception;
    private final List<Book> bookList;

    public CommandResult() {
        exception = null;
        bookList = new ArrayList<>();
    }

    public CommandResult(List<Book> booksList, Exception exception) {
        this.bookList = booksList;
        this.exception = exception;
    }

    public Optional<Exception> getException() {
        return exception != null ? Optional.of(exception) : Optional.empty();
    }

    public List<Book> getBookList() {
        return new ArrayList<>(bookList);
    }
}
