package by.kharitonov.day6.controller;

import by.kharitonov.day6.controller.command.ActionCommand;
import by.kharitonov.day6.controller.command.CommandFactory;
import by.kharitonov.day6.model.entity.CommandResult;
import by.kharitonov.day6.model.exception.CommandException;
import by.kharitonov.day6.view.ViewEmulator;

import java.util.ArrayList;

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
        ActionCommand command;
        CommandResult commandResult;
        try {
            command = factory.getActionCommand(request);
            commandResult = command.execute(content);
        } catch (CommandException e) {
            commandResult = new CommandResult(new ArrayList<>(), e);
        }
        ViewEmulator.setCommandResult(commandResult);
    }
}
