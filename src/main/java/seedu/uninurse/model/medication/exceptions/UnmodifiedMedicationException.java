package seedu.uninurse.model.medication.exceptions;

/**
 * Signals that the operation will result in duplicate Conditions.
 * Conditions are considered duplicates if they have the same name.
 */
public class UnmodifiedMedicationException extends RuntimeException {
    public UnmodifiedMedicationException() {
        super("Operation did not modify existing medication");
    }
}
