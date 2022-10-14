package seedu.address.model.ta.exceptions;

/**
 * Signals that the operation will result in duplicate Teaching Assistants
 * (Teaching Assistants are considered duplicates if they have the same identity).
 */
public class DuplicateTeachingAssistantException extends RuntimeException {
    public DuplicateTeachingAssistantException() {
        super("Operation would result in duplicate teaching assistants!");
    }
}
