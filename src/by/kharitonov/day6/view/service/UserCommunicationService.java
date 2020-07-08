package by.kharitonov.day6.view.service;

import by.kharitonov.day6.controller.type.BookTag;
import by.kharitonov.day6.controller.type.DaoAction;
import by.kharitonov.day6.model.validator.BookValidator;
import by.kharitonov.day6.view.ConsoleAddBookView;
import by.kharitonov.day6.view.ConsoleFindSortBooksView;

public class UserCommunicationService {
    private static final int MENU_START = 0;
    private static final int MENU_END = 5;
    private static final int TAG_START = 1;
    private static final int TAG_END = 6;

    public String requestEnterTag(BookTag bookTag) {
        ConsoleOutputService outputService = new ConsoleOutputService();
        ConsoleInputService inputService = new ConsoleInputService();
        BookValidator validator = new BookValidator();
        String tagvalue;
        boolean validateFlag;
        do {
            outputService.printEnterTagMessage(bookTag);
            tagvalue = inputService.readString();
            validateFlag = validator.validateTag(bookTag, tagvalue);
            if (!validateFlag) {
                outputService.printInputErrorMessage();
            }
        } while (!validateFlag);
        return tagvalue;
    }

    public DaoAction requestChooseDaoAction() {
        ConsoleOutputService outputService = new ConsoleOutputService();
        ConsoleInputService inputService = new ConsoleInputService();
        int choice;
        outputService.printStartMenu();
        choice = inputService.readMenuChoice(MENU_START, MENU_END);
        return DaoAction.values()[choice];
    }

    public BookTag requestChooseTag() {
        ConsoleFindSortBooksView booksView = new ConsoleFindSortBooksView();
        ConsoleInputService inputService = new ConsoleInputService();
        int choice;
        booksView.printTagMenu();
        choice = inputService.readMenuChoice(TAG_START, TAG_END);
        return BookTag.values()[choice - 1];
    }
}
