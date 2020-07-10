package by.kharitonov.day6.controller.command.impl;

import by.kharitonov.day6.controller.command.ActionCommand;
import by.kharitonov.day6.controller.entity.CommandResult;
import by.kharitonov.day6.model.exception.BookServiceException;
import by.kharitonov.day6.service.BookService;

public class RemoveCommand implements ActionCommand {
    @Override
    public CommandResult execute(String... content) {
        BookService service = new BookService();
        try {
            service.removeBook(content);
        } catch (BookServiceException e) {
            e.printStackTrace();
        }
        return null;
    }
}
