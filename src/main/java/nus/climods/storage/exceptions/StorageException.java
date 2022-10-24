package nus.climods.storage.exceptions;

/**
 * Represents an error which occurs during execution of a {@link nus.climods.storage.Storage}.
 */
public class StorageException extends Exception {

    public StorageException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code StorageException} with the specified detail {@code message} and {@code cause}.
     */
    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
