package by.kharitonov.day6.controller.command.impl;

import by.kharitonov.day6.controller.command.ActionCommand;
import by.kharitonov.day6.model.entity.CommandType;

public class CommandFactory {
    public ActionCommand getActionCommand(String request) {
        ActionCommand command;
        try {
            CommandType commandType = CommandType.
                    valueOf(request.toUpperCase());
            command = commandType.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            command = null;
        }
        return command;
    }
}