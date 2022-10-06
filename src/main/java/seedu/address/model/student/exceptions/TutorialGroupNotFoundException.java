package seedu.address.model.student.exceptions;

/**
 * Signals that the operation will result in duplicate Persons (Persons are considered duplicates if they have the same
 * identity).
 */
public class TutorialGroupNotFoundException extends RuntimeException {
    public TutorialGroupNotFoundException() {
        super("The specified tutorial group is not found.");
    }
}
