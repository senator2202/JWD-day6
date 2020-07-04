package by.kharitonov.day6.controller;

import by.kharitonov.day6.controller.service.ConsoleInputService;
import by.kharitonov.day6.model.bo.Book;
import by.kharitonov.day6.model.dao.impl.BookListDaoImpl;
import by.kharitonov.day6.model.dao.type.DaoAction;
import by.kharitonov.day6.model.exception.BookProjectException;
import by.kharitonov.day6.view.ConsoleOutputService;

import java.util.OptionalInt;

public class BookWarehouseController {
    private BookListDaoImpl bookListDao = BookListDaoImpl.getInstance();
    private ConsoleOutputService outputService = new ConsoleOutputService();
    private ConsoleInputService inputService = new ConsoleInputService();

    public DaoAction chooseStartAction() {
        ConsoleOutputService outputService = new ConsoleOutputService();
        ConsoleInputService inputService = new ConsoleInputService();
        int choice;
        outputService.printStartMenu();
        choice = inputService.readStartChoice();
        return DaoAction.values()[choice];
    }

    public void executeAction(DaoAction daoAction) {
        Book book = null;
        switch (daoAction) {
            case ADD_BOOK:
                addBookActions();
                break;
            case REMOVE_BOOK:
                book = removeBookPrepareActions();
                break;
            case FIND_BOOK_BY_TAG:
                book = findBookPrepareActions();
                break;
            case SORT_BOOKS_BY_TAG:
                book = sortBooksPrepareActions();
                break;
            default:
                break;
        }
    }

    private void addBookActions() {
        Book book = createBook();
        try {
            bookListDao.addBook(book);
            outputService.printDaoAddMessage();
        } catch (BookProjectException e) {
            outputService.printDaoAddMessage(e.getMessage());
        }
    }

    private Book createBook() {
        String title;
        OptionalInt authorsNumber = OptionalInt.empty();
        String[] authors;
        OptionalInt year = OptionalInt.empty();
        OptionalInt pages = OptionalInt.empty();
        String publishingHouse;
        Book book;
        outputService.printBookNameMessage();
        title = inputService.readString();
        while (authorsNumber.isEmpty()) {
            outputService.printAuthorsNumberMessage();
            authorsNumber = inputService.readInt();
            if (authorsNumber.isEmpty()) {
                outputService.printInputMismatchMessage();
                continue;
            }
            if (authorsNumber.getAsInt() <= 0) {
                outputService.printAuthorsNumberErrorMessage();
                authorsNumber = OptionalInt.empty();
            }
        }
        authors = new String[authorsNumber.getAsInt()];
        for (int i = 0; i < authorsNumber.getAsInt(); i++) {
            outputService.printAuthorMessage(i + 1);
            authors[i] = inputService.readString();
        }
        while (year.isEmpty()) {
            outputService.printYearMessage();
            year = inputService.readInt();
            if (year.isEmpty()) {
                outputService.printInputMismatchMessage();
            }
        }
        while (pages.isEmpty()) {
            outputService.printPagesNumberMessage();
            pages = inputService.readInt();
            if (pages.isEmpty()) {
                outputService.printInputMismatchMessage();
            }
        }
        outputService.printPublishingHouseMessage();
        publishingHouse = inputService.readString();
        book = Book.newBuilder().setPublishingHouse(publishingHouse)
                .setYear(year.getAsInt())
                .setPages(pages.getAsInt())
                .setAuthors(authors)
                .setTitle(title)
                .build();
        return book;
    }

    private Book removeBookPrepareActions() {
        return null;
    }

    private Book findBookPrepareActions() {
        return null;
    }

    private Book sortBooksPrepareActions() {
        return null;
    }
}
