package seedu.clinkedin.logic.parser.exceptions;

/**
 * Signals that an invalid file extension has been entered.
 */
public class InvalidExtensionException extends RuntimeException {
    public InvalidExtensionException() {
        super("Invalid Extension! Only .csv, .json, .txt, .xml extensions allowed!");
    }
}
