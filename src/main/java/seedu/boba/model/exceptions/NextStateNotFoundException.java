package seedu.boba.model.exceptions;

/**
 * Represents a redo error where the BobaBot is at the most updated state.
 */
public class NextStateNotFoundException extends RuntimeException {
    public NextStateNotFoundException() {
        super("No next state found! You are at the most updated state!");
    }
}
