package seedu.address.model.appointment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an upcoming appointment for a patient.
 */
public class UpcomingAppointment extends Appointment {
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
            this.value = date.format(DateTimeFormatter.ofPattern("dd-MM-uuuu"));
        }
    }
    /**
     * Constructs an {@code UpcomingAppointment} for a {@code Patient}.
     * @param dateString string representation of date of the appointment
     */
    public UpcomingAppointment(String dateString) {
        super(dateString);
        this.value = dateString;
    }

    /**
     * Returns true if a given string is a valid date; false otherwise.
     */
    public static boolean isValidDate(String test) {
        if (test.equals("")) {
            return true;
        }
        // An Upcoming Appointment can only be today or later.
        if (Appointment.isValidDate(test)) {
            LocalDate date = parseLocalDate(test);
            return date.isEqual(LocalDate.now()) || date.isAfter(LocalDate.now());
        }
        return false;
    }

    @Override
    public String toString() {
        return "Upcoming Appointment Date: " + (value == null || value.equals("") ? "None" : value);
    }
}
