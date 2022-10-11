package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

import javafx.beans.Observable;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Represents the patient's appointments' details.
 */
public class Appointment {
    public static final String REASON_MESSAGE_CONSTRAINTS = "Reason should not be empty";
    public static final String DATE_MESSAGE_CONSTRAINTS = "Date should contain YYYY-MM-DD and HH:MM values";
    public static final DateTimeFormatter DATE_FORMATTER =
            new DateTimeFormatterBuilder().appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                    .appendOptional(DateTimeFormatter.ofPattern("HH:mm yyyy-MM-dd")).toFormatter();

    public static final DateTimeFormatter STORAGE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final SimpleStringProperty reason;
    private final SimpleObjectProperty<LocalDateTime> dateTime;
    private final SimpleBooleanProperty isMarked;
    private final SimpleObjectProperty<Person> patient = new SimpleObjectProperty<>(this, "patient");

    private final DateTimeFormatter stringFormatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");


    /**
     * Creates an appointment object with the given reason, dateTime string, and status.
     *
     * @param reason The given reason for appointment.
     * @param dateTime The given time to book the appointment.
     * @param isMarked Status of the appointment.
     */
    public Appointment(String reason, String dateTime, boolean isMarked) {
        requireNonNull(reason);
        requireNonNull(dateTime);
        checkArgument(isValidReason(reason), REASON_MESSAGE_CONSTRAINTS);
        checkArgument(isValidDateTime(dateTime), DATE_MESSAGE_CONSTRAINTS);
        this.reason = new SimpleStringProperty(reason);
        String str = String.join(" ", dateTime.split("\\s+", 2));
        this.dateTime = new SimpleObjectProperty<>(this, "dateTime", LocalDateTime.parse(str, DATE_FORMATTER));
        this.isMarked = new SimpleBooleanProperty(isMarked);
    }

    /**
     * Creates an appointment object with the given reason, LocalDateTime dateTime, and status.
     *
     * @param reason The given reason for appointment.
     * @param dateTime The given time to book the appointment.
     * @param isMarked Status of the appointment.
     */
    public Appointment(String reason, LocalDateTime dateTime, boolean isMarked) {
        this.reason = new SimpleStringProperty(reason);
        this.dateTime = new SimpleObjectProperty<>(this, "dateTime", dateTime);
        this.isMarked = new SimpleBooleanProperty(isMarked);
    }

    /**
     * Checks whether the given appointment has the same time.
     *
     * @param other The given appointment.
     * @return The result of the equals test.
     */
    public boolean isSameTime(Appointment other) {
        return other.dateTime.get().equals(dateTime.get());
    }

    /**
     * Checks whether the given string is a valid reason.
     *
     * @param test The string to test.
     * @return The result of the equals test.
     */
    public static boolean isValidReason(String test) {
        return !test.equals("");
    }

    /**
     * Checks whether the given string is a valid DateTime.
     *
     * @param test The string to test.
     * @return The result of the LocalDateTime parse test.
     */
    public static boolean isValidDateTime(String test) {
        try {
            String str = String.join(" ", test.split("\\s+", 2));
            LocalDateTime.parse(str, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public LocalDateTime getDateTime() {
        return dateTime.get();
    }

    public String getFormattedDateTime() {
        return dateTime.get().format(stringFormatter);
    }

    public String getReason() {
        return reason.get();
    }

    public boolean isMarked() {
        return isMarked.get();
    }

    public void mark() {
        this.isMarked.set(true);
    }

    public void unmark() {
        this.isMarked.set(false);
    }

    public void setPatient(Person patient) {
        this.patient.set(patient);
    }

    public Person getPatient() {
        return patient.get();
    }

    public String getPatientName() {
        return this.patient.get().getName().fullName;
    }

    public String getStatus() {
        return "[" + getStateIcon() + "]";
    }

    @Override
    public String toString() {
        return getStatus() + " " + getFormattedDateTime() + " for " + reason.get();
    }

    private String getStateIcon() {
        String markedIcon = "✅";
        String unmarkedIcon = "❌";
        return isMarked.get() ? markedIcon : unmarkedIcon;
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

        return otherAppointment.patient.get().getName().equals(patient.get().getName())
                && otherAppointment.reason.get().equals(reason.get())
                && otherAppointment.dateTime.get().equals(dateTime.get())
                && (otherAppointment.isMarked.get() == isMarked.get());
    }

    /**
     * Returns true if both appointments have the same reason, date, time and status.
     * This defines a weaker notion of equality between two appointments.
     */
    public boolean isSameAppointment(Appointment appointment) {
        return appointment.reason.get().equals(reason.get())
                && appointment.dateTime.get().equals(dateTime.get())
                && (appointment.isMarked.get() == isMarked.get());
    }

    public Observable[] getProperties() {
        return new Observable[] {isMarked, reason, dateTime, patient};
    }
}
