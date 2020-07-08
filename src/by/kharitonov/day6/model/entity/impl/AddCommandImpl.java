package by.kharitonov.day6.model.entity.impl;

import by.kharitonov.day6.controller.BookWarehouseController;
import by.kharitonov.day6.model.entity.Command;

public class AddCommandImpl implements Command {
    private final BookWarehouseController controller;

    public AddCommandImpl(BookWarehouseController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.addBook();
    }
}
