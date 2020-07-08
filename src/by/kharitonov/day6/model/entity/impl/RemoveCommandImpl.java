package by.kharitonov.day6.model.entity.impl;

import by.kharitonov.day6.controller.BookWarehouseController;
import by.kharitonov.day6.model.entity.Command;

public class RemoveCommandImpl implements Command {
    private final BookWarehouseController controller;

    public RemoveCommandImpl(BookWarehouseController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.removeBook();
    }
}

