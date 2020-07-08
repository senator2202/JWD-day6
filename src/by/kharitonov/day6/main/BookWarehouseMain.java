package by.kharitonov.day6.main;

import by.kharitonov.day6.view.ConsoleStartMenuView;

public class BookWarehouseMain {
    public static void main(String[] args) {
        /*BookWarehouseController service = new BookWarehouseController();
        DaoAction daoAction;
        do {
            daoAction = service.chooseStartAction();
            service.executeAction(daoAction);
        } while (daoAction != DaoAction.NONE);*/
        ConsoleStartMenuView view = new ConsoleStartMenuView();
        view.startView();
    }
}
