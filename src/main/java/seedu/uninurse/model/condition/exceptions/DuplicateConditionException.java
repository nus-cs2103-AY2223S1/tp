package seedu.uninurse.model.condition.exceptions;

/**
 * Signals that the operation will result in duplicate Conditions.
 * Conditions are considered duplicates if they have the same name.
 */
public class DuplicateConditionException extends RuntimeException {
    public DuplicateConditionException() {
        super("Operation would result in duplicate conditions");
    }
}
