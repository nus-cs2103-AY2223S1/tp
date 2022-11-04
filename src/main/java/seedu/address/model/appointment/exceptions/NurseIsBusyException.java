package seedu.address.model.appointment.exceptions;

import seedu.address.model.person.Date;

/**
 * Signals that the nurse is busy
 */
public class NurseIsBusyException extends Exception {
    /**
     * Nurse is busy exception constructor
     *
     * @param date The date that the nurse is busy on
     */
    public NurseIsBusyException(Date date) {
        super(String.format(
                "The nurse is unavailable on this day %s. Please assign another nurse",
                date));
    }
}
