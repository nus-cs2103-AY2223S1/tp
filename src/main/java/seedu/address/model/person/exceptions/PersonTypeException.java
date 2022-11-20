package seedu.address.model.person.exceptions;

/**
 * Signals that the operation is unable to find the specified person.
 */
public class PersonTypeException extends RuntimeException {
    public PersonTypeException() {
        super("The student is not a teaching assistant.");
    }
}
