package seedu.address.model.calendar;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import javafx.collections.ObservableList;


/**
 * Represents a Calendar in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class CalendarMonth {

    // Data fields
    private ObservableList<CalendarEvent> calendarEvents;


    /**
     * Every field must be present and not null.
     */
    public CalendarMonth(ObservableList<CalendarEvent> calendarEvents) {
        requireAllNonNull(calendarEvents);
        this.calendarEvents = calendarEvents;
    }

    /**
     * Returns a mutable appointment set.
     */
    public ObservableList<CalendarEvent> getCalendarEvents() {
        return calendarEvents;
    }


    public void setAppointments(ObservableList<CalendarEvent> newCalendarEvents) {

        this.calendarEvents = newCalendarEvents;
    }


    /**
     * Returns true if both calendar month data field.
     * This defines a stronger notion of equality between two calendar month.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof CalendarMonth)) {
            return false;
        }

        CalendarMonth otherCalendar = (CalendarMonth) other;
        return otherCalendar.getCalendarEvents().equals(getCalendarEvents());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(calendarEvents);
    }

}
