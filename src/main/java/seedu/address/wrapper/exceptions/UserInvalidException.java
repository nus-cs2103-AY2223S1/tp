package seedu.address.wrapper.exceptions;

/**
 * Represents an error which occurs when user provided to GitHub API wrapper does not exist.
 */
public class UserInvalidException extends RuntimeException {
    public UserInvalidException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code UserInalidException} with the specified detail {@code message} and {@code cause}.
     */
    public UserInvalidException(String message, Throwable cause) {
        super(message, cause);
    }
}
