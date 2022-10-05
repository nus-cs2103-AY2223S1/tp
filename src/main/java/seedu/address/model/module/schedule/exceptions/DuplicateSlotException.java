package seedu.address.model.module.schedule.exceptions;

/**
 * Signals that the operation will result in duplicate Slots
 */
public class DuplicateSlotException extends RuntimeException {
    public DuplicateSlotException() {
        super("Operation will result in duplicate slots");
    }
}
