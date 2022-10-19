package seedu.condonery.logic.commands.exceptions;

import seedu.condonery.logic.commands.CommandQueue;

/**
 * Represents an error which occurs when trying to pop from empty {@link CommandQueue}.
 */
public class EmptyQueueException extends Exception {
    public EmptyQueueException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code CommandException} with the specified detail {@code message} and {@code cause}.
     */
    public EmptyQueueException(String message, Throwable cause) {
        super(message, cause);
    }
}
