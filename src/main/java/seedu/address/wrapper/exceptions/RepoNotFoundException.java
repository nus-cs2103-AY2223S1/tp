package seedu.address.wrapper.exceptions;

/**
 * Represents an error which occurs when trying to get a repository that does not exist.
 */
public class RepoNotFoundException extends RuntimeException {

    public RepoNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code RepoNotFoundException} with the specified detail {@code message} and {@code cause}.
     */
    public RepoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
