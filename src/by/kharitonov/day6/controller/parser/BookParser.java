package by.kharitonov.day6.controller.parser;

import by.kharitonov.day6.model.entity.Book;
import by.kharitonov.day6.model.exception.BookProjectException;

import java.util.ArrayList;
import java.util.List;

public class BookParser {
    private static final String SPLIT_TAGS =
            "(\\s*\\w+=\")|(\"\\s+\\w+=\")|(\")";

    public List<Book> parseBookList(String data) throws BookProjectException {
        List<Book> parsedList = new ArrayList<>();
        String ls = System.getProperty("line.separator");
        String[] lines = data.split(ls);
        for (String line : lines) {
            Book book = parseBook(line);
            parsedList.add(book);
        }
        return parsedList;
    }

    private Book parseBook(String line) throws BookProjectException {
        String[] tags = line.split(SPLIT_TAGS);
        String id = tags[1];
        String title = tags[2];
        String stringAuthors = tags[3];
        String[] authorsSplit = stringAuthors.split(",");
        int year;
        int pages;
        try {
            year = Integer.parseInt(tags[4]);
            pages = Integer.parseInt(tags[5]);
        } catch (NumberFormatException e) {
            throw new BookProjectException("Wrong data!", e.getCause());
        }
        String publishingHouse = tags[6];
        return Book.newBuilder()
                .setId(id)
                .setTitle(title)
                .setAuthors(authorsSplit)
                .setYear(year)
                .setPages(pages)
                .setPublishingHouse(publishingHouse)
                .build();
    }
}
