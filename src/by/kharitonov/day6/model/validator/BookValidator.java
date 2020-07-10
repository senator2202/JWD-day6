package by.kharitonov.day6.model.validator;

import by.kharitonov.day6.model.type.BookTag;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class BookValidator {
    private static final int MIN_YEAR = 1800;
    private static final int MAX_YEAR;
    private static final int MAX_PAGES = 2000;
    private static final int YEAR_INDEX = 3;
    private static final int PAGES_INDEX = 4;
    private static final int TAG_NUMBERS = 6;
    private static final int FIND_PARAMETERS_NUMBER = 2;
    public static final int BOOK_TAG_INDEX = 0;
    public static final int TAG_VALUE_INDEX = 1;

    static {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault(),
                Locale.getDefault());
        MAX_YEAR = calendar.get(Calendar.YEAR);
    }

    private boolean validateYear(int year) {
        return year >= MIN_YEAR && year <= MAX_YEAR;
    }

    private boolean validatePages(int pages) {
        return pages > 0 && pages <= MAX_PAGES;
    }

    public boolean validateTag(BookTag bookTag, String stringValue) {
        if (bookTag == null || stringValue == null || stringValue.isEmpty()) {
            return false;
        }
        if (bookTag != BookTag.PAGES && bookTag != BookTag.YEAR) {
            return true;
        }
        boolean result;
        try {
            int tagValue = Integer.parseInt(stringValue);
            result = bookTag == BookTag.YEAR
                    ? validateYear(tagValue)
                    : validatePages(tagValue);
        } catch (NumberFormatException e) {
            result = false;
        }
        return result;
    }

    public boolean validateAllBookTags(String[] tagValues) {
        if (tagValues == null || tagValues.length != TAG_NUMBERS) {
            return false;
        }
        boolean result = true;
        try {
            int year = Integer.parseInt(tagValues[YEAR_INDEX]);
            int pages = Integer.parseInt(tagValues[PAGES_INDEX]);
            if (!validateYear(year) || !validatePages(pages)) {
                result = false;
            }
        } catch (NumberFormatException e) {
            result = false;
        }
        return result;
    }

    public boolean validateFindParameters(String[] parameters) {
        if (parameters.length != FIND_PARAMETERS_NUMBER) {
            return false;
        }
        boolean result;
        try {
            BookTag bookTag =
                    BookTag.valueOf(parameters[BOOK_TAG_INDEX].toUpperCase());
            result = validateTag(bookTag, parameters[TAG_VALUE_INDEX]);
        } catch (IllegalArgumentException e) {
            result = false;
        }
        return result;
    }
}
