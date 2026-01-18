package th.rosenheim.oop;

/**
 * @author Chuan Hn Wong - 1084380
 * Exception thrown a user is not found in the system
 */
public class UserNotFoundException extends RuntimeException {

    /**
     * Constructs a new UserNotFoundException with the specific user ID
     * @param userId the ID of the user that was not found
     */
    public UserNotFoundException(String userId){
        super("User ID not found in the system.");
    }

    /**
     * Constructs a new UserNotFoundException with the specified user ID and cause
     * @param userId the ID of the user that was not found
     * @param cause the cause of the exception
     */
    public UserNotFoundException(String userId, Throwable cause){
        super("User ID not found in the system.", cause);
    }
}