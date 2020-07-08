package by.kharitonov.day6.controller;

import by.kharitonov.day6.controller.type.BookTag;
import by.kharitonov.day6.controller.type.DaoAction;
import by.kharitonov.day6.model.dao.BookListDao;
import by.kharitonov.day6.model.dao.impl.BookListDaoImpl;
import by.kharitonov.day6.model.entity.Book;
import by.kharitonov.day6.model.exception.BookProjectException;
import by.kharitonov.day6.model.service.BookService;
import by.kharitonov.day6.view.ConsoleDaoMessageView;
import by.kharitonov.day6.view.ConsoleFindSortBooksView;
import by.kharitonov.day6.view.ConsoleWarehouseView;
import by.kharitonov.day6.view.service.UserCommunicationService;

import java.util.List;

public class BookWarehouseController {
    private final BookListDao bookListDao = new BookListDaoImpl();

    public DaoAction chooseStartAction() {
        UserCommunicationService comunicator = new UserCommunicationService();
        return comunicator.requestChooseDaoAction();
    }

    public void executeAction(DaoAction daoAction) {
        switch (daoAction) {
            case ADD_BOOK:
                //addBook();
                break;
            case REMOVE_BOOK:
                removeBook();
                break;
            case FIND_BOOK_BY_TAG:
                findBooks();
                break;
            case SORT_BOOKS_BY_TAG:
                sortBooks();
                break;
            case FIND_ALL:
                findAll();
                break;
            default:
                break;
        }
    }

    public void addBook(Book book) {
        BookService service =new BookService();
        try {
            service.addBook(book);
        } catch (BookProjectException e) {
            e.printStackTrace();
        }
        /*ConsoleDaoMessageView daoMessageView = new ConsoleDaoMessageView();
        Book book = createBookConsole();
        try {
            bookListDao.addBook(book);
            daoMessageView.printDaoAddMessage();
        } catch (BookProjectException e) {
            daoMessageView.printDaoMessage(e.getMessage());
        }*/
    }

    private Book createBookConsole() {
        Book book;
        UserCommunicationService communicator = new UserCommunicationService();
        BookTag[] bookTags = BookTag.values();
        String[] tagValues = new String[bookTags.length];
        for (int i = 1; i < tagValues.length; i++) {
            tagValues[i] = communicator.requestEnterTag(bookTags[i]);
        }
        book = Book.newBuilder()
                .setTitle(tagValues[1])
                .setAuthors(tagValues[2])
                .setYear(Integer.parseInt(tagValues[3]))
                .setPages(Integer.parseInt(tagValues[4]))
                .setPublishingHouse(tagValues[5])
                .build();
        return book;
    }

    public void removeBook() {
        ConsoleDaoMessageView daoMessageView = new ConsoleDaoMessageView();
        Book book = createBookConsole();
        try {
            bookListDao.removeBook(book);
            daoMessageView.printDaoRemoveMessage();
        } catch (BookProjectException e) {
            daoMessageView.printDaoMessage(e.getMessage());
        }
    }

    public void findBooks() {
        ConsoleFindSortBooksView booksView = new ConsoleFindSortBooksView();
        UserCommunicationService communicator = new UserCommunicationService();
        BookTag bookTag;
        String tagValue;
        Object findResult;
        bookTag = communicator.requestChooseTag();
        tagValue = communicator.requestEnterTag(bookTag);
        findResult = bookTag.findFunction().apply(tagValue);
        booksView.printFindSortResult(findResult);
    }

    public void sortBooks() {
        ConsoleFindSortBooksView booksView = new ConsoleFindSortBooksView();
        UserCommunicationService communicator = new UserCommunicationService();
        BookTag bookTag;
        List<Book> findResult;
        booksView.printTagMenu();
        bookTag = communicator.requestChooseTag();
        findResult = (List<Book>) bookTag.sortFunction().apply("");
        booksView.printFindSortResult(findResult);
    }

    private void findAll() {
        ConsoleWarehouseView view = new ConsoleWarehouseView();
        List<Book> result = bookListDao.findAll();
        view.printWarehouse(result);
    }
}
