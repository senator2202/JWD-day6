package by.kharitonov.day6.controller.validator;

import by.kharitonov.day6.controller.type.BookTag;

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
        MIN_YEAR = 1800;
        MAX_YEAR = calendar.get(Calendar.YEAR);
        MAX_PAGES = 2000;
    }

    public boolean validateYear(int year) {
        return year >= MIN_YEAR && year <= MAX_YEAR;
    }

    public boolean validatePages(int pages) {
        return pages > 0 && pages <= MAX_PAGES;
    }

    public boolean validateTag(BookTag bookTag, String stringValue) {
        if (bookTag != BookTag.PAGES && bookTag != BookTag.YEAR) {
            return true;
        }
        try {
            int intValue = Integer.parseInt(stringValue);
            return bookTag == BookTag.YEAR
                    ? validateYear(intValue)
                    : validatePages(intValue);
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
