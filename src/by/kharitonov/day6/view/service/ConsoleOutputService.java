package by.kharitonov.day6.view.service;

import by.kharitonov.day6.controller.type.BookTag;

public class ConsoleOutputService {
    private static final String START_MENU =
            "Make a choice:\n" +
                    "1 - add book;\n" +
                    "2 - remove book;\n" +
                    "3 - find book by tag;\n" +
                    "4 - sort books by tag;\n" +
                    "5 - view all books;\n" +
                    "0 - exit.\n";
    private static final String INPUT_ERROR_MESSAGE =
            "Input mismatch! Enter correct value!";
    private static final String TAG_MESSAGE = "Enter %s:";

    public void printStartMenu() {
        System.out.println(START_MENU);
    }

    public void printInputErrorMessage() {
        System.out.println(INPUT_ERROR_MESSAGE);
    }

    public void printEnterTagMessage(BookTag bookTag) {
        System.out.println(String.format(TAG_MESSAGE, bookTag.getTagName()));
    }
}
