package foodwhere.model.stall.exceptions;

/**
 * Signals that the operation will result in duplicate Stalls (Stalls are considered duplicates if they have the same
 * identity).
 */
public class DuplicateStallException extends RuntimeException {
    public DuplicateStallException() {
        super("Operation would result in duplicate stalls");
    }
}
