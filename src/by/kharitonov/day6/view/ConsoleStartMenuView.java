package by.kharitonov.day6.view;

import by.kharitonov.day6.controller.type.DaoAction;
import by.kharitonov.day6.view.service.UserCommunicationService;

public class ConsoleStartMenuView {
    public void startView() {
        UserCommunicationService communicator = new UserCommunicationService();
        DaoAction action;
        action = communicator.requestChooseDaoAction();
        switch (action) {
            case NONE:
                break;
            case ADD_BOOK:
                break;
            case REMOVE_BOOK:
                break;
            case FIND_BOOK_BY_TAG:
                break;
            case SORT_BOOKS_BY_TAG:
                break;
            case FIND_ALL:
                break;
        }
    }
}
