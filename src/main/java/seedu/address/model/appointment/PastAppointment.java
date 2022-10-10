package seedu.address.model.appointment;

import java.time.LocalDate;
import java.util.Set;

import seedu.address.model.tag.Medication;

/**
 * Represents a completed appointment for a patient.
 * Guarantees: immutable as it is a sensitive record.
 */
public class PastAppointment extends Appointment {
    private final Set<Medication> prescription;
    private final String diagnosis;

    /**
     * Constructs a {@code PastAppointment} for a {@code Patient}.
     * @param date of the appointment
     * @param prescription the medications prescribed to the patient
     * @param diagnosis the doctor's analysis of the patient's state
     */
    public PastAppointment(LocalDate date, Set<Medication> prescription, String diagnosis) {
        super(date);
        this.prescription = prescription;
        this.diagnosis = diagnosis;
    }

    /**
     * Getter for the medications prescribed in the appointment.
     * @returns prescription for the patient
     */
    public Set<Medication> getPrescription() {
        return this.prescription;
    }

    /**
     * Getter for the doctor's diagnosis.
     * @return diagnosis for the patient
     */
    public String getDiagnosis() {
        return this.diagnosis;
    }
}
