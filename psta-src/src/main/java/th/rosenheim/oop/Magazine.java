package th.rosenheim.oop;
import java.time.LocalDate;

/**
 * @author Chuan Hn Wong - 1084380
 * Concrete class - Magazine in the library
 */
public class Magazine extends LibraryItem implements Borrowable{
    private String issn;
    private int issueNumber;
    private String publisher;
    private String borrowedBy;
    private LocalDate dueDate;

    /**
     * Constructor for Magazine
     * @param id magazine id (unique identifier)
     * @param title magazine title
     * @param author magazine author
     * @param issn magazine ISSN
     * @param issueNumber magazine issue number
     * @param publisher magazine publisher
     */
    public Magazine(String id, String title, String author, String issn, int issueNumber, String publisher){
        super(id, title, author);
        this.issn = issn;
        this.issueNumber = issueNumber;
        this.publisher = publisher;
    }

    // === Getters ===
    /**
     * Gets magazine's issn
     * @return ISSN
     */
    public String getIssn(){
        return issn;
    }
    /**
     * Gets magazine's issue number
     * @return issue number
     */
    public int getIssueNumber(){
        return issueNumber;
    }
    /**
     * Gets magazine's publisher
     * @return publisher
     */
    public String getPublisher(){
        return publisher;
    }
    /**
     * Gets the magazine's current borrower's ID
     * @return borrower's ID
     */
    public String getBorrower(){
        return borrowedBy;
    }

    /**
     * Gets detailed information about the magazine
     * provides more information than toString()
     * @return detailed string representation of the magazine
     */
    @Override
    public String getDetails(){
        return String.format("Magazine: %s, ID: %s Author: %s, ISSN: %s, Issue Number: %d, Publisher: %s",
                getTitle(), getId(), getAuthor(), issn, issueNumber, publisher);
    }

    /**
     * Borrows this magazine for a specific user based on the entered userId
     * marks the magazine as unavailable and sets a due date 1 week from now after the magazine has been borrowed
     * @param userId the unique identifier of the user borrowing the magazine
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
            dueDate = LocalDate.now().plusWeeks(1);
            return true;
        }
        return false;
    }

    /**
     * Returns this magazine back to the library
     * marks the magazine as available and clears the borrower's information
     * @throws IllegalStateException if magazine is not borrowed and cannot be returned
     */
    @Override
    public boolean returnItem(){
        if(!isAvailable()){
            setAvailable(true);
            borrowedBy = null;
            dueDate = null;
            return true;
        } else {
            throw new IllegalStateException("Magazine is not borrowed and cannot be returned");
        }
    }

    /**
     * Gets the due date for this magazine that has been borrowed
     * @return due date as a string in ISO format (YYYY-MM-DD) or "Magazine not borrowed" if not borrowed
     */
    @Override
    public String getDueDate(){
        // if due date is not null: return due date ; if due date is null: "Magazine Not Borrowed".
        return dueDate != null ? dueDate.toString(): "Magazine Not Borrowed";
    }

    /**
     * Return a string representation of the magazine
     * includes magazine-specific details
     * @return string representation of the magazine
     */
    @Override
    public String toString(){
        return String.format("Magazine ID: %s, Title: %s, ISSN: %s, Availability: %s ",
                getId(), getTitle(), issn, isAvailable());
    }
}

