package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

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

    private final DateTime dateTime;
    private final Location location;

    public DateTime getDateTime() {
        return dateTime;
    }

    public Location getLocation() {
        return location;
    }

    /**
     * Constructs an {@code Appointment}.
     *
     * @param dateTime A valid appointment.
     */
    public Appointment(DateTime dateTime, Location location) {
        requireNonNull(dateTime);
        this.dateTime = dateTime;
        this.location = location;
    }

    @Override
    public String toString() {
        return dateTime.toString() + ", " + location.toString();
    }

    /**
     * Checks whether the input DateTime has the correct format
     *
     * @param dateTime the DateTime representing the DateTime to be
     *        contained within this Appointment.
     * @return boolean value describing whether the input DateTime has
     *         the correct format.
     */
    public static boolean isValidAppointment(DateTime dateTime, Location location) {
        return DateTimeParser.isValidDateTime(dateTime.toString())
                && Location.isValidLocation(location.toString());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Appointment// instanceof handles nulls
                && dateTime.equals(((Appointment) other).dateTime)
                && location.equals(((Appointment) other).location)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime);
    }
}
