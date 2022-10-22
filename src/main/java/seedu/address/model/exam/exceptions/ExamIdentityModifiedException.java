package seedu.address.model.exam.exceptions;

/**
 * Signals that the operation will modify the identity of the exam.
 */
public class ExamIdentityModifiedException extends RuntimeException {
    public ExamIdentityModifiedException() {
        super("Operation would change exam identity");
    }
}
