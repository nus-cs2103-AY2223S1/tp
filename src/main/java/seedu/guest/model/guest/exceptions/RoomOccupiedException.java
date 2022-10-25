package seedu.guest.model.guest.exceptions;


/**
 * Signals that the operation will result in duplicate guest rooms (rooms are considered duplicates if they have the same
 * identity).
 */
public class RoomOccupiedException extends RuntimeException {
    public RoomOccupiedException() {
        super("Operation would result in guests occupying the same room.");
    }
}
