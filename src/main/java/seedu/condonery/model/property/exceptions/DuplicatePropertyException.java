package seedu.condonery.model.property.exceptions;

/**
 * Signals that the operation will result in duplicate Properties (Properties are considered duplicates
 * if they have the same identity).
 */
public class DuplicatePropertyException extends RuntimeException {
    public DuplicatePropertyException() {
        super("Operation would result in duplicate properties");
    }
}
