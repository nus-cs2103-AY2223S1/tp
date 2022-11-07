package jeryl.fyp.model.student.exceptions;

/**
 * Signals that the operation is unable to find the specified student.
 */
public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String s) {
        super(s);
    }
    public StudentNotFoundException() {
        super("Student not found!");
    }
}
