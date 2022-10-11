package seedu.address.model.person.tutor.exceptions;

public class TutorNotFoundException extends RuntimeException {
    public TutorNotFoundException() {
        super("Operation could not find the specified tutor");
    }
}
