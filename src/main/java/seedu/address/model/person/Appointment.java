package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DATE_FORMAT;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a person's Appointment in the address group.
 */
public class Appointment {
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    private final LocalDateTime appointmentDate;

    /**
     * Constructs an appointment.
     *
     * @param appointmentDate Date of the appointment.
     */
    public Appointment(String appointmentDate) {
        requireNonNull(appointmentDate);
        checkArgument(isValidDate(appointmentDate), MESSAGE_INVALID_DATE_FORMAT);
        this.appointmentDate = LocalDateTime.parse(appointmentDate, DATE_FORMAT);
    }

    /**
     * Returns true if date is valid.
     *
     * @param testDate Date to be tested.
     * @return True if valid.
     */
    public static boolean isValidDate(String testDate) {
        try {
            LocalDateTime.parse(testDate, DATE_FORMAT);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Appointment date getter.
     *
     * @return The record date.
     */
    public String getAppointmentDate() {
        return appointmentDate.format(DATE_FORMAT);
    }

    @Override
    public String toString() {
        return appointmentDate.format(DATE_FORMAT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Appointment // instanceof handles nulls
                && appointmentDate.equals(((Appointment) other).appointmentDate));
    }

    @Override
    public int hashCode() {
        return appointmentDate.hashCode();
    }
}
