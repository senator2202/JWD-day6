package by.kharitonov.day6.view.io;

import by.kharitonov.day6.model.type.BookTag;
import by.kharitonov.day6.model.type.DaoAction;

public class UserCommunicationService {
    private static final int MENU_START = 0;
    private static final int MENU_END = 5;
    private static final int TAG_START = 1;
    private static final int TAG_END = 6;

    public String requestEnterTag(BookTag bookTag) {
        ConsoleOutputService outputService = new ConsoleOutputService();
        ConsoleInputService inputService = new ConsoleInputService();
        String tagValue;
        outputService.printEnterTagMessage(bookTag);
        tagValue = inputService.readString();
        return tagValue;
    }

    public DaoAction requestChooseStartAction() {
        ConsoleOutputService outputService = new ConsoleOutputService();
        ConsoleInputService inputService = new ConsoleInputService();
        int choice;
        outputService.printStartMenu();
        choice = inputService.readMenuChoice(MENU_START, MENU_END);
        return DaoAction.values()[choice];
    }

    public BookTag requestChooseTag() {
        ConsoleOutputService outputService = new ConsoleOutputService();
        ConsoleInputService inputService = new ConsoleInputService();
        int choice;
        outputService.printTagMenu();
        choice = inputService.readMenuChoice(TAG_START, TAG_END);
        return BookTag.values()[choice - 1];
    }
}
