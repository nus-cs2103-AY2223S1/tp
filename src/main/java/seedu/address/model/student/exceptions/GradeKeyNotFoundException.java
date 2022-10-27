package seedu.address.model.student.exceptions;

/**
 * Signals that the operation cannot proceed because the {@code GradeKey} is not present in the map
 */
public class GradeKeyNotFoundException extends RuntimeException {
    public GradeKeyNotFoundException() {
        super("The specified student-task pair is not found");
    }
}
