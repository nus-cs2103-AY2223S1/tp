package seedu.address.logic.parser.exceptions;

/**
 * Signals that the operation is unable to find the specified prefix.
 */
public class PrefixNotFoundException extends RuntimeException {
    public PrefixNotFoundException() {
        super("Prefix to be edited does not exist");
    }
}
