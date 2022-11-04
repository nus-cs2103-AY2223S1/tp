package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.person.Person.MAXIMUM_NUM_OF_APPOINTMENTS;

import java.util.Objects;

import seedu.address.logic.parser.DateTimeParser;
import seedu.address.model.util.MaximumSortedList;

/**
 * Represents one of the Person's appointments.
 * Guarantees: immutable;
 */
public class Appointment implements Comparable<Appointment> {
    public static final String MESSAGE_CONSTRAINTS = "Appointments can only take in a date and "
            + "time in the format, d-M-yyyy HH:mm, and it should not be blank. \n"
            + "e.g \"d/01-03-2023 18:00\" represents 1-Mar-2022, 6:00 PM";
    public static final MaximumSortedList<Appointment> EMPTY_APPOINTMENTS =
            new MaximumSortedList<>(MAXIMUM_NUM_OF_APPOINTMENTS);

    private final DateTime dateTime;
    private final Location location;

    /**
     * Constructs an {@code Appointment}.
     *
     * @param dateTime A valid appointment.
     */
    public Appointment(DateTime dateTime, Location location) {
        requireNonNull(dateTime);
        requireNonNull(location);
        this.dateTime = dateTime;
        this.location = location;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public Location getLocation() {
        return location;
    }

    public Date getDate() {
        return dateTime.getDate();
    }

    public Time getTime() {
        return dateTime.getTime();
    }

    public String getTimeFormat() {
        return dateTime.getTimeFormat();
    }

    public int getDay() {
        return dateTime.getDay();
    }

    public int getMonth() {
        return dateTime.getMonth();
    }

    public int getYear() {
        return dateTime.getYear();
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
    public int compareTo(Appointment other) {
        return this.dateTime.compareTo(other.dateTime);
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
        return Objects.hash(dateTime, location);
    }
}
