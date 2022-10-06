package seedu.address.model.appointment;

import seedu.address.model.person.Email;

import java.time.LocalDateTime;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents an Appointment of a patient in the HealthConnect
 */
public class Appointment {
    /**
     * The FOREIGN KEY to identify a patient
     */
    private final Email email;
    private final LocalDateTime dateTime;
    private final String doctor;

    public Appointment(Email email, LocalDateTime dateTime, String doctor) {
        requireAllNonNull(email, dateTime, doctor);
        this.email = email;
        this.dateTime = dateTime;
        this.doctor = doctor;
    }

    public Email getEmail() {
        return email;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDoctor() {
        return doctor;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof Appointment) {
            Appointment other = (Appointment) o;
            return dateTime.equals(other.getDateTime())
                    && doctor.equals(other.getDoctor())
                    && email.equals(other.getEmail());
        }
        return false;
    }
}
