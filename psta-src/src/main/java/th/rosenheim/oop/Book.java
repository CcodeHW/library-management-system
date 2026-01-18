package th.rosenheim.oop;

import java.time.LocalDate;

/**
 * @author Chuan Hn Wong - 1084380
 * Concrete class - Book in library
 * implements 'Comparable' for natural ordering by title
 */
public class Book extends LibraryItem implements Borrowable,Comparable<Book> {
    private String isbn;
    private String genre;
    private String borrowedBy;
    private LocalDate dueDate;

    /**
     * Constructor for Book
     * @param id book id (unique identifier)
     * @param title book title
     * @param author book author
     * @param isbn book isbn
     * @param genre book genre
     */
    public Book(String id, String title, String author, String isbn, String genre){
        super(id, title, author);
        this.isbn = isbn;
        this.genre = genre;
    }

    // === Getters ===
    /**
     * Gets book's ISBN
     * @return ISBN
     */
    public String getIsbn(){
        return isbn;
    }
    /**
     * Gets book's genre
     * @return genre
     */
    public String getGenre(){
        return genre;
    }
    /**
     * Gets the book's current borrower's ID
     * @return borrower's ID
     */
    public String getBorrower(){
        return borrowedBy;
    }

    /**
     * Gets detailed information about the book
     * provides more information than toString()
     * @return detailed string representation of the book
     */
    @Override
    public String getDetails(){
        return String.format("Book: %s, ID: %s, Author: %s, Genre: %s, ISBN: %s , Availability: %s",
                getTitle(), getId(), getAuthor(), genre, isbn, isAvailable());
    }

    /**
     * Borrows this book for a specific user based on the entered userId
     * marks the book as unavailable and sets a due date 2 weeks from now after the book has been borrowed
     * @param userId the unique identifier of the user borrowing the book
     * @return true if item has been successfully borrowed, false otherwise
     * @throws IllegalArgumentException if user ID is null or empty
     */
    @Override
    public boolean borrow(String userId){
        if(userId == null || userId.trim().isEmpty()){
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }
        if(isAvailable()){
            setAvailable(false);
            borrowedBy = userId;
            dueDate = LocalDate.now().plusWeeks(2);
            return true;
        }
        return false;
    }

    /**
     * Returns this book back to the library
     * marks the book as available and clears the borrower's information
     * @return true if book is successfully returned, false otherwise
     */
    @Override
    public boolean returnItem() {
        if (!isAvailable()) {
            setAvailable(true);
            borrowedBy = null;
            dueDate = null;
            return true;
        }
        return false;
    }

    /**
     * Gets the due date for this book that has been borrowed
     * @return due date as a string in ISO format (YYYY-MM-DD) or "Book not borrowed" if not borrowed
     */
    @Override
    public String getDueDate(){
        // if due date is not null: return due date ; if due date is null: "Book Not Borrowed".
        return dueDate != null ? dueDate.toString(): "Book Not Borrowed";
    }

    /**
     * Compares this book to another book by title for ordering where comparison is case-insensitive
     * @param other the book to compare to
     * @return integer - zero/negative/positive
     * this book's title comes before/is equal to/comes after other book's title
     */
    @Override
    public int compareTo(Book other){
        return this.getTitle().compareToIgnoreCase(other.getTitle());
    }

    /**
     * Return a string representation of the book
     * includes book-specific details
     * @return string representation of the book
     */
    @Override
    public String toString(){
        return String.format("Book ID: %s, Title: %s, ISBN: %s, Availability: %s ",
                getId(), getTitle(), isbn, isAvailable());
    }
}


