package seedu.address.model.appointment.exceptions;

/**
 * Signals that the patient is busy
 */
public class PatientIsBusyException extends Exception {
    public PatientIsBusyException() {
        super("This patient is busy");
    }
}
