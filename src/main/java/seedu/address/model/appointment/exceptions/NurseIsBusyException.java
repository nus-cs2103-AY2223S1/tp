package seedu.address.model.appointment.exceptions;

public class NurseIsBusyException extends Exception {
    public NurseIsBusyException() {
        super("This nurse is busy");
    }
}
