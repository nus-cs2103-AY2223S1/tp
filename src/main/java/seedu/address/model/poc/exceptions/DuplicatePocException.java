package seedu.address.model.poc.exceptions;

/**
 * Signals that the operation will result in duplicate Pocs
 * (Companies are considered duplicates if they have the same identity).
 */
public class DuplicatePocException extends RuntimeException {
    public DuplicatePocException() {
        super("Operation would result in duplicate Poc");
    }
}
