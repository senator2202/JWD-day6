package by.kharitonov.day6.controller.command.impl;

import by.kharitonov.day6.controller.command.ActionCommand;
import by.kharitonov.day6.model.exception.BookServiceException;
import by.kharitonov.day6.service.BookService;


public class AddCommand implements ActionCommand {
    @Override
    public Object execute(String ... content) {
        BookService service = new BookService();
        try {
            service.addBook(content);
        } catch (BookServiceException e) {
            e.printStackTrace();
        }
        return null;
    }
}
