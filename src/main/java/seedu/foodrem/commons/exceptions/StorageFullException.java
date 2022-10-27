package seedu.foodrem.commons.exceptions;

/**
 * Represents an error when the storage is full.
 */
public class StorageFullException extends RuntimeException {
    public StorageFullException(String message) {
        super(message);
    }
}
