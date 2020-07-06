package by.kharitonov.day6.model.bo.comparator;

import by.kharitonov.day6.model.bo.Book;

import java.util.Comparator;

public class BookComparatorAuthors implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        if (o1.getAuthorsNumber() == o2.getAuthorsNumber()) {
            return o1.getMainAuthor().compareTo(o2.getMainAuthor());
        }
        return o1.getAuthorsNumber() - o2.getAuthorsNumber();
    }
}
