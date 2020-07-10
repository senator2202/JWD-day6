package test.kharitonov.day6.controller;

import by.kharitonov.day6.controller.BookWarehouseController;
import by.kharitonov.day6.model.entity.Book;
import by.kharitonov.day6.model.entity.BookWarehouse;
import by.kharitonov.day6.view.ViewEmulator;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class BookWarehouseControllerTest {
    private BookWarehouseController controller = new BookWarehouseController();
    private BookWarehouse warehouse = BookWarehouse.getInstance();
    private Book book1;
    private Book book2;
    private Book book3;

    @BeforeMethod
    private void setUpMethod() {
        book1 = Book.newBuilder()
                .setId("2")
                .setTitle("Harry Potter and the deathly hallows")
                .setAuthors("Joan Roaling")
                .setYear(2007)
                .setPages(800)
                .setPublishingHouse("Delibri")
                .build();
        book2 = Book.newBuilder()
                .setId("1")
                .setTitle("Lord of the rings")
                .setAuthors("John Tolkien")
                .setYear(2002)
                .setPages(900)
                .setPublishingHouse("Vysnova")
                .build();
        book3 = Book.newBuilder()
                .setId("3")
                .setTitle("The little golden calf")
                .setAuthors("Ilya Ilf", "Eugene Petrov")
                .setYear(2015)
                .setPages(416)
                .setPublishingHouse("Ishi Press")
                .build();
        warehouse.add(book1);
        warehouse.add(book2);
        warehouse.add(book3);
    }

    @Test
    public void testProcessRequestAddModelChange() {
        Book book = Book.newBuilder()
                .setId("322")
                .setTitle("Neznaika na lune")
                .setAuthors("Bunin", "Esenin")
                .setYear(1995)
                .setPages(250)
                .setPublishingHouse("Kapitoshka")
                .build();
        boolean flag;
        controller.processRequest("add",
                "322",
                "Neznaika na lune",
                "Bunin, Esenin",
                "1995",
                "250",
                "Kapitoshka");
        flag = warehouse.indexOf(book) == 3;
        assertTrue(flag);
    }

    @Test
    public void testProcessRequestAddViewChange() {
        Book book = Book.newBuilder()
                .setId("322")
                .setTitle("Neznaika na lune")
                .setAuthors("Bunin", "Esenin")
                .setYear(1995)
                .setPages(250)
                .setPublishingHouse("Kapitoshka")
                .build();
        boolean flag;
        controller.processRequest("add",
                "322",
                "Neznaika na lune",
                "Bunin, Esenin",
                "1995",
                "250",
                "Kapitoshka");
        flag = ViewEmulator.commandResult.getException().isEmpty() &&
                ViewEmulator.commandResult.getBookList().size() == 1 &&
                ViewEmulator.commandResult.getBookList().contains(book);
        assertTrue(flag);
    }

    @DataProvider(name = "dataProcessRequestAddException")
    @Test
    public Object[][] dataProcessRequestAddException() {
        return new Object[][]{
                {"2", "Harry Potter and the deathly hallows",
                        "Joan Roaling", "2007", "800", "Delibri"},
                {"322", "Neznaika na lune", "Bunin, Esenin", "-1995", "250",
                        "Kapitoshka"},
                {"322", "Neznaika na lune", "Bunin, Esenin", "1995", "-250",
                        "Kapitoshka"}
        };
    }

    @Parameters("tagValues")
    @Test(dataProvider = "dataProcessRequestAddException")
    public void testProcessRequestAddException(String[] tagValues) {
        boolean flag;
        controller.processRequest("add", tagValues);
        flag = ViewEmulator.commandResult.getException().isPresent() &&
                ViewEmulator.commandResult.getBookList().size() == 0;
        assertTrue(flag);
    }

    @Test
    public void testProcessRequestRemove() {
        int index;
        controller.processRequest("remove",
                "2",
                "Harry Potter and the deathly hallows",
                "Joan Roaling",
                "2007",
                "800",
                "Delibri");
        index = warehouse.indexOf(book1);
        assertTrue(index == -1);
    }

    @Test
    public void testProcessRequestFindTitle() {
    }
}