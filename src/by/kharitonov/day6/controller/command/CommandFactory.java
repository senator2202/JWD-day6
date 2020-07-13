package by.kharitonov.day6.controller.command;

import by.kharitonov.day6.model.exception.CommandException;
import by.kharitonov.day6.model.type.CommandType;

public class CommandFactory {
    public ActionCommand getActionCommand(String request)
            throws CommandException {
        ActionCommand command;
        try {
            CommandType commandType = CommandType.
                    valueOf(request.toUpperCase());
            command = commandType.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            throw new CommandException("Invalid command!", e.getCause());
        }
        return command;
    }
}