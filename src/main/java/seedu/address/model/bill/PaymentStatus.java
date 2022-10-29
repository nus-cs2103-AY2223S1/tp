package seedu.address.model.bill;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents bill's payment state in the HealthContact.
 * Guarantees: immutable; is valid as declared in {@link #isValidPaymentStatus  (String)}
 */
public class PaymentStatus {

    /**
     * The enumeration of possible payment status.
     */
    public enum Status {
        PAID, UNPAID
    }

    public static final String MESSAGE_CONSTRAINTS =
            "PaymentStatus should be either paid or unpaid";

    public static final String VALIDATION_REGEX = "PAID|UNPAID";

    private Status status;

    /**
     * Constructs a {@code Slot}.
     *
     * @param status A valid status of a payment.
     */
    public PaymentStatus(String status) {
        requireNonNull(status);
        checkArgument(isValidPaymentStatus(status), MESSAGE_CONSTRAINTS);
        if (status.toUpperCase().equals(Status.PAID.toString())) {
            this.status = Status.PAID;
        } else {
            this.status = Status.UNPAID;
        }
    }

    /**
     * Returns true if a given string is a valid status.
     */
    public static boolean isValidPaymentStatus(String test) {
        return test.toUpperCase().matches(VALIDATION_REGEX);
    }

    public boolean isPaid() {
        return status.equals(Status.PAID);
    }

    public void setAsPaid() {
        this.status = Status.PAID;
    }

    @Override
    public String toString() {
        return String.valueOf(status);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PaymentStatus // instanceof handles nulls
                && status.equals(((PaymentStatus) other).status)); // state check
    }

    @Override
    public int hashCode() {
        return status.hashCode();
    }

    public void setAsUnpaid() {
        this.status = Status.UNPAID;
    }
}
