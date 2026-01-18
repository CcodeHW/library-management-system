package th.rosenheim.oop;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Chuan Hn Wong - 1084380
 * Main library management class that manages library items and library users
 */
public class Library {
    private Map<String, LibraryItem> items;
    private Map<String, LibraryUser> users;

    /**
     * Constructor for Library
     * creates a new library with empty collections
     * initializes the item and user maps
     */
    public Library(){
        this.items = new HashMap<>();
        this.users = new HashMap<>();
    }

    // === Getters ===
    /**
     * Gets a copy of the library item maps
     * the copy prevents external modification of the internal map
     * @return copy of the items map (Item ID -> LibraryItem)
     */
    public Map<String, LibraryItem> getItems(){
        return new HashMap<>(items);
    }
    /**
     * Gets a copy of the library user maps
     * the copy prevents external modification of the internal map
     * @return copy of the users map (User ID -> LibraryUser)
     */
    public Map<String, LibraryUser> getUsers(){
        return new HashMap<>(users);
    }

    // === Item Management ===
    /**
     * Add a new item to the library - item and its ID is added
     * @param item Item to add
     * @throws IllegalArgumentException if item is null or has duplicate ID
     */
    public void addItem(LibraryItem item){
        if(item == null){
            throw new IllegalArgumentException("Item cannot be null");
        }
        if(items.containsKey(item.getId())){
            throw new IllegalArgumentException("Item ID already exists: " +item.getId());
        }
        this.items.put(item.getId(), item);
    }

    /**
     * Remove an item from the library
     * @param itemId ID of item to be removed
     * @return true if item has been successfully removed, false otherwise
     */
    public boolean removeItem(String itemId){
        return items.remove(itemId) != null;
    }

    // === User Management ===
    /**
     * Register for a new user
     * @param user new library user to be registered
     * @throws IllegalArgumentException if user is null or has duplicate ID
     */
    public void registerUser(LibraryUser user){
        if(user == null){
            throw new IllegalArgumentException("User cannot be null");
        }
        if(users.containsKey(user.getUserId())){
            throw new IllegalArgumentException("User ID already exists");
        }
        users.put(user.getUserId(),user);
    }

    /**
     * Borrow an item from the library for a specific user
     * Validates that both the item and user exist and the item is borrowable
     * @param itemId ID of the borrowed item
     * @param userId ID of the user borrowing the item
     * @return true if item is successfully borrowed, false otherwise
     * @throws IllegalArgumentException if itemId or userId is null/empty
     * @throws UserNotFoundException if user is not found
     * @throws ItemNotFoundException if item is not found
     */
    public boolean borrowItem(String itemId, String userId) {
        // validate inputs
        if(itemId == null || itemId.trim().isEmpty()){
            throw new IllegalArgumentException("Item ID cannot be null or empty");
        }
        if(userId == null || userId.trim().isEmpty()){
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }

        // check if user exists
        LibraryUser user = users.get(userId);
        if(user == null){
            throw new UserNotFoundException(userId);
        }

        // check if borrowed item exists
        LibraryItem itemToBeBorrowed = items.get(itemId);
        if(itemToBeBorrowed == null){
            throw new ItemNotFoundException(itemId);
        }

        // check if item is borrowable
        if(!(itemToBeBorrowed instanceof Borrowable)){
            throw new IllegalArgumentException("Item with ID: " +itemId+ " is not borrowable");
        }

        // check if the user and item is in the system
        Borrowable borrowable = (Borrowable) itemToBeBorrowed;
        if (borrowable.borrow(userId)) {
            user.borrowItem(itemToBeBorrowed);
            return true;
        }
        return false;
    }

    /**
     * Return an item to the library for a specific user
     * Validates that both the item and user exist and the item is borrowable
     * @param itemId ID of item to be returned
     * @param userId ID of the user returning the item
     * @return true if the item is successfully returned, false otherwise
     * @throws IllegalArgumentException if itemId or userId is null/empty
     * @throws UserNotFoundException if user is not found
     * @throws ItemNotFoundException if item is not found
     */
    public boolean returnItem (String itemId, String userId){
        // validate inputs
        if(itemId == null || itemId.trim().isEmpty()){
            throw new IllegalArgumentException("Item ID cannot be null or empty");
        }
        if(userId == null || userId.trim().isEmpty()){
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }

        // check if user exists
        LibraryUser user = users.get(userId);
        if(user == null){
            throw new UserNotFoundException(userId);
        }

        // check if returned item exists
        LibraryItem itemToBeReturned = items.get(itemId);
        if(itemToBeReturned == null){
            throw new ItemNotFoundException(itemId);
        }

        // check if item is borrowable
        if(!(itemToBeReturned instanceof Borrowable)){
            throw new IllegalArgumentException("Item with ID: " +itemId+ " is not borrowable");
        }

        // check if user and item is in the system
        Borrowable borrowable = (Borrowable) itemToBeReturned;
        if(borrowable.returnItem()){
            user.returnItem(itemToBeReturned);
            return true;
        }
        return false;
    }

    // === Search Methods ===
    /**
     * Search library items by title
     * @param title Title of the library item to search for
     * @return list of matching items, empty if no matches
     */
    public List<LibraryItem> searchByTitle(String title){
        return items.values().stream().filter(
                item -> item.getTitle().toLowerCase().contains(title.toLowerCase())
                        ).collect(Collectors.toList());
    }

    /**
     * Search library items by author
     * @param author Author of the library item to search for
     * @return List of matching items, empty if no matches
     */
    public List<LibraryItem> searchByAuthor(String author){
        return items.values().stream().filter(
                item -> item.getAuthor().toLowerCase().contains(author.toLowerCase())
                        ).collect(Collectors.toList());
    }

    /**
     * Get all available items
     * @return List of available items
     */
    public List<LibraryItem> getAvailableItems(){
        return items.values().stream().filter(
                LibraryItem::isAvailable
                ).collect(Collectors.toList());
    }

    /**
     * Get all borrowed items
     * @return List of all borrowed items
     */
    public List<LibraryItem> getBorrowedItems(){
        return items.values().stream().filter(
                item -> !item.isAvailable()
                ).collect(Collectors.toList());
    }
}
