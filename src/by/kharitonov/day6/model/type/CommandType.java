package by.kharitonov.day6.model.type;

import by.kharitonov.day6.controller.command.ActionCommand;
import by.kharitonov.day6.controller.command.impl.*;

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
    },
    EMPTY {
        {
            this.command = new EmptyCommand();
        }
    };

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
