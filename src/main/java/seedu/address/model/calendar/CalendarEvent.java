package seedu.address.model.calendar;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.Location;
import seedu.address.model.person.Name;


/**
 * Represents a CalendarEvent in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class CalendarEvent implements Comparable<CalendarEvent> {

    private final Name name;
    private final Appointment appointment;

    /**
     * Creates a CalendarEvent with the given name and appointment.
     */
    public CalendarEvent(Name name, Appointment appointment) {
        requireAllNonNull(name, appointment);

        this.name = name;
        this.appointment = appointment;
    }

    public Name getName() {
        return name;
    }

    public Location getLocation() {
        return appointment.getLocation();
    }

    public String getDate() {
        return appointment.getDate().toString();
    }

    public int getDay() {
        return this.appointment.getDay();
    }

    public int getMonth() {
        return this.appointment.getMonth();
    }

    public int getYear() {
        return this.appointment.getYear();
    }

    public String getTimeFormat() {
        return this.appointment.getTimeFormat();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) { // short circuit if same object
            return true;
        }

        if (!(other instanceof CalendarEvent)) { // instanceof handles nulls
            return false;
        }
        CalendarEvent otherEvent = (CalendarEvent) other;
        return name.equals(otherEvent.name)
                && appointment.equals(otherEvent.appointment); // state check
    }

    @Override
    public String toString() {
        return String.format("%s, %s", getTimeFormat(), this.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, appointment);
    }

    @Override
    public int compareTo(CalendarEvent other) {
        return appointment.compareTo(other.appointment);
    }


}
