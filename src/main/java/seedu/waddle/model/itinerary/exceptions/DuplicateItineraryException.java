package seedu.waddle.model.itinerary.exceptions;

/**
 * Signals that the operation will result in duplicate Persons (Persons are considered duplicates if they have the same
 * identity).
 */
public class DuplicateItineraryException extends RuntimeException {
    public DuplicateItineraryException() {
        super("Operation would result in duplicate itineraries");
    }
}
