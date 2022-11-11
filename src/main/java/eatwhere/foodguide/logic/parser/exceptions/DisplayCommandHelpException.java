package eatwhere.foodguide.logic.parser.exceptions;

/**
 * Represents a call to display the help message of a command
 * for supported commands.
 */
public class DisplayCommandHelpException extends Exception {

    public DisplayCommandHelpException(String message) {
        super(message);
    }

    public DisplayCommandHelpException(String message, Throwable cause) {
        super(message, cause);
    }
}
