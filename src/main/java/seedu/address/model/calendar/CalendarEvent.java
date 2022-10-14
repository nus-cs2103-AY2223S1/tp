package seedu.address.model.calendar;

import java.time.LocalDateTime;
import java.time.LocalTime;

import seedu.address.model.person.Appointment;
import seedu.address.model.person.Name;

public class CalendarEvent {

    private Name name;
    private Appointment appointment;
    private LocalDateTime localDateTime;


    public CalendarEvent(Name name, Appointment appointment) {
        this.name = name;
        this.appointment = appointment;
        this.localDateTime = this.appointment.getLocalDateTime();
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
