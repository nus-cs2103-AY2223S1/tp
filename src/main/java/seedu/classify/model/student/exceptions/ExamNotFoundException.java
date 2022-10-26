package seedu.classify.model.student.exceptions;

/**
 * Signals that the operation is unable to find the specified exam
 */
public class ExamNotFoundException extends RuntimeException {
    public ExamNotFoundException() {
        super("There are missing grades for this particular exam");
    }
}
