package th.rosenheim.oop;
/**
 * @author Chuan Hn Wong - 1084380
 * Interface for items that can be borrowed and returned
 */
public interface Borrowable {
    /**
     * Method to borrow an item
     * @param userId ID of the user borrowing the item
     * @return true if item is successfully borrowed, false otherwise
     * @throws IllegalArgumentException if userId is null or empty
     */
    boolean borrow(String userId);

    /**
     * Method to return an item
     * @return true if item is successfully returned, false otherwise
     * @throws IllegalArgumentException if the item is currently not borrowed
     */
    boolean returnItem();

    /**
     * Gets the due date for the borrowed item
     * @return Due date in terms of String type
     */
    String getDueDate();

}
