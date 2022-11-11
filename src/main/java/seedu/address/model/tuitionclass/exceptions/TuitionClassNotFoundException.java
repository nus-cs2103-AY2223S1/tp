package seedu.address.model.tuitionclass.exceptions;

/**
 * Signals that the operation is unable to find the specified tuition class.
 */
public class TuitionClassNotFoundException extends RuntimeException {

    public TuitionClassNotFoundException() {
        super("Tuition class does not exist.");
    }

}
