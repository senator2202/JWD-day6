package by.kharitonov.day6.view;

import by.kharitonov.day6.controller.BookWarehouseController;
import by.kharitonov.day6.view.io.ConsoleOutputService;

public class ConsoleAddBookView extends ConsoleView {
    @Override
    public void startView() {
        ConsoleOutputService outputService = new ConsoleOutputService();
        String[] tagValues = inputBookConsole();
        BookWarehouseController controller = new BookWarehouseController();
        String message = controller.addBook(tagValues);
        outputService.printMessage(message);
    }
}
