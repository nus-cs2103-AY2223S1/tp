package seedu.address.model.appointment;

import java.time.LocalDate;

/**
 * Represents an upcoming appointment for a patient.
 */
public class UpcomingAppointment extends Appointment {
    /**
     * Constructs an {@code UpcomingAppointment} for a {@code Patient}.
     * @param date of the appointment
     */
    public UpcomingAppointment(LocalDate date) {
        super(date);
    }
}
