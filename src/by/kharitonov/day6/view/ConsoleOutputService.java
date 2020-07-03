package by.kharitonov.day6.view;

public class ConsoleOutputService {
    private static final String START_MENU =
            "Make a choice:\n" +
                    "1 - add book;\n" +
                    "2 - remove book;\n" +
                    "3 - find book by tag;\n" +
                    "4 - sort books by tag;\n" +
                    "0 - exit.";
    private static final String BOOK_NAME_MESSAGE = "Enter book title: ";
    private static final String AUTHORS_NUMBER_MESSAGE =
            "Enter number of authors: ";
    private static final String AUTHORS_NUMBER_ERROR_MESSAGE =
            "Book must have at least 1 author!";
    private static final String AUTHOR_MESSAGE = "Enter %d author: ";
    private static final String YEAR_MESSAGE = "Enter year of publishing: ";
    private static final String INPUT_MISMATCH_MESSAGE =
            "Input mismatch! Enter integer value!";
    private static final String PAGES_NUMBER_MESSAGE =
            "Enter number of pages: ";
    private static final String PUBLISHING_HOUSE_MESSAGE =
            "Enter publishing house: ";

    ConsoleOutputService() {
    }

    void printStartMenu() {
        System.out.println(START_MENU);
    }

    void printBookNameMessage() {
        System.out.print(BOOK_NAME_MESSAGE);
    }

    void printAuthorsNumberMessage() {
        System.out.print(AUTHORS_NUMBER_MESSAGE);
    }

    void printAuthorsNumberErrorMessage() {
        System.out.println(AUTHORS_NUMBER_ERROR_MESSAGE);
    }

    void printAuthorMessage(int number) {
        System.out.printf(String.format(AUTHOR_MESSAGE, number));
    }

    void printYearMessage() {
        System.out.print(YEAR_MESSAGE);
    }

    void printInputMismatchMessage() {
        System.out.println(INPUT_MISMATCH_MESSAGE);
    }

    void printPagesNumberMessage() {
        System.out.print(PAGES_NUMBER_MESSAGE);
    }

    void printPublishingHouseMessage() {
        System.out.print(PUBLISHING_HOUSE_MESSAGE);
    }
}
