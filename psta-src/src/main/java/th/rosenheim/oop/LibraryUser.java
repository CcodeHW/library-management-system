package th.rosenheim.oop;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chuan Hn Wong - 1084380
 * Class representing a library user
 */
public class LibraryUser {
    private String userId;
    private String name;
    private String email;
    private List<LibraryItem> borrowedItems;

    /**
     * Constructor for LibraryUser
     * @param userId user ID (unique identifier)
     * @param name user name
     * @param email user email
     */
    public LibraryUser(String userId, String name, String email){
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.borrowedItems = new ArrayList<>();
    }

    // === Getters ===
    /**
     * Gets the user's id
     * @return userId
     */
    public String getUserId(){
        return userId;
    }
    /**
     * Gets the user's name
     * @return name
     */
    public String getName(){
        return name;
    }
    /**
     * Gets the user's email
     * @return email
     */
    public String getEmail(){
        return email;
    }
    /**
     * Gets the list of the borrowed items
     * @return borrowedItems
     */
    public List<LibraryItem> getBorrowedItems(){
        return borrowedItems;
    }

    /**
     * Add an item to user's borrowed items
     * @param item Item to borrow
     * @throws IllegalArgumentException if item is null or not borrowable
     */
    public void borrowItem(LibraryItem item){
        if(item == null){
            throw new IllegalArgumentException("Item cannot be null");
        }
        if(!(item instanceof Borrowable)){
            throw new IllegalArgumentException("Item is not borrowable");
        }
        borrowedItems.add(item);
    }

    /**
     * Remove an item from the list of the user's borrowed items
     * @param item Item to return
     * @throws IllegalArgumentException if item is null
     * @throws IllegalStateException if item was not borrowed by this user
     */
    public void returnItem(LibraryItem item){
        if(item == null){
            throw new IllegalArgumentException("Item cannot be null");
        }
        if(!borrowedItems.contains(item)){
            throw new IllegalStateException("Item was not borrowed by this user");
        }
        borrowedItems.remove(item);
    }

    /**
     * Return a string representation of the user's details
     * @return string representation of the user
     */
    @Override
    public String toString(){
        return String.format("ID: %s, Name: %s, Email: %s, Borrowed Items: %d",
                userId, name, email, borrowedItems.size());
    }

    /**
     * Check if the user is equal to another user
     * Two users are equal if they have the same ID
     * @param obj the object to compare with
     * @return true if objects have the same user ID, false otherwise
     */
    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        LibraryUser that = (LibraryUser) obj;
        return this.userId.equals(that.getUserId());
    }

    /**
     * Returns a hash code based on the user's ID
     * All users with the same ID will have the same hash code
     * @return hash code value
     */
    @Override
    public int hashCode(){
        return userId.hashCode();
    }
}
