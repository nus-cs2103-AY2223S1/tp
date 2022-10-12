package seedu.address.model.person.tutor.exceptions;

/**
 * Signals that the operation will result in duplicate Tutors (Tutors are considered duplicates
 * if they have the same identity).
 */
public class DuplicateTutorException extends RuntimeException {
    public DuplicateTutorException() {
        super("Operation would result in duplicate tutors");
    }
}
