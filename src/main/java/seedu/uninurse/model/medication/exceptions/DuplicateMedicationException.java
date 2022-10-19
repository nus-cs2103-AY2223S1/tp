package seedu.uninurse.model.medication.exceptions;

/**
 * Signals that the operation will result in duplicate Conditions.
 * Conditions are considered duplicates if they have the same name.
 */
public class DuplicateMedicationException extends RuntimeException {
    public DuplicateMedicationException() {
        super("Operation would result in duplicate medication");
    }
}
