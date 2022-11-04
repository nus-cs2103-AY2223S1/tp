package seedu.address.model.exam.exceptions;

/**
 * Signals that the operation is unable to find linked tasks for the specified exam.
 */
public class NoLinkedTaskForExamException extends RuntimeException {
    public NoLinkedTaskForExamException() {
        super("Operation cannot find any linked tasks for the specified exam");
    }
}
