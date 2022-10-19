package seedu.uninurse.model.medication.exceptions;

/**
 * Signals that the operation will result in duplicate Medications.
 * Medications are considered duplicates if they have the same medication type and dosage amount.
 */
public class DuplicateMedicationException extends RuntimeException {
    public DuplicateMedicationException() {
        super("Operation would result in duplicate medication");
    }
}
