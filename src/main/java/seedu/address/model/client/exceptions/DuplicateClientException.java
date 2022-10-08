package seedu.address.model.client.exceptions;

/**
 * Signals that the operation will result in duplicate clients. Clients are considered as duplicate if they have the
 * same identity.
 */

public class DuplicateClientException extends RuntimeException{

    /**
     * Alerts the user with respect to creating duplicate clients.
     */
    public DuplicateClientException() {
        super("Operation would result in duplicate clients");
    }
}
