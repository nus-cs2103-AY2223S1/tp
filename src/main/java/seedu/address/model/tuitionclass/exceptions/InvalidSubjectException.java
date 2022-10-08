package seedu.address.model.tuitionclass.exceptions;

/**
 * Signals that the operation takes in an invalid string input for Subject.
 */
public class InvalidSubjectException extends RuntimeException {

    /**
     * Constructor for an InvalidSubjectException.
     */
    public InvalidSubjectException() {
        super("Please give the subject name spelt out in full.\n"
                + "e.g. Mathematics");
    }
}
