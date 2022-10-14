package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.logic.parser.DateTimeParser;

/**
 * Represents one of the Person's appointments.
 * Guarantees: immutable;
 */
public class Appointment {
    public static final String MESSAGE_CONSTRAINTS = "Appointments can only take in a date and time in the format,"
            + "d-MMM-yyyy hh:mm a, and it should not be blank";
    public static final Set<Appointment> EMPTY_APPOINTMENTS = new HashSet<>();

    public final DateTime dateTime;

    /**
     * Constructs an {@code Appointment}.
     *
     * @param dateTime A valid appointment.
     */
    public Appointment(DateTime dateTime) {
        requireNonNull(dateTime);
        this.dateTime = dateTime;
    }

    public LocalDateTime getLocalDateTime() {
        return this.dateTime.getLocalDateTime();
    }

    public LocalDate getLocalDate() {
    return this.dateTime.getLocalDateTime().toLocalDate();
    }

    @Override
    public String toString() {
        return dateTime.toString();
    }

    /**
     * Checks whether the input DateTime has the correct format
     *
     * @param dateTime the DateTime representing the DateTime to be
     *        contained within this Appointment.
     * @return boolean value describing whether the input DateTime has
     *         the correct format.
     */
    public boolean isValidAppointment(DateTime dateTime) {
        return DateTimeParser.isValidDateTime(dateTime.toString());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Appointment// instanceof handles nulls
                && dateTime.equals(((Appointment) other).dateTime)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime);
    }
}
