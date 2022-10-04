package seedu.address.model.meeting.exceptions;

/**
 * Signals that the operation will result in conflicting {@code Meeting}.
 */
public class ConflictingMeetingException extends RuntimeException {
    public ConflictingMeetingException() {
        super("Operation would result in conflicting meetings");
    }
}
