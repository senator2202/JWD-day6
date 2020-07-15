package test.kharitonov.day6.controller.command.impl;

import by.kharitonov.day6.controller.command.impl.EmptyCommand;
import by.kharitonov.day6.controller.exception.CommandException;
import by.kharitonov.day6.model.entity.Book;
import by.kharitonov.day6.model.entity.CommandResult;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class EmptyCommandTest {
    private EmptyCommand command = new EmptyCommand();

    @Test
    public void testExecute() {
        CommandResult actualResult = command.execute("no matter what",
                "no matter how many");
        List<Book> list = new ArrayList<>();
        CommandException exception = new CommandException("Invalid command!");
        CommandResult expectedResult = new CommandResult(list, exception);
        Assert.assertEquals(actualResult, expectedResult);
    }
}