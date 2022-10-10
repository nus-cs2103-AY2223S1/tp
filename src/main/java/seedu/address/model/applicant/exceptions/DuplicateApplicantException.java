package seedu.address.model.applicant.exceptions;

/**
 * Signals that the operation will result in duplicate Applicants (Applicants are considered duplicates if they have the same
 * identity).
 */
public class DuplicateApplicantException extends RuntimeException {
    public DuplicateApplicantException() {
        super("Operation would result in duplicate applicants");
    }
}
