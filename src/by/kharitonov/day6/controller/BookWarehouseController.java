package by.kharitonov.day6.controller;

import by.kharitonov.day6.controller.command.ActionCommand;
import by.kharitonov.day6.controller.command.impl.CommandFactory;
import by.kharitonov.day6.controller.entity.CommandResult;
import by.kharitonov.day6.view.ViewEmulator;

public class BookWarehouseController {
    public void processRequest(String request, String... content) {
        CommandFactory factory = new CommandFactory();
        ActionCommand command = factory.getActionCommand(request);
        CommandResult commandResult = command.execute(content);
        ViewEmulator.setCommandResult(commandResult);
    }
}
