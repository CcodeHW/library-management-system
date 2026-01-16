package th.rosenheim.oop;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.*;

/**
 * @author Chuan Hn Wong - 1084380
 * JUnit tests for the Library Management System
 */
class AppTest {

    private Library library;
    private LibraryUser testUser;
    private Book testBook;
    private Magazine testMagazine;

    @BeforeEach
    void setUp(){
        library = new Library();
        testBook = new Book("B100", "Test Book", "Test Author", "000-0000000000", "Test Genre");
        testMagazine = new Magazine("M100", "Test Magazine", "Test Author", "0000-0000", 000, "Test Publisher");
        testUser = new LibraryUser("S100", "Test User", "test@gmail.com");

        library.addItem(testBook);
        library.addItem(testMagazine);
        library.registerUser(testUser);
    }

    @Test
    @DisplayName("Test adding items to library")
    void testAddValidItem(){
        Book newBook = new Book("B101", "New Book", "New Author", "000-0000000000", "Test Genre");
        Magazine newMagazine = new  Magazine("M101","New Magazine", "New Author","000-0000000000",000,"Test Publisher");

        library.addItem(newBook);
        library.addItem(newMagazine);

        Map<String,LibraryItem> items = library.getItems();
        assertEquals(4, items.size(),"Library should have 4 items - 2 Books, 2 Magazines");
        assertTrue(items.containsKey("B101"), "New book should be in library");
        assertTrue(items.containsKey("M101"),"New magazine should be in library");
    }

    @Test
    @DisplayName("Test adding duplicate book")
    void testAddDuplicateBook(){
        Book duplicateBook = new Book("B100", "New Book", "New Author", "000-0000000000", "Test Genre");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> library.addItem(duplicateBook));

        assertTrue(exception.getMessage().contains("already exists"));
    }

    @Test
    @DisplayName("Test adding duplicate magazine")
    void testAddDuplicateMagazine(){
        Magazine duplicateMagazine = new Magazine("M100", "Test Magazine", "Test Author", "0000-0000", 000, "Test Publisher");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> library.addItem(duplicateMagazine));

        assertTrue(exception.getMessage().contains("already exists"));
    }

    @Test
    @DisplayName("Test borrowing available item")
    void testBorrowAvailableItem(){
        boolean result = library.borrowItem("B100","S100");

        assertTrue(result, "Borrow should succeed and return true");
        assertFalse(testBook.isAvailable(),"item should be borrowed by test user and return false");
    }

    @Test
    @DisplayName("Test borrowing item twice")
    void testBorrowItemTwice(){
        library.borrowItem("B100","S100");
        boolean borrowAgain = library.borrowItem("B100","S100");

        assertFalse(borrowAgain, "item already borrowed, should return false");
    }

    @Test
    @DisplayName("Test borrowing item that is not in the library")
    void testBorrowUnavailableItem(){
        ItemNotFoundException exception = assertThrows(
                ItemNotFoundException.class,
                () -> library.borrowItem("B111","S100"));

        assertTrue(exception.getMessage().contains("not found in the system"),
                "Item not found in library should return ItemNotFoundException");

    }

    @Test
    @DisplayName("Test borrowing item with invalid user ID")
    void testBorrowItemWithInvalidUserID(){
        UserNotFoundException exception = assertThrows(
                UserNotFoundException.class,
                () -> library.borrowItem("B100", "S111"));

        assertTrue(exception.getMessage().contains("not found in the system"),
                "Invalid User should return UserNotFoundException");
    }

    @Test
    @DisplayName("Test returning borrowed item")
    void testReturnBorrowedItem(){
        library.borrowItem("B100","S100");

        boolean returnBorrowedItem = library.returnItem("B100","S100");

        assertTrue(returnBorrowedItem,"Item that has been borrowed should be returned back to the library, should return true");
    }

    @Test
    @DisplayName("Test returning item that has not been borrowed")
    void testReturnNotBorrowedItem(){
        boolean returnNotBorrowedItem = library.returnItem("B111","S100");

        assertFalse(returnNotBorrowedItem,"Item that has not been borrowed cannot be returned, should return false");
    }

    @Test
    @DisplayName("Test returning item with invalid user ID")
    void testReturnItemWithInvalidUserID(){
        UserNotFoundException exception = assertThrows(
                UserNotFoundException.class,
                () -> library.returnItem("B100", "S111"));

        assertTrue(exception.getMessage().contains("not found in the system"),
                "Invalid User should return UserNotFoundException");
    }

    @Test
    @DisplayName("Test search by title correct results")
    void testSearchByTitle(){
        List<LibraryItem> results = library.searchByTitle("Test");

        assertEquals(2, results.size(),"Should find 2 items with 'Test' in title - testBook, testMagazine");
        assertTrue(results.contains(testBook));
        assertTrue(results.contains(testMagazine));
    }

    @Test
    @DisplayName("Test search by author correct results")
    void testSearchByAuthor(){
        List<LibraryItem> results = library.searchByAuthor("Test");

        assertEquals(2, results.size(),"Should find 2 items with 'Test' in author - testBook & testMagazine");
        assertTrue(results.contains(testBook));
        assertTrue(results.contains(testMagazine));
    }

    @Test
    @DisplayName("Test get available items")
    void testGetAvailableItems(){
        library.borrowItem("B100","S100");

        List<LibraryItem> available = library.getAvailableItems();

        assertEquals(1, available.size(),"should have 1 available item left (testMagazine) since book has been borrowed already");
        assertTrue(available.contains(testMagazine),"Magazine should be available");
        assertFalse(available.contains(testBook),"Book should be not available");
    }

    @Test
    @DisplayName("Test get borrowed items")
    void testGetBorrowedItems(){
        library.borrowItem("B100","S100");

        List<LibraryItem> borrowedItems = library.getBorrowedItems();

        assertEquals(1,borrowedItems.size(),"should have only 1 borrowed item - testBook");
        assertTrue(borrowedItems.contains(testBook),"testBook is in borrowed list");
        assertFalse(borrowedItems.contains(testMagazine),"testMagazine is not borrowed, not in borrowed list");
    }

    @Test
    @DisplayName("Test Book Comparable implementation sorts by title")
    void testBookComparable(){
        Book b1 = new Book("B111","Apple","Author 1", "111-1111111111","Genre 1");
        Book b2 = new Book("B222","Banana","Author 2", "222-2222222222","Genre 2");
        Book b3 = new Book("B333","Cherry","Author 3", "333-3333333333","Genre 3");

        List<Book> books = List.of(b1, b3, b2); // make them not in order
        List<Book> sorted = books.stream().sorted().toList();

        assertEquals("Book1", sorted.get(0).getTitle(), "Book 1 should come first ");
        assertEquals("Book2", sorted.get(1).getTitle(), "Book 2 should come second ");
        assertEquals("Book3", sorted.get(2).getTitle(), "Book 3 should come third");
    }

    @Test
    @DisplayName("Test BookTitleComparator")
    void testBookTitleComparator(){
        BookTitleComparator comparator = new BookTitleComparator();

        Book b1 = new Book("B111","Apple","Author 1", "111-1111111111","Genre 1");
        Book b2 = new Book("B222","Banana","Author 2", "222-2222222222","Genre 2");
        Book b3 = new Book("B333","Cherry","Author 3", "333-3333333333","Genre 3");

        assertTrue(comparator.compare(b1,b2) < 0, "Apple should come before Banana");
        assertTrue(comparator.compare(b3,b2) > 0, "Cherry should come after Banana");
        assertEquals(0,comparator.compare(b1,b1), "Same Book - Apple should be equals");
    }

    @Test
    @DisplayName("Test LibraryItem equals() and hashCode()")
    void testLibraryItemEquals(){
        Book sameBook1 = new Book("B111","Apple","Author 1", "111-1111111111","Genre 1");
        Book sameBook2 = new Book("B111","Apple","Author 1", "111-1111111111","Genre 1");
        Book diffBook = new Book("B333","Cherry","Author 3", "333-3333333333","Genre 3");

        assertEquals(sameBook1, sameBook2,"Books with the same ID should be equal");
        assertNotEquals(sameBook1, diffBook, "Books with different ID should not be equal");

        assertEquals(sameBook1.hashCode(), sameBook2.hashCode(),"Equal objects should have the same hashCode");
        assertNotEquals(sameBook1.hashCode(),diffBook.hashCode(),"Different objects should have different hashCode");
    }

    @Test
    @DisplayName("Test LibraryUser equals() and borrowing functionality")
    void testLibraryUser(){
        LibraryUser s1 = new LibraryUser("S100", "Bob", "bob@gmail.com");
        LibraryUser s2 = new LibraryUser("S100", "Bob", "bob@gmail.com");
        LibraryUser s3 = new LibraryUser("S200", "Alice", "alice@gmail.com");

        // Test equals() based on user ID
        assertEquals(s1, s2,"Users with the same ID should be equal");
        assertNotEquals(s1, s3,"Users with the different ID should not be equal");

        // Test borrowing items
        s1.borrowItem(testBook);
        assertTrue(s1.getBorrowedItems().contains(testBook), "User's borrowed items should contain the test book");

        s1.returnItem(testBook);
        assertFalse(s1.getBorrowedItems().contains(testBook), "User's borrowed items should contain the test book");
    }

    @Test
    @DisplayName("Test due date calculation for books and magazines")
    void testDueDateCalculation(){
        testBook.borrow("S100");
        LocalDate bookDueDate = LocalDate.parse(testBook.getDueDate());
        LocalDate expectedBookDueDate = LocalDate.now().plusWeeks(2);
        assertEquals(expectedBookDueDate, bookDueDate, "Book due date should be 2 weeks from now");

        testMagazine.borrow("S100");
        LocalDate magazineDueDate = LocalDate.parse(testMagazine.getDueDate());
        LocalDate expectedMagazineDueDate = LocalDate.now().plusWeeks(1);
        assertEquals(expectedMagazineDueDate, magazineDueDate, "Book due date should be 1 week from now");
    }

    @Test
    @DisplayName("Test borrowing null item")
    void testBorrowNullItems(){
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> testUser.borrowItem(null));

        assertTrue(exception.getMessage().contains("cannot be null"));
    }

}