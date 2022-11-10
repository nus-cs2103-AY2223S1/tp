package seedu.address.model.iteration.exceptions;

/**
 * Signals that the operation will result in duplicate Iterations (Iterations are considered duplicates if they have
 * the same identity).
 */
public class DuplicateIterationException extends RuntimeException {
    public DuplicateIterationException() {
        super("Operation would result in duplicate iterations");
    }
}
