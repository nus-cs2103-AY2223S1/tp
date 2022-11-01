package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Set;

import seedu.address.model.tag.Medication;

/**
 * Represents a completed appointment for a patient.
 * Guarantees: immutable as it is a sensitive record.
 */
public class PastAppointment extends Appointment {
    private final Set<Medication> medication;
    private final String diagnosis;

    /**
     * Constructs a {@code PastAppointment} for a {@code Patient}.
     * @param date of the appointment
     * @param medication the medications prescribed to the patient
     * @param diagnosis the doctor's analysis of the patient's state
     */
    public PastAppointment(LocalDate date, Set<Medication> medication, String diagnosis) {
        super(date);
        requireNonNull(diagnosis);
        this.medication = medication;
        this.diagnosis = diagnosis;
    }

    /**
     * Returns true if a given string is a valid date; false otherwise.
     */
    public static boolean isValidDate(String test) {
        if (test.equals("")) {
            return true;
        }
        // A Past Appointment can only be today or earlier.
        if (Appointment.isValidDate(test)) {
            LocalDate date = parseLocalDate(test);
            return date.isBefore(LocalDate.now()) || date.isEqual(LocalDate.now());
        }
        return false;
    }

    /**
     * Getter for the medications prescribed in the appointment.
     * @returns prescription for the patient
     */
    public Set<Medication> getMedication() {
        return this.medication;
    }

    /**
     * Getter for the doctor's diagnosis.
     * @return diagnosis for the patient
     */
    public String getDiagnosis() {
        return this.diagnosis;
    }

    /**
     * Returns a string representation of the medications.
     */
    public String getMedicationString() {
        StringBuilder sb = new StringBuilder("Medication: ");
        getMedication().stream().sorted(Comparator.comparing(medication -> medication.medicationName))
                .forEach(medication -> sb.append(medication.medicationName).append(", "));
        // remove trailing comma
        sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("On: ").append(getDate().format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
        builder.append("; Diagnosis: ").append(getDiagnosis());
        Set<Medication> tags = getMedication();
        if (!tags.isEmpty()) {
            builder.append("; Prescribed Medication: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PastAppointment // instanceof handles nulls
                && getDate().equals(((PastAppointment) other).getDate())
                && getMedication().equals(((PastAppointment) other).getMedication())
                && getDiagnosis().equals(((PastAppointment) other).getDiagnosis())); // state check
    }
}
