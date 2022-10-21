package seedu.uninurse.model.medication.exceptions;

/**
 * Signals that the operation did not modify an existing duplicate Medication.
 * Medications are considered duplicates if they have the same medication type and dosage amount.
 */
public class UnmodifiedMedicationException extends RuntimeException {
    public UnmodifiedMedicationException() {
        super("Operation did not modify existing medication");
    }
}
