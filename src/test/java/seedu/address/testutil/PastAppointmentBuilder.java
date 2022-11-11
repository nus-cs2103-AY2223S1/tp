package seedu.address.testutil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import seedu.address.model.appointment.PastAppointment;
import seedu.address.model.tag.Medication;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building PastAppointment objects.
 */
public class PastAppointmentBuilder {
    public static final String DEFAULT_DATE = "06-06-2020";
    public static final String[] DEFAULT_MEDICATIONS = { "Paracetamol", "Ibuprofen" };
    public static final String DEFAULT_MEDICATION_1 = "Paracetamol";
    public static final String DEFAULT_MEDICATION_2 = "Ibuprofen";
    public static final String DEFAULT_DIAGNOSIS = "Fever";

    private LocalDate date;
    private Set<Medication> medication;
    private String diagnosis;

    /**
     * Creates a {@code PastAppointmentBuilder} with the default details.
     */
    public PastAppointmentBuilder() {
        date = LocalDate.parse(DEFAULT_DATE, DateTimeFormatter.ofPattern("dd-MM-uuuu"));
        medication = SampleDataUtil.getMedicationSet(DEFAULT_MEDICATIONS);
        diagnosis = DEFAULT_DIAGNOSIS;
    }

    /**
     * Initializes the PastAppointmentBuilder with the given date.
     */
    public PastAppointmentBuilder withDate(String date) {
        this.date = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-uuuu"));
        return this;
    }

    /**
     * Initializes the PastAppointmentBuilder with the given medication.
     */
    public PastAppointmentBuilder withMedication(String[] medication) {
        this.medication = SampleDataUtil.getMedicationSet(medication);
        return this;
    }

    /**
     * Initializes the PastAppointmentBuilder with the given diagnosis.
     */
    public PastAppointmentBuilder withDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
        return this;
    }

    /**
     * Builds a PastAppointment object.
     */
    public PastAppointment build() {
        return new PastAppointment(date, medication, diagnosis);
    }
}
