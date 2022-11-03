package seedu.address.model.appointment.exceptions;

/**
 * Signals that the nurse is busy
 */
public class NurseIsBusyException extends Exception {
    public NurseIsBusyException() {
        super("This nurse is busy");
    }

    public NurseIsBusyException(String string) {
        super(string);
    }
}
