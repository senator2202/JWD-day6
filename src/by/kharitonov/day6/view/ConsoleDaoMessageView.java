package by.kharitonov.day6.view;

public class ConsoleDaoMessageView {
    private static final String DAO_ADD_MESSAGE =
            "Book was successfully added to warehouse!";
    private static final String DAO_REMOVE_MESSAGE =
            "Book was successfully removed!";

    public void printDaoMessage(String message) {
        System.out.println(message);
    }

    public void printDaoAddMessage() {
        System.out.println(DAO_ADD_MESSAGE);
    }

    public void printDaoRemoveMessage() {
        System.out.println(DAO_REMOVE_MESSAGE);
    }
}
