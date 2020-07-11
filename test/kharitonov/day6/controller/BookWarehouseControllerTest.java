package test.kharitonov.day6.controller;

import by.kharitonov.day6.controller.BookWarehouseController;
import by.kharitonov.day6.model.entity.Book;
import by.kharitonov.day6.model.entity.BookWarehouse;
import by.kharitonov.day6.view.ViewEmulator;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class BookWarehouseControllerTest {
    private BookWarehouseController controller = new BookWarehouseController();
    private BookWarehouse warehouse = BookWarehouse.getInstance();
    private Book book1;
    private Book book2;
    private Book book3;

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
        flag = ViewEmulator.getCommandResult().getException().isEmpty() &&
                ViewEmulator.getCommandResult().getBookList().size() == 1 &&
                ViewEmulator.getCommandResult().getBookList().contains(book);
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
                        "Kapitoshka"},
                {"2", "Harry", "Joan Roaling", "2007", "800", "Delibri", "222"},
                {"2", "", "Joan Roaling", "2007", "800", "Delibri"},
        };
    }

    @Parameters("tagValues")
    @Test(dataProvider = "dataProcessRequestAddException")
    public void testProcessRequestAddException(String[] tagValues) {
        boolean flag;
        controller.processRequest("add", tagValues);
        flag = ViewEmulator.getCommandResult().getException().isPresent() &&
                ViewEmulator.getCommandResult().getBookList().isEmpty();
        assertTrue(flag);
    }

    @Test
    public void testProcessRequestRemoveModelChange() {
        int index;
        controller.processRequest("remove",
                "2",
                "Harry Potter and the deathly hallows",
                "Joan Roaling",
                "2007",
                "800",
                "Delibri");
        index = warehouse.indexOf(book1);
        assertEquals(-1, index);
    }

    @Test
    public void testProcessRequestRemoveViewChange() {
        Book book = Book.newBuilder()
                .setId("1")
                .setTitle("Lord of the rings")
                .setAuthors("John Tolkien")
                .setYear(2002)
                .setPages(900)
                .setPublishingHouse("Vysnova")
                .build();
        boolean flag;
        controller.processRequest("remove",
                "1",
                "Lord of the rings",
                "John Tolkien",
                "2002",
                "900",
                "Vysnova");
        flag = ViewEmulator.getCommandResult().getException().isEmpty() &&
                ViewEmulator.getCommandResult().getBookList().size() == 1 &&
                ViewEmulator.getCommandResult().getBookList().contains(book);
        assertTrue(flag);
    }

    @DataProvider(name = "dataProcessRequestRemoveException")
    @Test
    public Object[][] dataProcessRequestRemoveException() {
        return new Object[][]{
                {"2", "Bugaga", "Joan Kipling", "2007", "800", "Delibri"},
                {"322", "Neznaika na lune", "Bunin, Esenin", "-1995", "250",
                        "Kapitoshka"},
                {"322", "Neznaika na lune", "Bunin, Esenin", "1995", "-250",
                        "Kapitoshka"},
                {"2", "Bugaga", "Joan Kipling", "2007", "800", "Delibri", "2"},
                {"2", "", "", "2007", "800", "Delibri"},
        };
    }

    @Parameters("tagValues")
    @Test(dataProvider = "dataProcessRequestRemoveException")
    public void testProcessRequestRemoveException(String[] tagValues) {
        boolean flag;
        controller.processRequest("remove", tagValues);
        flag = ViewEmulator.getCommandResult().getException().isPresent() &&
                ViewEmulator.getCommandResult().getBookList().isEmpty();
        assertTrue(flag);
    }

    @Test
    public void testProcessRequestFindId() {
        boolean flag;
        controller.processRequest("find", "id", "1");
        flag = ViewEmulator.getCommandResult().getException().isEmpty() &&
                ViewEmulator.getCommandResult().getBookList().size() == 1 &&
                ViewEmulator.getCommandResult().getBookList().contains(book2);
        assertTrue(flag);
    }

    @Test
    public void testProcessRequestFindTitle() {
        boolean flag;
        controller.processRequest("find", "title", "Lord of the rings");
        flag = ViewEmulator.getCommandResult().getException().isEmpty() &&
                ViewEmulator.getCommandResult().getBookList().size() == 1 &&
                ViewEmulator.getCommandResult().getBookList().contains(book2);
        assertTrue(flag);
    }

    @Test
    public void testProcessRequestFindAuthor() {
        boolean flag;
        Book book = Book.newBuilder()
                .setId("2222")
                .setTitle("Harry Potter and the goblet of fire")
                .setAuthors("Joan Roaling")
                .setYear(2005)
                .setPages(788)
                .setPublishingHouse("Minsk")
                .build();
        warehouse.add(book);
        controller.processRequest("find", "authors", "Joan Roaling");
        flag = ViewEmulator.getCommandResult().getException().isEmpty() &&
                ViewEmulator.getCommandResult().getBookList().size() == 2 &&
                ViewEmulator.getCommandResult().getBookList().contains(book1) &&
                ViewEmulator.getCommandResult().getBookList().contains(book);
        assertTrue(flag);
    }

    @Test
    public void testProcessRequestFindYear() {
        boolean flag;
        controller.processRequest("find", "year", "2007");
        flag = ViewEmulator.getCommandResult().getException().isEmpty() &&
                ViewEmulator.getCommandResult().getBookList().size() == 1 &&
                ViewEmulator.getCommandResult().getBookList().contains(book1);
        assertTrue(flag);
    }

    @Test
    public void testProcessRequestFindPages() {
        boolean flag;
        controller.processRequest("find", "pages", "416");
        flag = ViewEmulator.getCommandResult().getException().isEmpty() &&
                ViewEmulator.getCommandResult().getBookList().size() == 1 &&
                ViewEmulator.getCommandResult().getBookList().contains(book3);
        assertTrue(flag);
    }

    @Test
    public void testProcessRequestFindPublishingHouse() {
        boolean flag;
        controller.processRequest("find", "publishing_house", "Delibri");
        flag = ViewEmulator.getCommandResult().getException().isEmpty() &&
                ViewEmulator.getCommandResult().getBookList().size() == 1 &&
                ViewEmulator.getCommandResult().getBookList().contains(book1);
        assertTrue(flag);
    }

    @DataProvider(name = "dataProcessRequestFindException")
    @Test
    public Object[][] dataProcessRequestFindException() {
        return new Object[][]{
                {null},
                {"tag", "value", "extra"},
                {"wrong_tag_name", "value"},
                {"id", ""},
                {"title", ""},
                {"authors", ""},
                {"year", ""},
                {"year", "-22"},
                {"pages", ""},
                {"pages", "22.22"},
                {"publishing_house", ""}
        };
    }

    @Parameters("parameters")
    @Test(dataProvider = "dataProcessRequestFindException")
    public void testProcessRequestFindException(String[] parameters) {
        boolean flag;
        controller.processRequest("find", parameters);
        flag = ViewEmulator.getCommandResult().getException().isPresent() &&
                ViewEmulator.getCommandResult().getBookList().isEmpty();
        assertTrue(flag);
    }

    @Test
    public void testProcessRequestSortId() {
        boolean flag;
        controller.processRequest("sort", "id");
        flag = ViewEmulator.getCommandResult().getException().isEmpty() &&
                ViewEmulator.getCommandResult().getBookList().size() == 3 &&
                ViewEmulator.getCommandResult()
                        .getBookList().indexOf(book2) == 0 &&
                ViewEmulator.getCommandResult()
                        .getBookList().indexOf(book1) == 1 &&
                ViewEmulator.getCommandResult()
                        .getBookList().indexOf(book3) == 2;
        assertTrue(flag);
    }

    @Test
    public void testProcessRequestSortTitle() {
        boolean flag;
        controller.processRequest("sort", "title");
        flag = ViewEmulator.getCommandResult().getException().isEmpty() &&
                ViewEmulator.getCommandResult().getBookList().size() == 3 &&
                ViewEmulator.getCommandResult()
                        .getBookList().indexOf(book1) == 0 &&
                ViewEmulator.getCommandResult()
                        .getBookList().indexOf(book2) == 1 &&
                ViewEmulator.getCommandResult()
                        .getBookList().indexOf(book3) == 2;
        assertTrue(flag);
    }

    @Test
    public void testProcessRequestSortAuthors() {
        boolean flag;
        controller.processRequest("sort", "authors");
        flag = ViewEmulator.getCommandResult().getException().isEmpty() &&
                ViewEmulator.getCommandResult().getBookList().size() == 3 &&
                ViewEmulator.getCommandResult()
                        .getBookList().indexOf(book1) == 0 &&
                ViewEmulator.getCommandResult()
                        .getBookList().indexOf(book2) == 1 &&
                ViewEmulator.getCommandResult()
                        .getBookList().indexOf(book3) == 2;
        assertTrue(flag);
    }

    @Test
    public void testProcessRequestSortYear() {
        boolean flag;
        controller.processRequest("sort", "year");
        flag = ViewEmulator.getCommandResult().getException().isEmpty() &&
                ViewEmulator.getCommandResult().getBookList().size() == 3 &&
                ViewEmulator.getCommandResult()
                        .getBookList().indexOf(book2) == 0 &&
                ViewEmulator.getCommandResult()
                        .getBookList().indexOf(book1) == 1 &&
                ViewEmulator.getCommandResult()
                        .getBookList().indexOf(book3) == 2;
        assertTrue(flag);
    }

    @Test
    public void testProcessRequestSortPages() {
        boolean flag;
        controller.processRequest("sort", "pages");
        flag = ViewEmulator.getCommandResult().getException().isEmpty() &&
                ViewEmulator.getCommandResult().getBookList().size() == 3 &&
                ViewEmulator.getCommandResult()
                        .getBookList().indexOf(book3) == 0 &&
                ViewEmulator.getCommandResult()
                        .getBookList().indexOf(book1) == 1 &&
                ViewEmulator.getCommandResult()
                        .getBookList().indexOf(book2) == 2;
        assertTrue(flag);
    }

    @Test
    public void testProcessRequestSortPublishingHouse() {
        boolean flag;
        controller.processRequest("sort", "publishing_house");
        flag = ViewEmulator.getCommandResult().getException().isEmpty() &&
                ViewEmulator.getCommandResult().getBookList().size() == 3 &&
                ViewEmulator.getCommandResult()
                        .getBookList().indexOf(book1) == 0 &&
                ViewEmulator.getCommandResult()
                        .getBookList().indexOf(book3) == 1 &&
                ViewEmulator.getCommandResult()
                        .getBookList().indexOf(book2) == 2;
        assertTrue(flag);
    }

    @DataProvider(name = "dataProcessRequestSortException")
    @Test
    public Object[][] dataProcessRequestSortException() {
        return new Object[][]{
                {null},
                {"tag", "extra"},
                {"wrong_tag_name"},
        };
    }

    @Parameters("parameters")
    @Test(dataProvider = "dataProcessRequestSortException")
    public void testProcessRequestSortException(String[] parameters) {
        boolean flag;
        controller.processRequest("sort", parameters);
        flag = ViewEmulator.getCommandResult().getException().isPresent() &&
                ViewEmulator.getCommandResult().getBookList().isEmpty();
        assertTrue(flag);
    }
}