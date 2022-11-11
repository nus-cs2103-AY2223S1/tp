package seedu.modquik.model.tutorial.exceptions;

/**
 * Signals that the operation will result in duplicate Tutorials (Tutorials are considered duplicates
 * if they have the same identity).
 */
public class DuplicateTutorialException extends RuntimeException {
    public DuplicateTutorialException() {
        super("Operation would result in duplicate tutorials");
    }
}
