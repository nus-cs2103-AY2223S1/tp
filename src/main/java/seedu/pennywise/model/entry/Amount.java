package seedu.pennywise.model.entry;

import static java.lang.Double.parseDouble;
import static java.util.Objects.requireNonNull;
import static seedu.pennywise.commons.util.AppUtil.checkArgument;

import java.text.DecimalFormat;

/**
 * Represents an {@code Entry}'s amount in the PennyWise application.
 * Guarantees: immutable; is valid as declared in {@link #isValidAmount(String)}
 */
public class Amount {

    public static final String MESSAGE_CONSTRAINTS = "Expense amount should only contain positive numbers, "
        + "and it should be formatted to accept 2 decimal places";
    public static final String VALIDATION_REGEX = "^\\s*(?=.*[1-9])\\d*(?:\\.\\d{1,2})?\\s*$";
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private final double amount;
    private final String amountString;

    /**
     * Constructs a {@code Amount}.
     *
     * @param amount A valid expense amount.
     */
    public Amount(String amount) {
        requireNonNull(amount);
        checkArgument(isValidAmount(amount), MESSAGE_CONSTRAINTS);
        this.amount = parseDouble(amount);
        this.amountString = df.format(this.amount);
    }

    /**
     * Returns true if a given string is a valid expense amount.
     */
    public static boolean isValidAmount(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public double getValue() {
        return amount;
    }

    public static Amount add(Amount amount1, Amount amount2) {
        return new Amount(String.valueOf(amount1.getValue() + amount2.getValue()));
    }

    /**
     * Returns the representation of the amount in currency format.
     */
    public String toFormattedString() {
        return "$" + df.format(this.amount);
    }

    @Override
    public String toString() {
        return amountString;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Amount // instanceof handles nulls
            && this.amount == (((Amount) other).amount)); // state check
    }

    @Override
    public int hashCode() {
        return amountString.hashCode();
    }
}

