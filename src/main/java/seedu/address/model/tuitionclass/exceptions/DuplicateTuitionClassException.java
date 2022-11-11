package seedu.address.model.tuitionclass.exceptions;

/**
 * Signals that the operation will result in duplicate tuition classes (tuition classes are considered duplicates
 * if they have the same data).
 */
public class DuplicateTuitionClassException extends RuntimeException {
    public DuplicateTuitionClassException() {
        super("Operation would result in duplicate tuition classes");
    }
}
