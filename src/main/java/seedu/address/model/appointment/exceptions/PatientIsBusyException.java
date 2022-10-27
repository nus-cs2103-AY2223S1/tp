package seedu.address.model.appointment.exceptions;

public class PatientIsBusyException extends Exception {
    public PatientIsBusyException() {
        super("This patient is busy");
    }
}
