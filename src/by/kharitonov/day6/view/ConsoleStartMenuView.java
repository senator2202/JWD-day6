package by.kharitonov.day6.view;

import by.kharitonov.day6.model.type.DaoAction;
import by.kharitonov.day6.view.io.UserCommunicationService;

public class ConsoleStartMenuView extends ConsoleView {
    @Override
    public void startView() {
        UserCommunicationService communicator = new UserCommunicationService();
        DaoAction action;
        do {
            action = communicator.requestChooseStartAction();
            switch (action) {
                case NONE:
                    break;
                case ADD_BOOK:
                    new ConsoleAddBookView().startView();
                    break;
                case REMOVE_BOOK:
                    new ConsoleRemoveBookView().startView();
                    break;
                case FIND_BOOK_BY_TAG:
                    new ConsoleFindBooksView().startView();
                    break;
                case SORT_BOOKS_BY_TAG:
                    new ConsoleSortBooksView().startView();
                    break;
                case FIND_ALL:
                    new ConsoleAllView().startView();
                    break;
            }
        } while (action != DaoAction.NONE);
    }
}
