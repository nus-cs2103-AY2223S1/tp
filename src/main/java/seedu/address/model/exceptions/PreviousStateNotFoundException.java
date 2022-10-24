package seedu.address.model.exceptions;

/**
 * Represents an undo error where the AddressBook is at the initialised state.
 */
public class PreviousStateNotFoundException extends RuntimeException {
    public PreviousStateNotFoundException() {
        super("No previous state found! You are at the initial state!");
    }
}
