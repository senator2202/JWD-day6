package by.kharitonov.day6.view;

import by.kharitonov.day6.controller.BookWarehouseController;
import by.kharitonov.day6.model.type.BookTag;
import by.kharitonov.day6.view.io.ConsoleOutputService;
import by.kharitonov.day6.view.io.UserCommunicationService;

public class ConsoleSortBooksView extends ConsoleView {
    @Override
    public void startView() {
        ConsoleOutputService outputService = new ConsoleOutputService();
        BookWarehouseController controller = new BookWarehouseController();
        UserCommunicationService communicator = new UserCommunicationService();
        BookTag bookTag = communicator.requestChooseTag();
        String result = controller.sortBooks(bookTag);
        outputService.printMessage(result);
    }
}
