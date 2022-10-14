package seedu.address.model.appointment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an upcoming appointment for a patient.
 */
public class UpcomingAppointment extends Appointment {
    public static final String MESSAGE_CONSTRAINTS = "Date string should be of the format ddMMyy.";
    public final String value;
    /**
     * Constructs an {@code UpcomingAppointment} for a {@code Patient}.
     * @param date of the appointment
     */
    public UpcomingAppointment(LocalDate date) {
        super(date);
        if (date == null) {
            this.value = null;
        } else {
            this.value = date.format(DateTimeFormatter.ofPattern("ddMMyy"));
        }
    }
    /**
     * Constructs an {@code UpcomingAppointment} for a {@code Patient}.
     * @param dateString string representation of date of the appointment
     */
    public UpcomingAppointment(String dateString) {
        super(LocalDate.parse(dateString, DateTimeFormatter.ofPattern("ddMMyy")));
        this.value = dateString;
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        if (test == null) {
            return true;
        }
        try {
            LocalDate.parse(test, DateTimeFormatter.ofPattern("ddMMyy"));
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "Upcoming Appointment Date: " + (value == null ? "None" : value);
    }
}
