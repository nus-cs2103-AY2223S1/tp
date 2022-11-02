package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.HealthContact;
import seedu.address.model.ReadOnlyHealthContact;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.bill.Bill;
import seedu.address.model.patient.Patient;

/**
 * An Immutable HealthContact that is serializable to JSON format.
 */
@JsonRootName(value = "healthcontact")
class JsonSerializableHealthContact {

    public static final String MESSAGE_DUPLICATE_PATIENT = "Patients list contains duplicate patient(s).";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "Appointment list contains duplicate appointment(s).";
    public static final String MESSAGE_DUPLICATE_BILL = "Appointment list contains duplicate bill(s).";
    public static final String MESSAGE_APPOINTING_PATIENT_NOT_EXIST =
            "Appointing patient does not exist in the patients list.";
    public static final String MESSAGE_APPOINTMENT_NOT_EXIST =
            "Appointment of a bill does not exist in the appointments list.";

    private final List<JsonAdaptedPatient> patients = new ArrayList<>();
    private final List<JsonAdaptedAppointment> appointments = new ArrayList<>();
    private final List<JsonAdaptedBill> bills = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableHealthContact} with the given patients and appointments.
     */
    @JsonCreator
    public JsonSerializableHealthContact(@JsonProperty("patients") List<JsonAdaptedPatient> patients,
                                       @JsonProperty("appointments") List<JsonAdaptedAppointment> appointments,
                                       @JsonProperty("bills") List<JsonAdaptedBill> bills) {
        this.patients.addAll(patients);
        this.appointments.addAll(appointments);
        this.bills.addAll(bills);
    }

    /**
     * Converts a given {@code ReadOnlyHealthContact} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableHealthContact}.
     */
    public JsonSerializableHealthContact(ReadOnlyHealthContact source) {
        patients.addAll(source.getPatientList().stream().map(JsonAdaptedPatient::new).collect(Collectors.toList()));
        appointments.addAll(source.getAppointmentList().stream()
                .map(JsonAdaptedAppointment::new).collect(Collectors.toList()));
        bills.addAll(source.getBillList().stream().map(JsonAdaptedBill::new).collect(Collectors.toList()));
    }

    /**
     * Converts this HealthContact into the model's {@code HealthContact} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public HealthContact toModelType() throws IllegalValueException {
        HealthContact healthContact = new HealthContact();
        for (JsonAdaptedPatient jsonAdaptedPatient : patients) {
            Patient patient = jsonAdaptedPatient.toModelType();
            if (healthContact.hasPatient(patient)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PATIENT);
            }
            healthContact.addPatient(patient);
        }
        for (JsonAdaptedAppointment jsonAdaptedAppointment : appointments) {
            Appointment appointment = jsonAdaptedAppointment.toModelType();
            if (healthContact.hasAppointment(appointment)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_APPOINTMENT);
            }
            if (!healthContact.hasPatient(appointment.getName())) {
                throw new IllegalValueException(MESSAGE_APPOINTING_PATIENT_NOT_EXIST);
            }
            healthContact.addAppointment(appointment);
        }
        for (JsonAdaptedBill jsonAdaptedBill : bills) {
            Bill bill = jsonAdaptedBill.toModelType();
            if (healthContact.hasBill(bill)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_BILL);
            }
            if (!healthContact.hasAppointment(bill.getAppointment())) {
                throw new IllegalValueException(MESSAGE_APPOINTMENT_NOT_EXIST);
            }
            healthContact.addBill(bill);
        }
        return healthContact;
    }
}
