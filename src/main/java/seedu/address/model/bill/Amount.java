package seedu.address.model.bill;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents amount of bill in the HealthContact.
 * Guarantees: immutable; is valid as declared in {@link #isValidAmount (String)}
 */
public class Amount {
    public static final String MESSAGE_CONSTRAINTS =
            "Amount should only be a positive number with at most two decimal places";
    /*
     * The first character of the amount must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^\\d+(?:\\.\\d{0,2})?$";

    public final Double amount;

    /**
     * Constructs a {@code Doctor}.
     *
     * @param amount A valid name of a doctor.
     */
    public Amount(String amount) {
        requireNonNull(amount);
        checkArgument(isValidAmount(amount), MESSAGE_CONSTRAINTS);
        this.amount = Double.parseDouble(amount);
    }

    /**
     * Returns true if a given string is a valid doctor name.
     */
    public static boolean isValidAmount(String test) {
        return test.matches(VALIDATION_REGEX) && Double.parseDouble(test) > 0;
    }

    @Override
    public String toString() {
        return String.format("%.2f", amount);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Amount // instanceof handles nulls
                && amount.equals(((Amount) other).amount)); // state check
    }

    @Override
    public int hashCode() {
        return amount.hashCode();
    }
}
