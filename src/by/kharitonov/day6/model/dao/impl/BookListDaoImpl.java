package by.kharitonov.day6.model.dao.impl;

import by.kharitonov.day6.model.dao.BookListDao;
import by.kharitonov.day6.model.entity.Book;
import by.kharitonov.day6.model.entity.BookWarehouse;
import by.kharitonov.day6.model.exception.BookProjectException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookListDaoImpl implements BookListDao {
    private final BookWarehouse warehouse;

    public BookListDaoImpl() {
        warehouse = BookWarehouse.getInstance();
    }

    @Override
    public void addBook(Book book) throws BookProjectException {
        warehouse.add(book);
    }

    @Override
    public void removeBook(Book book) throws BookProjectException {
        warehouse.remove(book);
    }

    @Override
    public Optional<Book> findBookById(String id) {
        List<Book> allBooks = warehouse.findAll();
        Optional<Book> optionalBook = Optional.empty();
        for (Book book : allBooks) {
            if (book.getId().equals(id)) {
                optionalBook = Optional.of(book);
                break;
            }
        }
        return optionalBook;
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
    public List<Book> findAll() {
        return warehouse.findAll();
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
