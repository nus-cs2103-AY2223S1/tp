package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.person.Person.MAXIMUM_APPOINTMENTS;

import java.util.Objects;

import seedu.address.logic.parser.DateTimeParser;
import seedu.address.logic.util.MaximumSortedList;

/**
 * Represents one of the Person's appointments.
 * Guarantees: immutable;
 */
public class Appointment implements Comparable<Appointment> {
    public static final String MESSAGE_CONSTRAINTS = "Appointments can only take in a date and "
            + "time in the format, d-MMM-yyyy hh:mm a, and it should not be blank";
    public static final MaximumSortedList<Appointment> EMPTY_APPOINTMENTS =
            new MaximumSortedList<>(MAXIMUM_APPOINTMENTS);

    private final DateTime dateTime;

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
    public static boolean isValidAppointment(DateTime dateTime) {
        return DateTimeParser.isValidDateTime(dateTime.toString());
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public Date getDate() {
        return dateTime.getDate();
    }

    public Time getTime() {
        return dateTime.getTime();
    }

    @Override
    public int compareTo(Appointment other) {
        return this.dateTime.compareTo(other.dateTime);
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
