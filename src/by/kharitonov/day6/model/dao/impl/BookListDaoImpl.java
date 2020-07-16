package by.kharitonov.day6.model.dao.impl;

import by.kharitonov.day6.model.dao.BookListDao;
import by.kharitonov.day6.model.entity.Book;
import by.kharitonov.day6.model.entity.BookWarehouse;
import by.kharitonov.day6.model.exception.DaoException;

import java.util.ArrayList;
import java.util.List;

public class BookListDaoImpl implements BookListDao {
    private final BookWarehouse warehouse;

    public BookListDaoImpl() {
        warehouse = BookWarehouse.getInstance();
    }

    @Override
    public void addBook(Book book) throws DaoException {
        List<Book> allBooks = warehouse.findAll();
        if (allBooks.contains(book)) {
            throw new DaoException("This book already exists!");
        }
        if (warehouse.isFull()) {
            throw new DaoException("Warehouse is full!");
        }
        warehouse.add(book);
    }

    @Override
    public void removeBook(Book removingBook)
            throws DaoException {
        List<Book> allBooks = warehouse.findAll();
        boolean result = false;
        for (Book book : allBooks) {
            if (isSimilar(book, removingBook)) {
                warehouse.remove(book);
                result = true;
            }
        }
        if (!result) {
            throw new DaoException("Such book doesn't exist!");
        }
    }

    private boolean isSimilar(Book book, Book removingBook) {
        return (book.getTitle().equals(removingBook.getTitle()) &&
                book.getAuthors().equals(removingBook.getAuthors()) &&
                book.getYear() == removingBook.getYear() &&
                book.getPages() == removingBook.getPages() &&
                book.getPublishingHouse()
                        .equals(removingBook.getPublishingHouse()));
    }

    @Override
    public List<Book> findBookById(String id) {
        List<Book> allBooks = warehouse.findAll();
        List<Book> searchedBook = new ArrayList<>();
        for (Book book : allBooks) {
            if (book.getId().equals(id)) {
                searchedBook.add(book);
                break;
            }
        }
        return searchedBook;
    }

    @Override
    public List<Book> findBooksByTitle(String title) {
        List<Book> allBooks = warehouse.findAll();
        List<Book> resultList = new ArrayList<>();
        for (Book book : allBooks) {
            if (book.getTitle().equals(title)) {
                resultList.add(book);
            }
        }
        return resultList;
    }

    @Override
    public List<Book> findBooksByAuthor(String author) {
        List<Book> allBooks = warehouse.findAll();
        List<Book> resultList = new ArrayList<>();
        for (Book book : allBooks) {
            if (book.getAuthors().contains(author)) {
                resultList.add(book);
            }
        }
        return resultList;
    }

    @Override
    public List<Book> findBooksByYear(String year) {
        List<Book> allBooks = warehouse.findAll();
        List<Book> resultList = new ArrayList<>();
        for (Book book : allBooks) {
            if (String.valueOf(book.getYear()).equals(year)) {
                resultList.add(book);
            }
        }
        return resultList;
    }

    @Override
    public List<Book> findBooksByPages(String pages) {
        List<Book> allBooks = warehouse.findAll();
        List<Book> resultList = new ArrayList<>();
        for (Book book : allBooks) {
            if (String.valueOf(book.getPages()).equals(pages)) {
                resultList.add(book);
            }
        }
        return resultList;
    }

    @Override
    public List<Book> findBooksByPublishingHouse(String publishingHouse) {
        List<Book> allBooks = warehouse.findAll();
        List<Book> resultList = new ArrayList<>();
        for (Book book : allBooks) {
            if (book.getPublishingHouse().equals(publishingHouse)) {
                resultList.add(book);
            }
        }
        return resultList;
    }

    @Override
    public List<Book> sortBooksById() {
        List<Book> allBooks = warehouse.findAll();
        List<Book> sortedList = new ArrayList<>(allBooks);
        sortedList.sort(new Book.BookIdComparator());
        return sortedList;
    }

    @Override
    public List<Book> sortBooksByTitle() {
        List<Book> allBooks = warehouse.findAll();
        List<Book> sortedList = new ArrayList<>(allBooks);
        sortedList.sort(new Book.BookTitleComparator());
        return sortedList;
    }

    @Override
    public List<Book> sortBooksByAuthors() {
        List<Book> allBooks = warehouse.findAll();
        List<Book> sortedList = new ArrayList<>(allBooks);
        sortedList.sort(new Book.BookAuthorsComparator());
        return sortedList;
    }

    @Override
    public List<Book> sortBooksByYear() {
        List<Book> allBooks = warehouse.findAll();
        List<Book> sortedList = new ArrayList<>(allBooks);
        sortedList.sort(new Book.BookYearComparator());
        return sortedList;
    }

    @Override
    public List<Book> sortBooksByPages() {
        List<Book> allBooks = warehouse.findAll();
        List<Book> sortedList = new ArrayList<>(allBooks);
        sortedList.sort(new Book.BookPagesComparator());
        return sortedList;
    }

    @Override
    public List<Book> sortBooksByPublishingHouse() {
        List<Book> allBooks = warehouse.findAll();
        List<Book> sortedList = new ArrayList<>(allBooks);
        sortedList.sort(new Book.BookPublishingHouseComparator());
        return sortedList;
    }
}
