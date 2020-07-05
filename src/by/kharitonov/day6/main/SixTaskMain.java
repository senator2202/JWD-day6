package by.kharitonov.day6.main;

import by.kharitonov.day6.controller.BookWarehouseController;
import by.kharitonov.day6.model.dao.type.DaoAction;

public class SixTaskMain {
    public static void main(String[] args) {
        BookWarehouseController service = new BookWarehouseController();
        DaoAction daoAction;
        do {
            daoAction = service.chooseStartAction();
            service.executeAction(daoAction);
        } while (daoAction != DaoAction.NONE);
    }
}
