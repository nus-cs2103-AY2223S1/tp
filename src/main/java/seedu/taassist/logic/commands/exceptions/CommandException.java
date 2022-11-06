package seedu.taassist.logic.commands.exceptions;

import seedu.taassist.logic.commands.Command;

/**
 * Represents an error which occurs during execution of a {@link Command}.
 */
public class CommandException extends Exception {
    public CommandException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code CommandException} with the specified detail {@code message} and {@code cause}.
     *
     * @param message Error message shown to the user.
     * @param cause Cause of the error.
     */
    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }
}
