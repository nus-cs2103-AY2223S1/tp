package seedu.taassist.logic.commands.exceptions;

import seedu.taassist.logic.commands.actions.StorageAction;

/**
 * Represents an error which occurs during action of a {@link StorageAction}.
 */
public class StorageActionException extends CommandException {

    public StorageActionException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code StorageActionException} with the specified detail {@code message} and {@code cause}.
     */
    public StorageActionException(String message, Throwable cause) {
        super(message, cause);
    }
}
