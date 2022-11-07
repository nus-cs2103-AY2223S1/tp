package seedu.uninurse.logic.commands.exceptions;

/**
 * Represents an error which occurs when an invalid attribute index is given in the command.
 */
public class InvalidAttributeIndexException extends CommandException {
    /**
     * Constructs a new InvalidAttributeIndexException with the specified detail message.
     *
     * @param message The given detail message.
     */
    public InvalidAttributeIndexException(String message) {
        super(message);
    }
}
