package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.Doctor;
import seedu.address.model.appointment.MedicalTest;
import seedu.address.model.appointment.Slot;
import seedu.address.model.bill.Amount;
import seedu.address.model.bill.Bill;
import seedu.address.model.bill.BillDate;
import seedu.address.model.bill.PaymentStatus;
import seedu.address.model.patient.Name;

/**
 * Jackson-friendly version of {@link seedu.address.model.bill.Bill}.
 */
class JsonAdaptedBill {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Bill's %s field is missing!";

    private final String name;
    private final String slot;
    private final String medicalTest;
    private final String doctor;
    private final String billDate;
    private final String amount;
    private final String paymentStatus;

    /**
     * Constructs a {@code JsonAdaptedBill} with the given bill details.
     */
    @JsonCreator
    public JsonAdaptedBill(@JsonProperty("name") String name, @JsonProperty("slot") String slot,
                           @JsonProperty("medicalTest") String medicalTest, @JsonProperty("doctor") String doctor,
                           @JsonProperty("billDate") String billDate,
                           @JsonProperty("amount") String amount,
                           @JsonProperty("paymentStatus") String paymentStatus) {
        this.name = name;
        this.slot = slot;
        this.medicalTest = medicalTest;
        this.doctor = doctor;
        this.billDate = billDate;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
    }

    /**
     * Converts a given {@code Bill} into this class for Jackson use.
     */
    public JsonAdaptedBill(Bill source) {
        name = source.getAppointment().getName().fullName;
        slot = source.getAppointment().getSlot().toString();
        doctor = source.getAppointment().getDoctor().doctorName;
        medicalTest = source.getAppointment().getMedicalTest().medicalTestName;
        amount = source.getAmount().toString();
        billDate = source.getBillDate().toString();
        paymentStatus = source.getPaymentStatus().toString();
    }

    /**
     * Converts this Jackson-friendly adapted bill object into the model's {@code Bill} object.
     *
     * @throws seedu.address.commons.exceptions.IllegalValueException if there were any data constraints
     *      violated in the adapted bill.
     */
    public Bill toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

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

        if (medicalTest == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    MedicalTest.class.getSimpleName()));
        }
        if (!MedicalTest.isValidMedicalTest(medicalTest)) {
            throw new IllegalValueException(MedicalTest.MESSAGE_CONSTRAINTS);
        }
        final MedicalTest modelMedicalTest = new MedicalTest(medicalTest);

        if (amount == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Amount.class.getSimpleName()));
        }
        if (!Amount.isValidAmount(amount)) {
            throw new IllegalValueException(Amount.MESSAGE_CONSTRAINTS);
        }
        final Amount modelAmount = new Amount(amount);

        if (billDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    BillDate.class.getSimpleName()));
        }
        if (!BillDate.isValidDate(billDate)) {
            throw new IllegalValueException(BillDate.MESSAGE_CONSTRAINTS);
        }
        final BillDate modelBillDate = new BillDate(billDate);

        if (paymentStatus == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    PaymentStatus.class.getSimpleName()));
        }
        if (!PaymentStatus.isValidPaymentStatus(paymentStatus)) {
            throw new IllegalValueException(PaymentStatus.MESSAGE_CONSTRAINTS);
        }
        final PaymentStatus modelPaymentStatus = new PaymentStatus(paymentStatus);

        final Appointment modelAppointment = new Appointment(modelName, modelMedicalTest, modelSlot, modelDoctor);
        return new Bill(modelAppointment, modelAmount, modelBillDate, modelPaymentStatus);
    }
}
