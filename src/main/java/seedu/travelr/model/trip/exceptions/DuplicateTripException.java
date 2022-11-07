package seedu.travelr.model.trip.exceptions;

/**
 * Signals that the operation will result in duplicate Trips (Trips are considered duplicates if they have the same
 * title).
 */
public class DuplicateTripException extends RuntimeException {
    public DuplicateTripException() {
        super("Operation would result in duplicate trips");
    }
}
