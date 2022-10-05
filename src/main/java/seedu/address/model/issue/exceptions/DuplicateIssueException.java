package seedu.address.model.issue.exceptions;

/**
 * Signals that the operation will result in duplicate Issues (Issues are considered duplicates if they have the same
 * identity).
 */
public class DuplicateIssueException extends RuntimeException {
    public DuplicateIssueException() {
        super("Operation would result in duplicate issues");
    }
}
