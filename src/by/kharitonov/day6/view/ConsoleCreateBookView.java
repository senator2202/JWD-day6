package by.kharitonov.day6.view;

public class ConsoleCreateBookView {
    private static final String BOOK_ID_MESSAGE =
            "Enter book id: ";
    private static final String BOOK_NAME_MESSAGE = "Enter book title: ";
    private static final String AUTHORS_NUMBER_MESSAGE =
            "Enter number of authors: ";
    private static final String AUTHORS_NUMBER_ERROR_MESSAGE =
            "Book must have at least 1 author!";
    private static final String AUTHOR_MESSAGE = "Enter %d author: ";
    private static final String YEAR_MESSAGE = "Enter year of publishing: ";
    private static final String YEAR_MISMATCH_MESSAGE = "Wrong year value!";
    private static final String INPUT_MISMATCH_MESSAGE =
            "Input mismatch! Enter integer value!";
    private static final String PAGES_NUMBER_MESSAGE =
            "Enter number of pages: ";
    private static final String PAGES_MISMATCH_MESSAGE = "Wrong pages number!";
    private static final String PUBLISHING_HOUSE_MESSAGE =
            "Enter publishing house: ";

    public void printBookIdMessage() {
        System.out.println(BOOK_ID_MESSAGE);
    }

    public void printBookTitleMessage() {
        System.out.print(BOOK_NAME_MESSAGE);
    }

    public void printAuthorsNumberMessage() {
        System.out.print(AUTHORS_NUMBER_MESSAGE);
    }

    public void printAuthorsNumberErrorMessage() {
        System.out.println(AUTHORS_NUMBER_ERROR_MESSAGE);
    }

    public void printAuthorMessage(int number) {
        System.out.println(String.format(AUTHOR_MESSAGE, number));
    }

    public void printYearMessage() {
        System.out.print(YEAR_MESSAGE);
    }

    public void printYearMismatchMessage() {
        System.out.println(YEAR_MISMATCH_MESSAGE);
    }

    public void printInputMismatchMessage() {
        System.out.println(INPUT_MISMATCH_MESSAGE);
    }

    public void printPagesNumberMessage() {
        System.out.print(PAGES_NUMBER_MESSAGE);
    }

    public void printPagesMismatchMessage() {
        System.out.println(PAGES_MISMATCH_MESSAGE);
    }

    public void printPublishingHouseMessage() {
        System.out.print(PUBLISHING_HOUSE_MESSAGE);
    }
}
