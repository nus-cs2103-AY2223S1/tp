package seedu.address.model.module.schedule.exceptions;

/**
 * Signals that the operation will result in conflicts among Slots
 */
public class ConflictSlotException extends RuntimeException {
    public ConflictSlotException() {
        super("Operation results in conflict of slots");
    }
}
