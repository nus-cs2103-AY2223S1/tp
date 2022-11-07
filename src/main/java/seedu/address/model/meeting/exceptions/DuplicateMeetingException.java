package seedu.address.model.meeting.exceptions;

/**
 * Signals that the operation will result in duplicate Meeting (Meetings are considered duplicates if they have the same
 * identity).
 */
public class DuplicateMeetingException extends RuntimeException {
    public DuplicateMeetingException() {
        super("Operation would result in duplicate meetings");
    }
}
