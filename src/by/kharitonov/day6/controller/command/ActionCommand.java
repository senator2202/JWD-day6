package by.kharitonov.day6.controller.command;

import by.kharitonov.day6.controller.entity.CommandResult;

public interface ActionCommand {
    CommandResult execute(String... content);
}
