package seedu.address.testutil;

import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_1;

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
 * A utility class to help with building Bill objects.
 */
public class BillBuilder {

    public static final Appointment DEFAULT_APPOINTMENT = APPOINTMENT_1;
    public static final String DEFAULT_BILL_DATE = "2022-11-17";
    public static final String DEFAULT_AMOUNT = "1234.00";
    public static final String DEFAULT_PAYMENT_STATUS = "unpaid";

    private Appointment appointment;
    private BillDate billDate;
    private Amount amount;
    private PaymentStatus paymentStatus;

    /**
     * Creates a {@code BillBuilder} with the default details.
     */
    public BillBuilder() {
        appointment = DEFAULT_APPOINTMENT;
        billDate = new BillDate(DEFAULT_BILL_DATE);
        amount = new Amount(DEFAULT_AMOUNT);
        paymentStatus = new PaymentStatus(DEFAULT_PAYMENT_STATUS);
    }

    /**
     * Initializes the BillBuilder with the data of {@code billToCopy}.
     */
    public BillBuilder(Bill billToCopy) {
        appointment = billToCopy.getAppointment();
        billDate = billToCopy.getBillDate();
        amount = billToCopy.getAmount();
        paymentStatus = billToCopy.getPaymentStatus();
    }

    /**
     * Sets the {@code Appointment} of the {@code Bill} that we are building.
     */
    public BillBuilder withAppointment(String name, String medicalTest, String slot, String doctor) {
        this.appointment = new Appointment(new Name(name), new MedicalTest(medicalTest),
                new Slot(slot), new Doctor(doctor));
        return this;
    }

    /**
     * Sets the {@code Appointment} of the {@code Bill} that we are building.
     */
    public BillBuilder withAppointment(Appointment appointment) {
        this.appointment = appointment;
        return this;
    }

    /**
     * Parses the {@code billDate} into a {@code BillDate} and set it to the {@code Bill} that we are building.
     */
    public BillBuilder withBillDate(String billDate) {
        this.billDate = new BillDate(billDate);
        return this;
    }

    /**
     * Parses the {@code amount} into a {@code Amount}
     * and set it to the {@code Bill} that we are building.
     */
    public BillBuilder withAmount(String amount) {
        this.amount = new Amount(amount);
        return this;
    }

    /**
     * Parses the {@code paymentStatus} into a {@code PaymentStatus}
     * and set it to the {@code Appointment} that we are building.
     */
    public BillBuilder withPaymentStatus(String paymentStatus) {
        this.paymentStatus = new PaymentStatus(paymentStatus);
        return this;
    }

    public Bill build() {
        return new Bill(appointment, amount, billDate, paymentStatus);
    }

}
