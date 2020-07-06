package by.kharitonov.day6.model.bo.comparator;

import by.kharitonov.day6.model.bo.Book;

import java.util.Comparator;

public class BookComparatorYear implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        return o1.getYear() - o2.getYear();
    }
}
