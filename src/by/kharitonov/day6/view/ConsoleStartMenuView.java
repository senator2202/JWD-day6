package by.kharitonov.day6.view;

public class ConsoleStartMenuView {
    private static final String START_MENU =
        "Make a choice:\n" +
                "1 - add book;\n" +
                "2 - remove book;\n" +
                "3 - find book by tag;\n" +
                "4 - sort books by tag;\n" +
                "0 - exit.";

    public void printStartMenu() {
        System.out.println(START_MENU);
    }
}
