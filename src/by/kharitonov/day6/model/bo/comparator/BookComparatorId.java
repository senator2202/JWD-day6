package by.kharitonov.day6.model.bo.comparator;

import by.kharitonov.day6.model.bo.Book;

import java.util.Comparator;

public class BookComparatorId implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        return o1.getId().compareTo(o2.getId());
    }
}
