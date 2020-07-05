package by.kharitonov.day6.controller;

import by.kharitonov.day6.controller.service.ConsoleInputService;
import by.kharitonov.day6.controller.validator.BookValidator;
import by.kharitonov.day6.model.bo.Book;
import by.kharitonov.day6.model.dao.impl.BookListDaoImpl;
import by.kharitonov.day6.model.dao.type.DaoAction;
import by.kharitonov.day6.model.exception.BookProjectException;
import by.kharitonov.day6.view.ConsoleAddBookView;
import by.kharitonov.day6.view.ConsoleStartMenuView;

import java.util.OptionalInt;

public class BookWarehouseController {
    private BookListDaoImpl bookListDao = new BookListDaoImpl();
    private ConsoleInputService inputService = new ConsoleInputService();

    public DaoAction chooseStartAction() {
        ConsoleStartMenuView startMenuViewView = new ConsoleStartMenuView();
        int choice;
        startMenuViewView.printStartMenu();
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
            default:
                break;
        }
    }

    private void addBookActions() {
        ConsoleAddBookView addBookView = new ConsoleAddBookView();
        Book book = createBook(addBookView);
        try {
            bookListDao.addBook(book);
            addBookView.printDaoAddMessage();
        } catch (BookProjectException e) {
            addBookView.printDaoAddMessage(e.getMessage());
        }
    }

    private Book createBook(ConsoleAddBookView addBookView) {
        BookValidator bookValidator = new BookValidator();
        String title;
        OptionalInt authorsNumber = OptionalInt.empty();
        String[] authors;
        OptionalInt year = OptionalInt.empty();
        OptionalInt pages = OptionalInt.empty();
        String publishingHouse;
        Book book;
        addBookView.printBookNameMessage();
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
                .setYear(year.getAsInt())
                .setPages(pages.getAsInt())
                .setAuthors(authors)
                .setTitle(title)
                .build();
        return book;
    }

    private void removeBookActions() {
    }

    private void findBookActions() {
    }

    private void sortBooksActions() {
    }
}
