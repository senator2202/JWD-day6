package by.kharitonov.day6.controller.type;

public enum DaoAction {
    NONE(0),
    ADD_BOOK(1),
    REMOVE_BOOK(2),
    FIND_BOOK_BY_TAG(3),
    SORT_BOOKS_BY_TAG(4),
    VIEW_ALL(5);

    private final int index;

    DaoAction(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
