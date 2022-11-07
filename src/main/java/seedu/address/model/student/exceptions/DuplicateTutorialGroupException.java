package seedu.address.model.student.exceptions;

/**
 * Signals that the operation will result in duplicate TutorialGroups (TutorialGroups are considered duplicates if they
 * have the same identity).
 */
public class DuplicateTutorialGroupException extends RuntimeException {
    public DuplicateTutorialGroupException() {
        super("Operation would result in duplicate tutorial groups");
    }
}
