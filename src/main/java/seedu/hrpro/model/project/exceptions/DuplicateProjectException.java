package seedu.hrpro.model.project.exceptions;

/**
 * Signals that the operation will result in duplicate Projects
 * (Projects are considered duplicates if they have the same
 * identity).
 */
public class DuplicateProjectException extends RuntimeException {
    public DuplicateProjectException() {
        super("Operation would result in duplicate persons");
    }
}
