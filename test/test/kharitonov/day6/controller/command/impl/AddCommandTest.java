package test.kharitonov.day6.controller.command.impl;

import by.kharitonov.day6.controller.command.impl.AddCommand;
import by.kharitonov.day6.model.entity.Book;
import by.kharitonov.day6.model.entity.BookWarehouse;
import by.kharitonov.day6.model.entity.CommandResult;
import by.kharitonov.day6.model.exception.ServiceException;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class AddCommandTest {
    private AddCommand command = new AddCommand();
    private final BookWarehouse warehouse = BookWarehouse.getInstance();
    private Book book1;
    private Book book2;
    private Book book3;

    @BeforeClass
    @BeforeMethod
    private void setUpMethod() {
        warehouse.removeAll();
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
    public void testExecute() {
        String[] content = {
                "322",
                "Neznaika na lune",
                "Bunin, Esenin",
                "1995",
                "250",
                "Kapitoshka"};
        Book book = Book.newBuilder()
                .setId("322")
                .setTitle("Neznaika na lune")
                .setAuthors("Bunin", "Esenin")
                .setYear(1995)
                .setPages(250)
                .setPublishingHouse("Kapitoshka")
                .build();
        List<Book> expectedList = new ArrayList<>();
        expectedList.add(book);
        CommandResult actualResult = command.execute(content);
        CommandResult expectedResult = new CommandResult(expectedList, null);
        assertEquals(actualResult, expectedResult);
    }

    @DataProvider(name = "dataExecuteCatchException")
    @Test
    public Object[][] dataExecuteCatchException() {
        return new Object[][]{
                {"322", "Neznaika na lune", "Bunin, Esenin", "-1995", "250",
                        "Kapitoshka"},
                {"322", "Neznaika na lune", "Bunin, Esenin", "1995", "-250",
                        "Kapitoshka"},
                {"2", "Harry", "Joan Roaling", "2007", "800", "Delibri", "222"},
                {"2", "", "Joan Roaling", "2007", "800", "Delibri"}
        };
    }

    @Parameters("content")
    @Test(dataProvider = "dataExecuteCatchException")
    public void testExecuteCatchException(String[] content) {
        List<Book> expectedList = new ArrayList<>();
        ServiceException exception =
                new ServiceException("Invalid book parameters!");
        CommandResult expectedResult =
                new CommandResult(expectedList, exception);
        CommandResult actualResult = command.execute(content);
        assertEquals(actualResult,expectedResult);
    }

    @Test
    public void dataExecuteCatchExistingException() {
        String[] content = {"2", "Harry Potter and the deathly hallows",
                "Joan Roaling", "2007", "800", "Delibri"};
        List<Book> expectedList = new ArrayList<>();
        ServiceException exception =
                new ServiceException("This book already exists!");
        CommandResult expectedResult =
                new CommandResult(expectedList, exception);
        CommandResult actualResult = command.execute(content);
        assertEquals(actualResult,expectedResult);
    }
}