package jarvis.model.exceptions;

/**
 * Signals that the operation will result in duplicate Students.
 */
public class DuplicateStudentException extends RuntimeException {
    public DuplicateStudentException() {
        super("Operation would result in duplicate students");
    }
}
