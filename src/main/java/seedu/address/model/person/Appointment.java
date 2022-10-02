package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents one of the Person's appointments.
 * Guarantees: immutable;
 */
public class Appointment {
    public static final String MESSAGE_CONSTRAINTS = "Appointments can only take in a date and time in the format,"
            + "d-MMM-yyyy hh:mm a, and it should not be blank";

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

    @Override
    public String toString() {
        return "Appointment at " + dateTime;
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
