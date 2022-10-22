package jeryl.fyp.model.student.exceptions;

/**
 * Signals that the operation will result in duplicate Deadlines (Deadlines are considered duplicates if they have the
 * same name).
 */
public class DuplicateDeadlineException extends RuntimeException {
    public DuplicateDeadlineException() {
        super("Operation would result in duplicate deadlines");
    }
}
