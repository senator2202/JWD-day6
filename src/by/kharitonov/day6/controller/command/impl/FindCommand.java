package by.kharitonov.day6.controller.command.impl;

import by.kharitonov.day6.controller.command.ActionCommand;
import by.kharitonov.day6.controller.entity.CommandResult;
import by.kharitonov.day6.service.BookService;

public class FindCommand implements ActionCommand {
    @Override
    public CommandResult execute(String... content) {
        BookService service = new BookService();
        //List<Book> foundBooks = service.findBooks(content);
        return null;
    }
}
