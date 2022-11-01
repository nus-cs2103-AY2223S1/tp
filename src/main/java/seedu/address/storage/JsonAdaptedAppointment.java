package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.Doctor;
import seedu.address.model.appointment.MedicalTest;
import seedu.address.model.appointment.Slot;
import seedu.address.model.patient.Name;

/**
 * Jackson-friendly version of {@link seedu.address.model.appointment.Appointment}.
 */
class JsonAdaptedAppointment {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Appointment's %s field is missing!";

    private final String name;
    private final String slot;
    private final String medicalTest;
    private final String doctor;

    /**
     * Constructs a {@code JsonAdaptedPatient} with the given patient details.
     */
    @JsonCreator
    public JsonAdaptedAppointment(@JsonProperty("name") String name, @JsonProperty("medicalTest") String medicalTest,
                                  @JsonProperty("slot") String slot, @JsonProperty("doctor") String doctor) {
        this.name = name;
        this.slot = slot;
        this.medicalTest = medicalTest;
        this.doctor = doctor;
    }

    /**
     * Converts a given {@code Appointment} into this class for Jackson use.
     */
    public JsonAdaptedAppointment(Appointment source) {
        name = source.getName().fullName;
        slot = source.getSlot().toString();
        medicalTest = source.getMedicalTest().toString();
        doctor = source.getDoctor().toString();
    }

    /**
     * Converts this Jackson-friendly adapted appointment object into the model's {@code Appointment} object.
     *
     * @throws seedu.address.commons.exceptions.IllegalValueException if there were any data constraints
     *      violated in the adapted appointment.
     */
    public Appointment toModelType() throws IllegalValueException {

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelEmail = new Name(name);

        if (medicalTest == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    MedicalTest.class.getSimpleName()));
        }
        if (!MedicalTest.isValidMedicalTest(medicalTest)) {
            throw new IllegalValueException(MedicalTest.MESSAGE_CONSTRAINTS);
        }
        final MedicalTest modelMedicalTest = new MedicalTest(medicalTest);

        if (slot == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Slot.class.getSimpleName()));
        }
        if (!Slot.isValidSlot(slot)) {
            throw new IllegalValueException(Slot.MESSAGE_CONSTRAINTS);
        }
        final Slot modelSlot = new Slot(slot);

        if (doctor == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Doctor.class.getSimpleName()));
        }
        if (!Doctor.isValidDoctorName(doctor)) {
            throw new IllegalValueException(Doctor.MESSAGE_CONSTRAINTS);
        }
        final Doctor modelDoctor = new Doctor(doctor);

        return new Appointment(modelEmail, modelMedicalTest, modelSlot, modelDoctor);
    }

}
