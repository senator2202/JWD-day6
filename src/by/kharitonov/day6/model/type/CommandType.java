package by.kharitonov.day6.model.type;

import by.kharitonov.day6.controller.command.ActionCommand;
import by.kharitonov.day6.controller.command.impl.AddCommand;
import by.kharitonov.day6.controller.command.impl.FindCommand;
import by.kharitonov.day6.controller.command.impl.RemoveCommand;
import by.kharitonov.day6.controller.command.impl.SortCommand;

public enum CommandType {
    ADD {
        {
            this.command = new AddCommand();
        }
    },
    REMOVE {
        {
            this.command = new RemoveCommand();
        }
    },
    FIND {
        {
            this.command = new FindCommand();
        }
    },
    SORT {
        {
            this.command = new SortCommand();
        }
    };

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
