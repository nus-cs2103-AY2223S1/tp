package jeryl.fyp.model.student.exceptions;

/**
 * Signals that the operation is unable to find the specified deadline.
 */
public class DeadlineNotFoundException extends RuntimeException {
    public DeadlineNotFoundException(String s) {
        super(s);
    }
    public DeadlineNotFoundException() {
        super("Deadline not found for this student.");
    }
}
