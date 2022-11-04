package seedu.address.model.appointment;

import java.time.LocalDateTime;

import seedu.address.model.appointment.enums.AppointmentSlotNumber;
import seedu.address.model.person.Date;

/**
 * Represents the datetime of an appointment between a patient and a nurse.
 */
public class AppointmentDateTime implements Comparable<AppointmentDateTime> {
    private final Date date;
    private final AppointmentSlotNumber slotNumber;

    /**
     * AppointmentDateTime constructor
     *
     * @param date       The date of the appointment
     * @param slotNumber The slot number of the appointment
     */
    public AppointmentDateTime(Date date, AppointmentSlotNumber slotNumber) {
        this.date = date;
        this.slotNumber = slotNumber;
    }

    /**
     * Exports the appointment datetime as a LocalDateTime object
     *
     * @return The corresponding LocalDateTime object
     */
    public LocalDateTime export() {
        return LocalDateTime.of(date.getDate(), slotNumber.getTime());
    }

    @Override
    public int compareTo(AppointmentDateTime o) {
        return export().compareTo(o.export());
    }

    public boolean isToday() {
        return date.equals(Date.today());
    }

    public Date getDate() {
        return date;
    }
}
