package test.kharitonov.day6.service;

import by.kharitonov.day6.model.entity.Book;
import by.kharitonov.day6.model.entity.BookWarehouse;
import by.kharitonov.day6.service.exception.ServiceException;
import by.kharitonov.day6.service.BookService;
import by.kharitonov.day6.view.ViewEmulator;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class BookServiceTest {
    private final BookService service = new BookService();
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
    public void testAddBook() throws ServiceException {
        Book book = Book.newBuilder()
                .setId("12")
                .setTitle("Harry")
                .setAuthors("Bunin", "Esenin")
                .setYear(2020)
                .setPages(444)
                .setPublishingHouse("Minsk")
                .build();
        String[] tagValues = {"12", "Harry", "Bunin, Esenin",
                "2020", "444", "Minsk"};
        List<Book> actualList = service.addBook(tagValues);
        List<Book> expectedList = new ArrayList<>();
        expectedList.add(book);
        assertEquals(actualList, expectedList);
    }

    @Test
    public void testAddBookModelChange() throws ServiceException {
        Book book = Book.newBuilder()
                .setId("322")
                .setTitle("Neznaika na lune")
                .setAuthors("Bunin", "Esenin")
                .setYear(1995)
                .setPages(250)
                .setPublishingHouse("Kapitoshka")
                .build();
        boolean flag;
        String[] tagValues = {
                "322",
                "Neznaika na lune",
                "Bunin, Esenin",
                "1995",
                "250",
                "Kapitoshka"
        };
        service.addBook(tagValues);
        flag = warehouse.indexOf(book) == 3;
        assertTrue(flag);
    }

    @DataProvider(name = "dataAddBookException")
    @Test
    public Object[][] dataAddBookException() {
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
    @Test(dataProvider = "dataAddBookException",
            expectedExceptions = ServiceException.class)
    public void testAddBookException(String[] tagValues)
            throws ServiceException {
        service.addBook(tagValues);
    }

    @Test (expectedExceptions = ServiceException.class)
    public void testAddBookCapacityException() throws ServiceException {
        String[] tagValues = {"322", "Neznaika na lune", "Bunin, Esenin",
                "1995", "250", "Kapitoshka"};
        boolean flag;
        for (int i = 0; i < 97; i++) {
            warehouse.add(book1);
        }
        service.addBook(tagValues);
    }

    @Test
    public void testRemoveBook() throws ServiceException {
        Book book = Book.newBuilder()
                .setId("2")
                .setTitle("Harry Potter and the deathly hallows")
                .setAuthors("Joan Roaling")
                .setYear(2007)
                .setPages(800)
                .setPublishingHouse("Delibri")
                .build();
        String[] tagValues = {"2", "Harry Potter and the deathly hallows",
                "Joan Roaling", "2007", "800", "Delibri"};
        List<Book> actualList = service.removeBook(tagValues);
        List<Book> expectedList = new ArrayList<>();
        expectedList.add(book);
        assertEquals(actualList, expectedList);
    }

    @Test
    public void testRemoveBookModelChange() throws ServiceException {
        int index;
        String[] tagValues = {
                "2",
                "Harry Potter and the deathly hallows",
                "Joan Roaling",
                "2007",
                "800",
                "Delibri"
        };
        service.removeBook(tagValues);
        index = warehouse.indexOf(book1);
        assertEquals(-1, index);
    }

    @DataProvider(name = "dataRemoveBookException")
    @Test
    public Object[][] dataRemoveBookException() {
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
    @Test(dataProvider = "dataRemoveBookException",
            expectedExceptions = ServiceException.class)
    public void testRemoveBookException(String[] tagValues)
            throws ServiceException {
        service.removeBook(tagValues);
    }

    @DataProvider(name = "dataFindBooks")
    @Test
    public Object[][] dataFindBooks() {
        List<Book> listId = new ArrayList<>();
        List<Book> listTitle = new ArrayList<>();
        List<Book> listAuthors = new ArrayList<>();
        List<Book> listYear = new ArrayList<>();
        List<Book> listPages = new ArrayList<>();
        List<Book> listPublisher = new ArrayList<>();
        listId.add(book2);
        listTitle.add(book2);
        listAuthors.add(book1);
        listYear.add(book1);
        listPages.add(book3);
        listPublisher.add(book3);
        return new Object[][]{
                {listId, "id", "1"},
                {listTitle, "title", "Lord of the rings"},
                {listAuthors, "authors", "Joan Roaling"},
                {listYear, "year", "2007"},
                {listPages, "pages", "416"},
                {listPublisher, "publishing_house", "Ishi Press"}
        };
    }

    @Parameters({"expectedList", "parameters"})
    @Test(dataProvider = "dataFindBooks")
    public void testFindBooks(List<Book> expectedList, String[] parameters)
            throws ServiceException {
        List<Book> actualList = service.findBooks(parameters);
        assertEquals(actualList, expectedList);
    }

    @DataProvider(name = "dataFindException")
    @Test
    public Object[][] dataFindException() {
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
    @Test(dataProvider = "dataFindException",
            expectedExceptions = ServiceException.class)
    public void testFindException(String[] parameters)
            throws ServiceException {
        service.findBooks(parameters);
    }

    @DataProvider(name = "dataSortBooks")
    @Test
    public Object[][] dataSortBooks() {
        List<Book> listId = new ArrayList<>();
        List<Book> listTitle = new ArrayList<>();
        List<Book> listAuthors = new ArrayList<>();
        List<Book> listYear = new ArrayList<>();
        List<Book> listPages = new ArrayList<>();
        List<Book> listPublisher = new ArrayList<>();
        listId.add(book2);
        listId.add(book1);
        listId.add(book3);
        listTitle.add(book1);
        listTitle.add(book2);
        listTitle.add(book3);
        listAuthors.add(book1);
        listAuthors.add(book2);
        listAuthors.add(book3);
        listYear.add(book2);
        listYear.add(book1);
        listYear.add(book3);
        listPages.add(book3);
        listPages.add(book1);
        listPages.add(book2);
        listPublisher.add(book1);
        listPublisher.add(book3);
        listPublisher.add(book2);
        return new Object[][]{
                {listId, "id"},
                {listTitle, "title"},
                {listAuthors, "authors"},
                {listYear, "year"},
                {listPages, "pages"},
                {listPublisher, "publishing_house"}
        };
    }

    @Parameters({"expectedList", "parameters"})
    @Test(dataProvider = "dataSortBooks")
    public void testSortBooks(List<Book> expectedList, String[] parameters)
            throws ServiceException {
        List<Book> actualList = service.sortBooks(parameters);
        assertEquals(actualList, expectedList);
    }

    @DataProvider(name = "dataSortException")
    @Test
    public Object[][] dataSortException() {
        return new Object[][]{
                {null},
                {"tag", "extra"},
                {"wrong_tag_name"},
        };
    }

    @Parameters("parameters")
    @Test(dataProvider = "dataSortException",
            expectedExceptions = ServiceException.class)
    public void testSortException(String[] parameters)
            throws ServiceException {
        boolean flag;
        service.sortBooks(parameters);
        flag = ViewEmulator.getCommandResult().getException().isPresent() &&
                ViewEmulator.getCommandResult().getBookList().isEmpty();
        assertTrue(flag);
    }
}