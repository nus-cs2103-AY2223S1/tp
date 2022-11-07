package seedu.condonery.model.property.exceptions;

/**
 * Signals that the operation is unable to find a unique property.
 */
public class UniquePropertyNotFoundException extends RuntimeException {
    public UniquePropertyNotFoundException() {
        super("Operation cannot find a unique property");
    }
}
