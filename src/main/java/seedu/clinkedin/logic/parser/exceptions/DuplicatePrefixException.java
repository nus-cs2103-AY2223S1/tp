package seedu.clinkedin.logic.parser.exceptions;

/**
 * Signals that the operation will result in duplicate prefixes.
 */
public class DuplicatePrefixException extends RuntimeException {
    public DuplicatePrefixException() {
        super("Operation would result in duplicate prefixes");
    }
}
