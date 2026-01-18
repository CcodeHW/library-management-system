package th.rosenheim.oop;

/**
 * @author Chuan Hn Wong - 1084380
 * Exception thrown when an item is not found in the system
 */

public class ItemNotFoundException extends RuntimeException {

    /**
     * Constructs a new ItemNotFoundException
     * @param itemId the ID of the item that was not found
     */
    public ItemNotFoundException(String itemId){
        super("Item ID not found in the system");
    }

    /**
     * Constructs a new ItemNotFoundException with cause
     * @param itemId the ID of the item that was not found
     * @param cause the cause of the exception
     */
    public ItemNotFoundException(String itemId, Throwable cause){
        super("Item ID not found in the system", cause);
    }
}
