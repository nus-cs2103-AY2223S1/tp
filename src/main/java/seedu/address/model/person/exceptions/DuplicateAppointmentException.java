package seedu.address.model.person.exceptions;

/**
 * Signals that the operation will result in duplicate Appointments
 * (Appointments are considered duplicates if they have the same date, time, reason, patient).
 */
public class DuplicateAppointmentException extends RuntimeException {
    public DuplicateAppointmentException() {
        super("Operation would result in duplicate appointments");
    }
}
