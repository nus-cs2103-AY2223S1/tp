package seedu.address.model.student.exceptions;

/**
 * Signals that the operation cannot proceed because the {@code GradeKey} is already present in the map
 */
public class DuplicateGradeKeyException extends RuntimeException {
    public DuplicateGradeKeyException() {
        super("A student-task association can not have multiple grade values!");
    }
}
