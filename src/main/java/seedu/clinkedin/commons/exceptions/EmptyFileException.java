package seedu.clinkedin.commons.exceptions;

/**
 * Signals that file is empty.
 */
public class EmptyFileException extends Exception {
    public EmptyFileException(String message) {
        super(message);
    }
}
