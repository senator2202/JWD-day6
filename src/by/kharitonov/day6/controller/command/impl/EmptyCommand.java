package by.kharitonov.day6.controller.command.impl;

import by.kharitonov.day6.controller.command.ActionCommand;
import by.kharitonov.day6.controller.exception.CommandException;
import by.kharitonov.day6.model.entity.Book;
import by.kharitonov.day6.model.entity.CommandResult;
import by.kharitonov.day6.service.BookService;
import by.kharitonov.day6.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class EmptyCommand implements ActionCommand {
    @Override
    public CommandResult execute(String... content) {
        CommandResult commandResult;
        List<Book> list = new ArrayList<>();
        CommandException exception = new CommandException("Invalid command!");
        commandResult = new CommandResult(list,exception);
        return commandResult;
    }
}
