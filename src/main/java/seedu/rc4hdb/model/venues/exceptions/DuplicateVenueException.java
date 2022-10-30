package seedu.rc4hdb.model.venues.exceptions;

/**
 * Signals that the operation will result in duplicate Venue (Venues are considered duplicates if they have the
 * same identity).
 */
public class DuplicateVenueException extends RuntimeException {
    public DuplicateVenueException() {
        super("Operation would result in duplicate venues");
    }
}
