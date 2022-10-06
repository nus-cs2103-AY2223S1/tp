package seedu.address.model.appointment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.person.Email;
import seedu.address.model.tag.Tag;

import java.util.Set;

/**
 * Represents an Appointment of a patient in the HealthConnect
 */
public class Appointment {
    /**
     * The FOREIGN KEY to identify a patient
     */
    private final Email email;
    private final MedicalTest medicalTest;
    private final Slot slot;
    private final Doctor doctor;

    /**
     * Every field must be present and not null.
     */
    public Appointment(Email email, MedicalTest medicalTest, Slot slot, Doctor doctor) {
        requireAllNonNull(email, medicalTest, slot, doctor);
        this.email = email;
        this.medicalTest = medicalTest;
        this.slot = slot;
        this.doctor = doctor;
    }

    public Email getEmail() {
        return email;
    }

    public MedicalTest getMedicalTest() {
        return medicalTest;
    }

    public Slot getSlot() {
        return slot;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof Appointment) {
            Appointment other = (Appointment) o;
            return slot.equals(other.getSlot())
                    && medicalTest.equals(other.getMedicalTest())
                    && doctor.equals(other.getDoctor())
                    && email.equals(other.getEmail());
        }
        return false;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Appointment Email: ")
                .append(getEmail())
                .append("; Medical Test: ")
                .append(getMedicalTest())
                .append(" Slot: ")
                .append(getSlot())
                .append(" Doctor: ")
                .append(getDoctor());

        return builder.toString();
    }
}
