package seedu.address.model.bill;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents bill's payment state in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidIsPaid  (String)}
 */
public class Paid {
    public static final String MESSAGE_CONSTRAINTS =
            "Paid should be either true or false";

    public static final String VALIDATION_REGEX = "true|false";

    public final Boolean isPaid;

    /**
     * Constructs a {@code Slot}.
     *
     * @param isPaid A valid dateTime of a slot.
     */
    public Paid(String isPaid) {
        requireNonNull(isPaid);
        checkArgument(isValidIsPaid(isPaid), MESSAGE_CONSTRAINTS);
        this.isPaid = isPaid.equals("true");
    }

    /**
     * Returns true if a given string is a valid date time.
     */
    public static boolean isValidIsPaid(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return String.valueOf(isPaid);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Paid // instanceof handles nulls
                && isPaid.equals(((Paid) other).isPaid)); // state check
    }

    @Override
    public int hashCode() {
        return isPaid.hashCode();
    }
}
