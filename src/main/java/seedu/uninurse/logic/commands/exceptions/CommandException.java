package seedu.uninurse.logic.commands.exceptions;

/**
 * Represents an error which occurs during execution of a Command.
 */
public class CommandException extends Exception {
    /**
     * Constructs a new CommandException with the specified detail message.
     *
     * @param message The given detail message.
     */
    public CommandException(String message) {
        super(message);
    }

    /**
     * Constructs a new CommandException with the specified detail message and cause.
     *
     * @param message The given detail message.
     * @param cause The given cause.
     */
    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }
}
