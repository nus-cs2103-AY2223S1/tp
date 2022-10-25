package seedu.address.wrapper.exceptions;

/**
 * Represents an error which occurs when getting a link.
 */
public class NetworkConnectionException extends RuntimeException {

    public NetworkConnectionException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code RepoNotFoundException} with the specified detail {@code message} and {@code cause}.
     */
    public NetworkConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
