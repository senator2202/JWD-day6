package by.kharitonov.day6.controller;

import by.kharitonov.day6.controller.command.ActionCommand;
import by.kharitonov.day6.controller.command.impl.CommandFactory;
import by.kharitonov.day6.model.entity.CommandResult;
import by.kharitonov.day6.view.ViewEmulator;

public class BookWarehouseController {
    private static BookWarehouseController bookWarehouseControllerInstance;

    private BookWarehouseController() {
    }

    public static BookWarehouseController getInstance() {
        if (bookWarehouseControllerInstance == null) {
            bookWarehouseControllerInstance = new BookWarehouseController();
        }
        return bookWarehouseControllerInstance;
    }

    public void processRequest(String request, String... content) {
        CommandFactory factory = new CommandFactory();
        ActionCommand command = factory.getActionCommand(request);
        CommandResult commandResult = command.execute(content);
        ViewEmulator.setCommandResult(commandResult);
    }
}
