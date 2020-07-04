package by.kharitonov.day6.controller.validator;

import by.kharitonov.day6.model.bo.Book;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class BookValidator {
    private static final int MIN_YEAR;
    private static final int MAX_YEAR;
    private static final int MAX_PAGES;

    static {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault(),
                Locale.getDefault());
        MIN_YEAR = 0;
        MAX_YEAR = calendar.get(Calendar.YEAR);
        MAX_PAGES = 2000;
    }

    boolean validateBook(Book book) {
        if (book == null) {
            return false;
        }
        return (book.getYear() < MIN_YEAR || book.getYear() > MAX_YEAR ||
                book.getPages() <= 0 || book.getPages() > MAX_PAGES);
    }
}
