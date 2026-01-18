package th.rosenheim.oop;

import java.util.*;

/**
 * @author Chuan Hn Wong - 1084380
 * Application Class demonstrating the Library Mnagement System
 * Demomstrates all OOP concepts for the PStA
 */

public class App {


    public static void main(String[] args) {
        System.out.println("=== Library Management System Demonstration ===");
        Library library = new Library();

        Book book1 = new Book("B100", "Harry Potter", "J.K Rowling",
                            "978-0199299327", "Fantasy");
        Book book2 = new Book("B101", "The Hobbit", "J.R.R. Tolkien",
                            "978-1020201029", "Fantasy");
        Book book3 = new Book("B102", "The Da Vinci Code", "Dan Brown",
                            "978-1271829901", "Mystery");
        Magazine magazine1 = new Magazine("M100", "National Geographic",
                        "National Geographic Society","0023-9878", 156, "Nat Geo");

        System.out.println("Adding items to the library...");
        library.addItem(book1);
        library.addItem(book2);
        library.addItem(book3);
        library.addItem(magazine1);

        System.out.println("=== Library User Registration ===");
        LibraryUser user1 = new LibraryUser("S100", "Bob Marley", "bobmarley@gmail.com");
        LibraryUser user2 = new LibraryUser("S101", "Kelly Lee", "kellylee@gmail.com");
        LibraryUser user3 = new LibraryUser("S102", "Christine Smith", "christine@yahoo.com");

        library.registerUser(user1);
        library.registerUser(user2);
        library.registerUser(user3);

        System.out.println("=== Polymorphism Demonstration ===");
        List<LibraryItem> items = new ArrayList<>();
        items.add(book1);
        items.add(magazine1);

        for(LibraryItem item : items){
            System.out.println(" - " +item.getDetails());
        }

        System.out.println("=== Borrowing Functionality ===");
        boolean borrowSuccess = library.borrowItem("B100", "S100");
        System.out.println(" Borrow 'Harry Potter': " +(borrowSuccess ? "SUCCESS" : "FAILED"));

        System.out.println();

        boolean borrowFail = library.borrowItem("B100", "S100");
        System.out.println(" Borrow the same book 'Harry Potter' again: " +(borrowFail ? "SUCCESS" : "FAILED"));

        System.out.println();

        System.out.println("=== Search Functionality ===");
        List<LibraryItem> jkRowlingBooks = library.searchByAuthor("J.K Rowling");
        System.out.println(" Books by J.K Rowling: " +jkRowlingBooks.size());
        System.out.println();
        List<LibraryItem> theHobbitBooks = library.searchByTitle("The Hobbit");
        System.out.println(" Books with similar title 'The Hobbit': " +theHobbitBooks.size());
        System.out.println();

        System.out.println("=== Sorting Demonstration Using Comparator & Comparable ===");
        List<Book> books = Arrays.asList(book3, book1, book2); // purposely messed them up in different order
        System.out.println("Book Before Sorting: " );
        for(Book book : books){
            System.out.println(" - " +book.getTitle());
        }

        System.out.println();

        Collections.sort(books);
        System.out.println("Book sorted by title (Comparable): ");
        for(Book book : books){
            System.out.println(" - " +book.getTitle());
        }

        System.out.println();

        books.sort(new BookTitleComparator());
        System.out.println(" Books sorted by title (Comparator): ");
        for(Book book : books){
            System.out.println(" - " +book.getTitle());
        }

        System.out.println();
        System.out.println("=== Returning Functionality ===");
        boolean returnSuccess = library.returnItem("B100", "S100");
        System.out.println(" Return 'Harry Potter': " +(returnSuccess ? "SUCCESS" : "FAILURE"));
        System.out.println();
        boolean returnFail = library.returnItem("B100","S100");
        System.out.println(" Return 'Harry Potter' again: " +(returnFail ? "SUCCESS" : "FAILURE"));
        System.out.println();


    }
}
