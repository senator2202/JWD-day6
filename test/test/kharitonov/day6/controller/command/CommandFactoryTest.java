package test.kharitonov.day6.controller.command;

import by.kharitonov.day6.controller.command.ActionCommand;
import by.kharitonov.day6.controller.command.CommandFactory;
import by.kharitonov.day6.controller.command.impl.AddCommand;
import by.kharitonov.day6.controller.command.impl.FindCommand;
import by.kharitonov.day6.controller.command.impl.RemoveCommand;
import by.kharitonov.day6.controller.command.impl.SortCommand;
import by.kharitonov.day6.model.exception.ControllerException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CommandFactoryTest {
    private final CommandFactory factory = new CommandFactory();

    @DataProvider(name = "dataGetActionCommand")
    @Test
    public Object[][] dataGetActionCommand() {
        return new Object[][]{
                {"add", new AddCommand()},
                {"remove", new RemoveCommand()},
                {"find", new FindCommand()},
                {"sort", new SortCommand()}
        };
    }

    @Parameters({"request", "expectedCommand"})
    @Test(dataProvider = "dataGetActionCommand")
    public void testGetActionCommand(String request,
                                     ActionCommand expectedCommand)
            throws ControllerException {
        ActionCommand actualCommand = factory.getActionCommand(request);
        Assert.assertEquals(actualCommand.getClass(),
                expectedCommand.getClass());
    }

    @Test(expectedExceptions = ControllerException.class)
    public void testGetActionCommandException()
            throws ControllerException {
        factory.getActionCommand("wrong_command");
    }
}