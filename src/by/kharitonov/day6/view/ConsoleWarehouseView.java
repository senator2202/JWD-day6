package by.kharitonov.day6.view;

import by.kharitonov.day6.model.bo.Book;

import java.util.List;

public class ConsoleWarehouseView {
    public void printWarehouse(List<Book> books) {
        for (Book book : books) {
            System.out.println(book);
        }
    }
}
