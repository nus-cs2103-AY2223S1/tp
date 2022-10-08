package seedu.address.model.tuitionclass.exceptions;

public class InvalidSubjectException extends RuntimeException {
    public InvalidSubjectException() {
        super("Please give the subject name spelt out in full.\n" +
                "e.g. Mathematics");
    }
}
