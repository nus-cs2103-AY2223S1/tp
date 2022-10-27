package seedu.boba.model.exceptions;

/**
 * Represents an undo error where the BobaBot is at the initialised state.
 */
public class PreviousStateNotFoundException extends RuntimeException {
    public PreviousStateNotFoundException() {
        super("No previous state found! You are at the initial state!");
    }
}
