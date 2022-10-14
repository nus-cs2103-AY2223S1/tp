package seedu.address.model.appointment;

import java.time.LocalDate;

/**
 * Represents an appointment for a patient.
 */
public abstract class Appointment {
    private final LocalDate date;

    /**
     * Constructs an {@code Appointment} for a {@code Patient}.
     * @param date of the appointment
     */
    public Appointment(LocalDate date) {
        this.date = date;
    }

    /**
     * Returns date of {@code Appointment}.
     * @return the date of the appointment
     */
    public LocalDate getDate() {
        return this.date;
    }
}
