package seedu.address.model.bill;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents bill's payment state in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPaymentStatus  (String)}
 */
public class PaymentStatus {
    public static final String MESSAGE_CONSTRAINTS =
            "PaymentStatus should be either true or false";

    public static final String VALIDATION_REGEX = "true|false";

    public final Boolean isPaid;

    /**
     * Constructs a {@code Slot}.
     *
     * @param isPaid A valid dateTime of a slot.
     */
    public PaymentStatus(String isPaid) {
        requireNonNull(isPaid);
        checkArgument(isValidPaymentStatus(isPaid), MESSAGE_CONSTRAINTS);
        this.isPaid = isPaid.equals("true");
    }

    /**
     * Returns true if a given string is a valid date time.
     */
    public static boolean isValidPaymentStatus(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public boolean isPaid() {
        return isPaid;
    }

    @Override
    public String toString() {
        return String.valueOf(isPaid);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PaymentStatus // instanceof handles nulls
                && isPaid.equals(((PaymentStatus) other).isPaid)); // state check
    }

    @Override
    public int hashCode() {
        return isPaid.hashCode();
    }
}
