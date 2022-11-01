package seedu.address.model.appointment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.patient.Name;

/**
 * Represents an Appointment of a patient in the HealthConnect
 */
public class Appointment {
    /**
     * The FOREIGN KEY to identify a patient
     */
    private final Name name;
    private final MedicalTest medicalTest;
    private final Slot slot;
    private final Doctor doctor;

    /**
     * Every field must be present and not null.
     */
    public Appointment(Name name, MedicalTest medicalTest, Slot slot, Doctor doctor) {
        requireAllNonNull(name, medicalTest, slot, doctor);
        this.name = name;
        this.medicalTest = medicalTest;
        this.slot = slot;
        this.doctor = doctor;
    }

    public Name getName() {
        return name;
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

    public boolean isSameAppointment(Appointment appointment) {
        return appointment != null
                && this.name.isSameName(appointment.getName())
                && this.doctor.isSameDoctor(appointment.getDoctor())
                && this.medicalTest.isSameMedicalTest(appointment.getMedicalTest())
                && this.slot.equals(appointment.getSlot());
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
                    && name.equals(other.getName());
        }
        return false;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Name: ")
                .append(getName())
                .append("; Medical Test: ")
                .append(getMedicalTest())
                .append("; Slot: ")
                .append(getSlot())
                .append("; Doctor: ")
                .append(getDoctor());

        return builder.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, medicalTest, slot, doctor);
    }
}
