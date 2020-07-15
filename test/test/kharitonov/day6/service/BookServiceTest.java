package test.kharitonov.day6.service;

import by.kharitonov.day6.model.entity.Book;
import by.kharitonov.day6.model.entity.BookWarehouse;
import by.kharitonov.day6.service.BookService;
import by.kharitonov.day6.service.exception.ServiceException;
import by.kharitonov.day6.view.ViewEmulator;
import org.testng.annotations.*;
import test.kharitonov.day6.data_provider.StaticDataProvider;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class BookServiceTest {
    private final BookService service = new BookService();
    private final BookWarehouse warehouse = BookWarehouse.getInstance();

    @BeforeClass
    @BeforeMethod
    private void setUpMethod() {
        warehouse.removeAll();
        warehouse.add(StaticDataProvider.FIRST_BOOK);
        warehouse.add(StaticDataProvider.SECOND_BOOK);
        warehouse.add(StaticDataProvider.THIRD_BOOK);
        warehouse.add(StaticDataProvider.FOURS_BOOK);
    }

    @Test
    public void testAddBook() throws ServiceException {
        List<Book> actualList =
                service.addBook(StaticDataProvider.ADDING_BOOK.getTags());
        List<Book> expectedList = new ArrayList<>();
        expectedList.add(StaticDataProvider.ADDING_BOOK);
        assertEquals(actualList, expectedList);
    }

    @Test
    public void testAddBookModelChange() throws ServiceException {
        boolean flag;
        service.addBook(StaticDataProvider.ADDING_BOOK.getTags());
        flag = warehouse.indexOf(StaticDataProvider.ADDING_BOOK) == 4;
        assertTrue(flag);
    }

    @Parameters("tagValues")
    @Test(dataProvider = "invalidBookTags",
            dataProviderClass = StaticDataProvider.class,
            expectedExceptions = ServiceException.class)
    public void testAddBookInvalidTags(String[] tagValues)
            throws ServiceException {
        service.addBook(tagValues);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testAddExistingBook() throws ServiceException {
        service.addBook(StaticDataProvider.FIRST_BOOK.getTags());
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testAddBookCapacityException() throws ServiceException {
        for (int i = 0; i < 97; i++) {
            warehouse.add(StaticDataProvider.FIRST_BOOK);
        }
        service.addBook(StaticDataProvider.ADDING_BOOK.getTags());
    }

    @Test
    public void testRemoveBook() throws ServiceException {
        List<Book> actualList =
                service.removeBook(StaticDataProvider.FIRST_BOOK.getTags());
        List<Book> expectedList = new ArrayList<>();
        expectedList.add(StaticDataProvider.FIRST_BOOK);
        assertEquals(actualList, expectedList);
    }

    @Test
    public void testRemoveBookModelChange() throws ServiceException {
        int index;
        service.removeBook(StaticDataProvider.FIRST_BOOK.getTags());
        index = warehouse.indexOf(StaticDataProvider.FIRST_BOOK);
        assertEquals(-1, index);
    }

    @Parameters("tagValues")
    @Test(dataProvider = "invalidBookTags",
            dataProviderClass = StaticDataProvider.class,
            expectedExceptions = ServiceException.class)
    public void testRemoveBookInvalidTags(String[] tagValues)
            throws ServiceException {
        service.removeBook(tagValues);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testRemoveNotExistingBook() throws ServiceException {
        service.removeBook(StaticDataProvider.ADDING_BOOK.getTags());
    }

    @Parameters({"expectedList", "parameters"})
    @Test(dataProvider = "foundBooks",
            dataProviderClass = StaticDataProvider.class)
    public void testFindBooks(List<Book> expectedList, String[] parameters)
            throws ServiceException {
        List<Book> actualList = service.findBooks(parameters);
        assertEquals(actualList, expectedList);
    }

    @Parameters("parameters")
    @Test(dataProvider = "invalidFindParameters",
            dataProviderClass = StaticDataProvider.class,
            expectedExceptions = ServiceException.class)
    public void testFindException(String[] parameters)
            throws ServiceException {
        service.findBooks(parameters);
    }

    @Parameters({"expectedList", "parameters"})
    @Test(dataProvider = "sortedBooks",
            dataProviderClass = StaticDataProvider.class)
    public void testSortBooks(List<Book> expectedList, String[] parameters)
            throws ServiceException {
        List<Book> actualList = service.sortBooks(parameters);
        assertEquals(actualList, expectedList);
    }

    @Parameters("parameters")
    @Test(dataProvider = "invalidSortParameters",
            dataProviderClass = StaticDataProvider.class,
            expectedExceptions = ServiceException.class)
    public void testSortException(String[] parameters)
            throws ServiceException {
        service.sortBooks(parameters);
    }
}