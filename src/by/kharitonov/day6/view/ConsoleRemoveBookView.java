package by.kharitonov.day6.view;

import by.kharitonov.day6.controller.BookWarehouseController;
import by.kharitonov.day6.model.service.io.ConsoleOutputService;

public class ConsoleRemoveBookView extends ConsoleView {

    @Override
    public void startView() {
        ConsoleOutputService outputService = new ConsoleOutputService();
        BookWarehouseController controller = new BookWarehouseController();
        String message;
        String[] tagValues = inputBookConsole();
        message = controller.removeBook(tagValues);
        outputService.printMessage(message);
    }
}
