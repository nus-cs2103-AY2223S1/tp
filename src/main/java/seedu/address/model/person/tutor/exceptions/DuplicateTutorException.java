package seedu.address.model.person.tutor.exceptions;

public class DuplicateTutorException extends RuntimeException {
    public DuplicateTutorException() {
        super("Operation would result in duplicate tutors");
    }
}