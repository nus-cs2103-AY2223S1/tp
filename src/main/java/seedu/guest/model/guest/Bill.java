package seedu.guest.model.guest;

import static java.util.Objects.requireNonNull;
import static seedu.guest.commons.util.AppUtil.checkArgument;

/**
 * Represents any additional amount of money owed by the Guest in the guest book.
 * Guarantees: immutable; is valid as declared in {@link #isValidBill(String)}
 */
public class Bill {

    public static final String MESSAGE_CONSTRAINTS =
            "Bills should only contain numbers, optionally one decimal point (.), and one leading plus (+) "
                    + "or minus (-) sign.\n"
                    + "If a decimal point is used, there can be up to 2 digits after it (e.g., 2.5, 9.99).\n"
                    + "If no sign is used, the value is assumed to be positive.";
    public static final String VALIDATION_REGEX = "^[-+]?(\\d+(\\.\\d{0,2})?|\\d*\\.\\d{1,2})$";
    public final String value;

    /**
     * Constructs an initial {@code Bill} with a value of 0.00.
     */
    public Bill() {
        value = "0.00";
    }

    /**
     * Constructs a {@code Bill}.
     *
     * @param bill A valid amount of money.
     */
    public Bill(String bill) {
        requireNonNull(bill);
        checkArgument(isValidBill(bill), MESSAGE_CONSTRAINTS);
        value = String.format("%.2f", Double.parseDouble(bill));
    }

    public double getValue() {
        return Double.parseDouble(value);
    }

    /**
     * Returns true if a given string is a valid bill amount.
     */
    public static boolean isValidBill(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns a {@code Bill} containing the sum of values.
     */
    public Bill add(Bill addend) {
        return new Bill(String.format("%.2f", getValue() + addend.getValue()));
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Bill // instanceof handles nulls
                && value.equals(((Bill) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
