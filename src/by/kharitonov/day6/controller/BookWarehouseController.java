package by.kharitonov.day6.controller;

import by.kharitonov.day6.controller.service.ConsoleInputService;
import by.kharitonov.day6.controller.type.DaoAction;
import by.kharitonov.day6.controller.validator.BookValidator;
import by.kharitonov.day6.model.bo.Book;
import by.kharitonov.day6.model.dao.BookListDao;
import by.kharitonov.day6.model.dao.impl.BookListDaoImpl;
import by.kharitonov.day6.model.exception.BookProjectException;
import by.kharitonov.day6.view.ConsoleCreateBookView;
import by.kharitonov.day6.view.ConsoleDaoMessageView;
import by.kharitonov.day6.view.ConsoleStartMenuView;
import by.kharitonov.day6.view.ConsoleWarehouseView;

import java.util.OptionalInt;

public class BookWarehouseController {
    private BookListDao bookListDao = new BookListDaoImpl();
    private ConsoleInputService inputService = new ConsoleInputService();

    public DaoAction chooseStartAction() {
        ConsoleStartMenuView startMenuView = new ConsoleStartMenuView();
        int choice;
        startMenuView.printStartMenu();
        choice = inputService.readStartChoice();
        return DaoAction.values()[choice];
    }

    public void executeAction(DaoAction daoAction) {
        switch (daoAction) {
            case ADD_BOOK:
                addBookActions();
                break;
            case REMOVE_BOOK:
                removeBookActions();
                break;
            case FIND_BOOK_BY_TAG:
                findBookActions();
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

    private void addBookActions() {
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
        while (authorsNumber.isEmpty()) {
            addBookView.printAuthorsNumberMessage();
            authorsNumber = inputService.readInt();
            if (authorsNumber.isEmpty()) {
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
        while (year.isEmpty()) {
            addBookView.printYearMessage();
            year = inputService.readInt();
            if (year.isEmpty()) {
                addBookView.printInputMismatchMessage();
                continue;
            }
            if (!bookValidator.validateYear(year.getAsInt())) {
                addBookView.printYearMismatchMessage();
                year = OptionalInt.empty();
            }
        }
        while (pages.isEmpty()) {
            addBookView.printPagesNumberMessage();
            pages = inputService.readInt();
            if (pages.isEmpty()) {
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

    private void removeBookActions() {
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

    private void findBookActions() {
    }

    private void sortBooksActions() {
    }

    private void viewAllActions() {
        ConsoleWarehouseView view = new ConsoleWarehouseView();
        String result = bookListDao.getAll();
        view.printWarehouse(result);
    }
}
