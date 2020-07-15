package by.kharitonov.day6.controller;

import by.kharitonov.day6.controller.command.ActionCommand;
import by.kharitonov.day6.model.entity.CommandResult;
import by.kharitonov.day6.model.type.CommandType;
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
        ActionCommand command;
        CommandResult commandResult;
        try {
            command = CommandType
                    .valueOf(request.toUpperCase())
                    .getCurrentCommand();
        } catch (IllegalArgumentException e) {
            command = CommandType.EMPTY.getCurrentCommand();
        }
        commandResult = command.execute(content);
        ViewEmulator.setCommandResult(commandResult);
    }
}
