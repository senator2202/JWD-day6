package by.kharitonov.day6.view;

import by.kharitonov.day6.bo.Book;
import by.kharitonov.day6.dao.type.DaoAction;

import java.util.OptionalInt;

public class UserActionExecutor {

    public DaoAction chooseStartAction() {
        ConsoleInputService inputService = new ConsoleInputService();
        ConsoleOutputService outputService = new ConsoleOutputService();
        int choice;
        outputService.printStartMenu();
        choice = inputService.readStartChoice();
        return DaoAction.values()[choice];
    }

    public Book executeAction(DaoAction daoAction) {
        Book book = null;
        switch (daoAction) {
            case ADD_BOOK:
                book = addBookPrepareActions();
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
        return book;
    }

    private Book addBookPrepareActions() {
        ConsoleInputService inputService = new ConsoleInputService();
        ConsoleOutputService outputService = new ConsoleOutputService();
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
