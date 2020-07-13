package by.kharitonov.day6.controller.command;

import by.kharitonov.day6.model.exception.ControllerException;
import by.kharitonov.day6.model.type.CommandType;

public class CommandFactory {
    public ActionCommand getActionCommand(String request)
            throws ControllerException {
        ActionCommand command;
        try {
            CommandType commandType = CommandType.
                    valueOf(request.toUpperCase());
            command = commandType.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            throw new ControllerException("Invalid command!", e.getCause());
        }
        return command;
    }
}