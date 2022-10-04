package seedu.address.model.poc.exceptions;

/**
 * Signals that the operation will result in duplicate Companies
 * (Companies are considered duplicates if they have the same identity).
 */
public class DuplicatePoCException extends RuntimeException {
    public DuplicatePoCException() {
        super("Operation would result in duplicate companies");
    }
}
