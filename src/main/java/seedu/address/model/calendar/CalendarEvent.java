package seedu.address.model.calendar;

import java.time.LocalDateTime;
import java.time.LocalTime;

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

    public LocalTime getTime() {
        return localDateTime.toLocalTime();
    }

    public String toString() {
        return String.format("%s, %s", getTime().toString(), this.name);
    }


}
