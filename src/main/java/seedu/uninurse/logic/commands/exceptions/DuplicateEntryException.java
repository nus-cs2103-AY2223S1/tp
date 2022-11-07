package seedu.uninurse.logic.commands.exceptions;

/**
 * Represents an error which occurs when the command results in a duplicate entry.
 */
public class DuplicateEntryException extends CommandException {
    /**
     * Constructs a new DuplicateOperationException with the specified detail message.
     *
     * @param message The given detail message.
     */
    public DuplicateEntryException(String message) {
        super(message);
    }
}
