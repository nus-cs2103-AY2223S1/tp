package seedu.address.model.calendar;

import seedu.address.model.person.Appointment;
import seedu.address.model.person.Name;

public class CalendarEvent {

    private Name name;
    private Appointment appointment;


    public CalendarEvent(Name name, Appointment appointment) {
        this.name = name;
        this.appointment = appointment;
    }
}
