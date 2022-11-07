package seedu.waddle.model.itinerary.exceptions;

/**
 * Signals that the operation will result in duplicate Itineraries.
 */
public class DuplicateItineraryException extends RuntimeException {
    public DuplicateItineraryException() {
        super("Operation would result in duplicate itineraries");
    }
}
