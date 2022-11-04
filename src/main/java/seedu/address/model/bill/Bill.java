package seedu.address.model.bill;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.appointment.Appointment;

/**
 * Represents a bill of a patient in the HealthConnect
 */
public class Bill {

    private final Appointment appointment;
    private final Amount amount;
    private final BillDate billDate;
    private final PaymentStatus paymentStatus;

    /**
     * Every field must be present and not null.
     */
    public Bill(Appointment appointment, Amount amount, BillDate billDate, PaymentStatus paymentStatus) {
        requireAllNonNull(amount, amount, billDate, paymentStatus);
        this.appointment = appointment;
        this.amount = amount;
        this.billDate = billDate;
        this.paymentStatus = paymentStatus;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public Amount getAmount() {
        return amount;
    }

    public BillDate getBillDate() {
        return billDate;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setBillAsUnpaid() {
        this.paymentStatus.setAsUnpaid();
    }

    public boolean isSameBill(Bill bill) {
        return !(bill == null) && this.appointment.isSameAppointment(bill.getAppointment());
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof Bill) {
            Bill other = (Bill) o;
            return amount.equals(other.getAmount())
                    && billDate.equals(other.getBillDate())
                    && paymentStatus.equals(other.getPaymentStatus())
                    && appointment.equals(other.getAppointment());
        }
        return false;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Appointment: [")
                .append(getAppointment().toString())
                .append("]; Amount: ")
                .append(getAmount())
                .append("; Bill Date: ")
                .append(getBillDate())
                .append("; Payment Status: ")
                .append(getPaymentStatus());

        return builder.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(appointment, amount, billDate, paymentStatus);
    }

    public void setBillAsPaid() {
        this.paymentStatus.setAsPaid();
    }
}
