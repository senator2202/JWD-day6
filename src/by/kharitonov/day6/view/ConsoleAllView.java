package by.kharitonov.day6.view;

import by.kharitonov.day6.controller.BookWarehouseController;
import by.kharitonov.day6.model.service.io.ConsoleOutputService;

public class ConsoleAllView extends ConsoleView {
    @Override
    public void startView() {
        ConsoleOutputService outputService = new ConsoleOutputService();
        BookWarehouseController controller = new BookWarehouseController();
        String result = controller.findAll();
        outputService.printMessage(result);
    }
}
