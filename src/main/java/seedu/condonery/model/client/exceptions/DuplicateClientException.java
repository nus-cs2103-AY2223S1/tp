package seedu.condonery.model.client.exceptions;

/**
 * Signals that the operation will result in duplicate Properties (Properties are considered duplicates
 * if they have the same identity).
 */
public class DuplicateClientException extends RuntimeException {
    public DuplicateClientException() {
        super("Operation would result in duplicate properties");
    }
}
