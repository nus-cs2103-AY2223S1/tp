package jarvis.model.exceptions;

/**
 * Signals that the operation will result in exceeding the maximum number of students.
 */
public class MaxStudentsExceededException extends RuntimeException {
    public MaxStudentsExceededException(String message) {
        super(message);
    }
}
