package test.kharitonov.day6.service.parser;

import by.kharitonov.day6.model.entity.Book;
import by.kharitonov.day6.service.parser.BookParser;
import org.testng.annotations.Test;
import test.kharitonov.day6.data_provider.StaticDataProvider;

import static org.testng.Assert.assertEquals;

public class BookParserTest {
    private final BookParser parser = new BookParser();

    @Test
    public void testPrepareBook() {
        Book book = StaticDataProvider.FIRST_BOOK;
        String[] bookTags = StaticDataProvider.parseTags(book);
        Book actualBook = parser.prepareBook(bookTags);
        assertEquals(actualBook, StaticDataProvider.FIRST_BOOK);
    }
}