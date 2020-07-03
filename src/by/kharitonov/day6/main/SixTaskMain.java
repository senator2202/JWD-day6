package by.kharitonov.day6.main;

import by.kharitonov.day6.dao.type.DaoAction;
import by.kharitonov.day6.view.UserActionExecutor;

public class SixTaskMain {
    public static void main(String[] args) {
        UserActionExecutor actionExecutor = new UserActionExecutor();
        DaoAction daoAction = actionExecutor.chooseStartAction();
        if (daoAction != DaoAction.NONE) {
            actionExecutor.executeAction(daoAction);
        }
    }
}
