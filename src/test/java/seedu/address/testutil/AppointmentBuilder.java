package seedu.address.testutil;

import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.Doctor;
import seedu.address.model.appointment.MedicalTest;
import seedu.address.model.appointment.Slot;
import seedu.address.model.patient.Name;

/**
 * A utility class to help with building Appointment objects.
 */
public class AppointmentBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_MEDICAL_TEST = "Computed Tomography";
    public static final String DEFAULT_SLOT = "2022-10-10 16:08";
    public static final String DEFAULT_DOCTOR = "Shashank S Joshi";

    private Name name;
    private MedicalTest medicalTest;
    private Slot slot;
    private Doctor doctor;

    /**
     * Creates a {@code AppointmentBuilder} with the default details.
     */
    public AppointmentBuilder() {
        name = new Name(DEFAULT_NAME);
        medicalTest = new MedicalTest(DEFAULT_MEDICAL_TEST);
        slot = new Slot(DEFAULT_SLOT);
        doctor = new Doctor(DEFAULT_DOCTOR);
    }

    /**
     * Initializes the AppointmentBuilder with the data of {@code appointmentToCopy}.
     */
    public AppointmentBuilder(Appointment appointmentToCopy) {
        name = appointmentToCopy.getName();
        medicalTest = appointmentToCopy.getMedicalTest();
        slot = appointmentToCopy.getSlot();
        doctor = appointmentToCopy.getDoctor();
    }

    /**
     * Sets the {@code Name} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code slot} into a {@code Slot} and set it to the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withSlot(String slot) {
        this.slot = new Slot(slot);
        return this;
    }

    /**
     * Parses the {@code medicalTest} into a {@code MedicalTest}
     * and set it to the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withMedicalTest(String medicalTest) {
        this.medicalTest = new MedicalTest(medicalTest);
        return this;
    }

    /**
     * Parses the {@code doctor} into a {@code Doctor}
     * and set it to the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withDoctor(String doctor) {
        this.doctor = new Doctor(doctor);
        return this;
    }

    public Appointment build() {
        return new Appointment(name, medicalTest, slot, doctor);
    }

}
