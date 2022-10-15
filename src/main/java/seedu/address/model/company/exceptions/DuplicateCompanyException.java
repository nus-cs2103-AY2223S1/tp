package seedu.address.model.company.exceptions;

/**
 * Signals that the operation will result in duplicate Companies
 * (Companies are considered duplicates if they have the same identity).
 */
public class DuplicateCompanyException extends RuntimeException {
    public DuplicateCompanyException() {
        super("Operation would result in duplicate Company");
    }
}
