package seedu.address.model.appointment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an appointment for a patient.
 */
public abstract class Appointment {
    public static final String MESSAGE_CONSTRAINTS = "Date string should be of the format dd-MM-yyyy.";
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

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        if (test == null) {
            return true;
        }
        try {
            LocalDate.parse(test, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}
