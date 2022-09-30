package seedu.address.model.person;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Represents the patient's appointments' details.
 */
public class Appointment {
    public static final String REASON_MESSAGE_CONSTRAINTS = "Reason should not be empty";
    public static final String DATE_MESSAGE_CONSTRAINTS = "Date should contain YYYY-MM-DD and HH:MM values";
    public static final DateTimeFormatter DATE_FORMATTER =
            new DateTimeFormatterBuilder().appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                    .appendOptional(DateTimeFormatter.ofPattern("HH:mm yyyy-MM-dd")).toFormatter();

    @JsonIgnore
    private final DateTimeFormatter stringFormatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
    private String reason;
    private LocalDateTime dateTime;

    public Appointment() {

    }

    /**
     * Creates an appointment object with the given reason and time.
     *
     * @param reason The given reason for appointment.
     * @param dateTime The given time to book the appointment.
     */
    public Appointment(String reason, LocalDateTime dateTime) {
        this.reason = reason;
        this.dateTime = dateTime;
    }

    public boolean isSameTime(Appointment other) {
        return other.dateTime.equals(dateTime);
    }

    @Override
    public String toString() {
        return dateTime.format(stringFormatter) + " for " + reason;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Appointment)) {
            return false;
        }

        Appointment otherAppointment = (Appointment) other;
        return otherAppointment.reason.equals(reason) && otherAppointment.dateTime.equals(dateTime);
    }
}
