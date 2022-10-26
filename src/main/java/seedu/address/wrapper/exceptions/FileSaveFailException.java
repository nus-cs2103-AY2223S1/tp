package seedu.address.wrapper.exceptions;

/**
 * Represents an error which occurs when saving a file to disk.
 */
public class FileSaveFailException extends RuntimeException {

    public FileSaveFailException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code FileSaveFailException} with the specified detail {@code message} and {@code cause}.
     */
    public FileSaveFailException(String message, Throwable cause) {
        super(message, cause);
    }
}
