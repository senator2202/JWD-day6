package by.kharitonov.day6.view.io;

import by.kharitonov.day6.model.type.BookTag;

public class ConsoleOutputService {
    private static final String START_MENU =
            "Make a choice:\n" +
                    "1 - add book;\n" +
                    "2 - remove book;\n" +
                    "3 - find book by tag;\n" +
                    "4 - sort books by tag;\n" +
                    "5 - view all books;\n" +
                    "0 - exit.\n";
    private static final String TAG_MESSAGE = "Enter %s:";
    private static final String TAG_MENU =
            "Choose a tag to find by:\n" +
                    "1 - id;\n" +
                    "2 - title;\n" +
                    "3 - author;\n" +
                    "4 - year;\n" +
                    "5 - pages;\n" +
                    "6 - publishing house.\n";

    public void printStartMenu() {
        System.out.println(START_MENU);
    }

    public void printEnterTagMessage(BookTag bookTag) {
        System.out.println(String.format(TAG_MESSAGE, bookTag.getTagName()));
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printTagMenu() {
        System.out.println(TAG_MENU);
    }
}
