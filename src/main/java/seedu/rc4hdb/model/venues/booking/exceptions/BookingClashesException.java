package seedu.rc4hdb.model.venues.booking.exceptions;

/**
 * Signals that the operation will result in duplicate Venue (Venues are considered duplicates if they have the
 * same identity).
 */
public class BookingClashesException extends RuntimeException {
}
