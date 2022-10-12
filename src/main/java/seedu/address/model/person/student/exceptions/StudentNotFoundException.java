package seedu.address.model.person.student.exceptions;

/**
 * Signals that the operation is unable to find the specified student.
 */
public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException() {
        super("Operation could not find the specified student");
    }
}
