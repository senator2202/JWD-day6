package by.kharitonov.day6.controller;

import by.kharitonov.day6.controller.service.ConsoleInputService;
import by.kharitonov.day6.controller.type.BookTag;
import by.kharitonov.day6.controller.type.DaoAction;
import by.kharitonov.day6.controller.validator.BookValidator;
import by.kharitonov.day6.model.bo.Book;
import by.kharitonov.day6.model.dao.BookListDao;
import by.kharitonov.day6.model.dao.impl.BookListDaoImpl;
import by.kharitonov.day6.model.exception.BookProjectException;
import by.kharitonov.day6.view.*;

import java.util.List;
import java.util.OptionalInt;

public class BookWarehouseController {
    private BookListDao bookListDao = new BookListDaoImpl();
    private ConsoleInputService inputService = new ConsoleInputService();

    public DaoAction chooseStartAction() {
        ConsoleStartMenuView startMenuView = new ConsoleStartMenuView();
        int choice;
        startMenuView.printStartMenu();
        choice = inputService.readMenuChoice(0, 5);
        return DaoAction.values()[choice];
    }

    public void executeAction(DaoAction daoAction) {
        switch (daoAction) {
            case ADD_BOOK:
                addBook();
                break;
            case REMOVE_BOOK:
                removeBook();
                break;
            case FIND_BOOK_BY_TAG:
                findBook();
                break;
            case SORT_BOOKS_BY_TAG:
                sortBooksActions();
                break;
            case VIEW_ALL:
                viewAllActions();
                break;
            default:
                break;
        }
    }

    public void addBook() {
        ConsoleCreateBookView createBookView = new ConsoleCreateBookView();
        ConsoleDaoMessageView daoMessageView = new ConsoleDaoMessageView();
        Book book = createBookConsole(createBookView);
        try {
            bookListDao.addBook(book);
            daoMessageView.printDaoAddMessage();
        } catch (BookProjectException e) {
            daoMessageView.printDaoMessage(e.getMessage());
        }
    }

    private Book createBookConsole(ConsoleCreateBookView addBookView) {
        BookValidator bookValidator = new BookValidator();
        String id;
        String title;
        OptionalInt authorsNumber = OptionalInt.empty();
        String[] authors;
        OptionalInt year = OptionalInt.empty();
        OptionalInt pages = OptionalInt.empty();
        String publishingHouse;
        Book book;
        addBookView.printBookIdMessage();
        id = inputService.readString();
        addBookView.printBookTitleMessage();
        title = inputService.readString();
        while (!authorsNumber.isPresent()) {
            addBookView.printAuthorsNumberMessage();
            authorsNumber = inputService.readInt();
            if (!authorsNumber.isPresent()) {
                addBookView.printInputMismatchMessage();
                continue;
            }
            if (authorsNumber.getAsInt() <= 0) {
                addBookView.printAuthorsNumberErrorMessage();
                authorsNumber = OptionalInt.empty();
            }
        }
        authors = new String[authorsNumber.getAsInt()];
        for (int i = 0; i < authorsNumber.getAsInt(); i++) {
            addBookView.printAuthorMessage(i + 1);
            authors[i] = inputService.readString();
        }
        while (!year.isPresent()) {
            addBookView.printYearMessage();
            year = inputService.readInt();
            if (!year.isPresent()) {
                addBookView.printInputMismatchMessage();
                continue;
            }
            if (!bookValidator.validateYear(year.getAsInt())) {
                addBookView.printYearMismatchMessage();
                year = OptionalInt.empty();
            }
        }
        while (!pages.isPresent()) {
            addBookView.printPagesNumberMessage();
            pages = inputService.readInt();
            if (!pages.isPresent()) {
                addBookView.printInputMismatchMessage();
                continue;
            }
            if (!bookValidator.validatePages(pages.getAsInt())) {
                addBookView.printPagesMismatchMessage();
                pages = OptionalInt.empty();
            }
        }
        addBookView.printPublishingHouseMessage();
        publishingHouse = inputService.readString();
        book = Book.newBuilder().setPublishingHouse(publishingHouse)
                .setId(id)
                .setYear(year.getAsInt())
                .setPages(pages.getAsInt())
                .setAuthors(authors)
                .setTitle(title)
                .build();
        return book;
    }

    public void removeBook() {
        ConsoleCreateBookView createBookView = new ConsoleCreateBookView();
        ConsoleDaoMessageView daoMessageView = new ConsoleDaoMessageView();
        Book book = createBookConsole(createBookView);
        try {
            bookListDao.removeBook(book);
            daoMessageView.printDaoRemoveMessage();
        } catch (BookProjectException e) {
            daoMessageView.printDaoMessage(e.getMessage());
        }
    }

    public void findBook() {
        ConsoleFindBooksView findBooksView = new ConsoleFindBooksView();
        ConsoleDaoMessageView daoMessageView = new ConsoleDaoMessageView();
        BookTag bookTag;
        String tagValue;
        List<Book> findResult;
        findBooksView.printFindMenu();
        bookTag = chooseFindTag();
        findBooksView.printEnterTagMessage(bookTag);
        tagValue = inputService.readString();
        try {
            findResult = bookListDao.findBooksByTag(bookTag,tagValue);
            findBooksView.printFindResult(findResult);
        } catch (BookProjectException e) {
            daoMessageView.printDaoMessage(e.getMessage());
        }
    }

    private BookTag chooseFindTag() {
        int choice;
        choice = inputService.readMenuChoice(1, 6);
        return BookTag.values()[choice - 1];
    }

    private void sortBooksActions() {
    }

    private void viewAllActions() {
        ConsoleWarehouseView view = new ConsoleWarehouseView();
        String result = bookListDao.getAll();
        view.printWarehouse(result);
    }
}
