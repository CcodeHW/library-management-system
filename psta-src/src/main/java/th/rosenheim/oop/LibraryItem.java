package th.rosenheim.oop;
/**
 * @author Chuan Hn Wong - 1084380
 * Abstract base class for all library items.
 */
public abstract class LibraryItem {
    protected String id;
    protected String title;
    protected String author;
    protected boolean isAvailable;

    /**
     * Constructor for LibraryItem
     * @param id item id (unique identifier)
     * @param title item title
     * @param author item author
     */
    public LibraryItem(String id, String title, String author){
        if(id == null || id.trim().isEmpty()){
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        if(title == null || title.trim().isEmpty()){
            throw new IllegalArgumentException("Title cannot be null or empty");
        }

        this.id = id;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    /**
     * Gets detailed information about this item
     * @return detailed description
     */
    public abstract String getDetails();

    // === Getters & Setters ===

    /**
     * Gets the item's ID
     * @return the ID
     */
    public String getId(){
        return id;
    }
    /**
     * Gets the item's title
     * @return the title
     */
    public String getTitle(){
        return title;
    }
    /**
     * Gets the item's author
     * @return the author
     */
    public String getAuthor(){
        return author;
    }
    /**
     * Checks if item is available
     * @return availability status - true/false
     */
    public boolean isAvailable(){
        return isAvailable;
    }
    /**
     * Sets the availability status
     * @param available new availability status
     */
    public void setAvailable(boolean available){
        isAvailable = available;
    }

    /**
     * String representation of the item
     * @return formatted string
     */
    @Override
    public String toString(){
        return String.format("ID: %s, Title: %s, Author: %s, Available: %s",
                id, title, author, isAvailable);
    }

    /**
     * Check if this library item is equal to another object
     * Two items are equal if they have similar ID
     * @param obj the object to compare with
     * @return true if objects have the same ID, false otherwise.
     */
    @Override
    public boolean equals(Object obj){
        // if 'this' and 'obj' refers/point to the same object memory, they must be equal.
        if(this == obj){
            return true;
        }
        // Compared object must not be null or must be the same class (eg: book to book/DVD to DVD)
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        LibraryItem that = (LibraryItem) obj;
        return this.id.equals(that.id);
    }

    /**
     * Returns a hash code based on the item's ID
     * all objects with the same ID will have the same hash code
     * @return hash code value
     */
    @Override
    public int hashCode(){
        return id.hashCode();
    }
}
