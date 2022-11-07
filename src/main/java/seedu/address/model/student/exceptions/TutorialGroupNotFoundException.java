package seedu.address.model.student.exceptions;

/**
 * Signals that the operation will result in TutorialGroup not found (TutorialGroup are considered not found if they
 * are null).
 */
public class TutorialGroupNotFoundException extends RuntimeException {
    public TutorialGroupNotFoundException() {
        super("The specified tutorial group is not found.");
    }
}
