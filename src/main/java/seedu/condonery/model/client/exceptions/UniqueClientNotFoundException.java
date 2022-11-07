package seedu.condonery.model.client.exceptions;

/**
 * Signals that the operation is unable to find a unique client.
 */
public class UniqueClientNotFoundException extends RuntimeException {
    public UniqueClientNotFoundException() {
        super("Operation cannot find a unique client");
    }
}
