package jarvis.logic.commands.exceptions;

/**
 * Represents an error which occurs when an invalid mark is supplied to a Command.
 */
public class InvalidMarkException extends RuntimeException {
    public InvalidMarkException() {
        super("Mark cannot be less than 0 or greater than the total mark of the assessment.");
    }
}
