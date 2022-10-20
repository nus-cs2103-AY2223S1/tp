package seedu.application.model.application.interview.exceptions;

/**
 * Signals that the operation will result in invalid Interviews (Interview is considered invalid
 * if it is before its Application's applied date).
 */
public class InvalidInterviewException extends RuntimeException {
    public InvalidInterviewException() {
        super("Interview date should be later than applied date");
    }
}
