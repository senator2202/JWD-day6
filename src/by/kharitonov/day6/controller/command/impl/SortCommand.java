package by.kharitonov.day6.controller.command.impl;

import by.kharitonov.day6.controller.command.ActionCommand;
import by.kharitonov.day6.model.entity.Book;
import by.kharitonov.day6.model.entity.CommandResult;
import by.kharitonov.day6.model.exception.BookServiceException;
import by.kharitonov.day6.service.BookService;

import java.util.ArrayList;
import java.util.List;

public class SortCommand implements ActionCommand {
    @Override
    public CommandResult execute(String... content) {
        BookService service = new BookService();
        CommandResult commandResult;
        try {
            List<Book> bookList = service.sortBooks(content);
            commandResult = new CommandResult(bookList, null);
        } catch (BookServiceException e) {
            List<Book> bookList = new ArrayList<>();
            commandResult = new CommandResult(bookList, e);
        }
        return commandResult;
    }
}
