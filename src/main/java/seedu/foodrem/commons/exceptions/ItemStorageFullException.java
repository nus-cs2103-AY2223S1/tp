package seedu.foodrem.commons.exceptions;

/**
 * Represents an error when more items are added when the storage is full.
 */
public class ItemStorageFullException extends StorageFullException {
    /**
     * Constructs an ItemStorageFullException.
     */
    public ItemStorageFullException(int maxNumberOfItems) {
        super(String.format("The item storage is full. FoodRem can only hold up to %s items.", maxNumberOfItems));
    }

    /**
     * Constructs an ItemStorageFullException.
     */
    public ItemStorageFullException(String message) {
        super(message);
    }
}
