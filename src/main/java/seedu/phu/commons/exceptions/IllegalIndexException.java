package seedu.phu.commons.exceptions;

/**
 * Signals that an Index object doesn't fulfill the range constraint.
 */
public class IllegalIndexException extends Exception {
    public IllegalIndexException() {
        super("The given Index object is invalid!");
    }
}
