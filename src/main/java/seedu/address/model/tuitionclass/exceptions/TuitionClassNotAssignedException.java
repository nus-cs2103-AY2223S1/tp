package seedu.address.model.tuitionclass.exceptions;

/**
 * Signals that the operation is unable to find the tuition class assigned to student or tutor.
 */
public class TuitionClassNotAssignedException extends RuntimeException {

    public TuitionClassNotAssignedException() {
        super("The tuition class is not assigned to this person");
    }
}
