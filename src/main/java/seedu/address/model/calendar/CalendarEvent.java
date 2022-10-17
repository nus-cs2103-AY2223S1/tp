package seedu.address.model.calendar;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Objects;

import seedu.address.model.person.Appointment;
import seedu.address.model.person.Name;


/**
 * Represents a CalendarEvent in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class CalendarEvent {

    private Name name;
    private Appointment appointment;
    private LocalDateTime localDateTime;

    /**
     * Creates a CalendarEvent with the given name and appointment.
     */
    public CalendarEvent(Name name, Appointment appointment) {
        requireAllNonNull(name);
        requireAllNonNull(appointment);

        this.name = name;
        this.appointment = appointment;
        this.localDateTime = this.appointment.getLocalDateTime();
    }

    public Name getName() {
        return name;
    }

    public int getDay() {
        return localDateTime.getDayOfMonth();
    }

    public int getMonth() {
        return localDateTime.getMonthValue();
    }

    public String getTime() {
        return localDateTime.format(java.time.format
                .DateTimeFormatter.ofPattern("hh:mm a"));
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
        return String.format("%s, %s", getTime(), this.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, appointment);
    }


}
