package by.kharitonov.day6.exception;

public class BookProjectException extends Exception {
    public BookProjectException() {
    }

    public BookProjectException(String message) {
        super(message);
    }

    public BookProjectException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookProjectException(Throwable cause) {
        super(cause);
    }
}
