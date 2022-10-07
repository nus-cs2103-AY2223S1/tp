package seedu.address.model.person.tutor.exceptions;

import seedu.address.model.person.exceptions.DuplicatePersonException;

/**
 * Signals that the operation will result in duplicate Tutors (Tutors are considered duplicates if they have the same
 * identity).
 */
public class DuplicateTutorException extends RuntimeException {
    public DuplicateTutorException() {
        super("Operation would result in duplicate persons");
    }
}
