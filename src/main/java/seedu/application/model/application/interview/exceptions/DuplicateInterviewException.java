package seedu.application.model.application.interview.exceptions;

/**
 * Signals that the operation will result in duplicate Interviews (Interviews are considered duplicates
 * if they have the same InterviewTime and InterviewDate).
 */
public class DuplicateInterviewException extends RuntimeException {
    public DuplicateInterviewException() {
        super("Operation would result in duplicate interviews");
    }
}
