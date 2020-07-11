package by.kharitonov.day6.view;

import by.kharitonov.day6.model.entity.CommandResult;

public class ViewEmulator {
    private static CommandResult commandResult = new CommandResult();

    private ViewEmulator() {
    }

    public static CommandResult getCommandResult() {
        return commandResult;
    }

    public static void setCommandResult(CommandResult commandResult) {
        ViewEmulator.commandResult = commandResult;
    }
}
