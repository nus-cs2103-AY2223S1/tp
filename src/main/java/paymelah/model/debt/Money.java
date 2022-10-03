package paymelah.model.debt;

import static java.util.Objects.requireNonNull;
import static paymelah.commons.util.AppUtil.checkArgument;

/**
 * Represents a Debt's money amount in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidMoney(String)}
 */
public class Money {
    public static final String MESSAGE_CONSTRAINTS =
            "Money amounts should only contain numbers to represent the amount in dollars";

    public static final String VALIDATION_REGEX = "(-|)\\d{1,}(.\\d{1,2}|)";
    public final Float value;

    /**
     * Constructs a {@code Money}.
     *
     * @param money A valid money amount.
     */
    public Money(String money) {
        requireNonNull(money);
        checkArgument(isValidMoney(money), MESSAGE_CONSTRAINTS);
        value = Float.parseFloat(money);
    }

    /**
     * Returns true if a given string is a valid money amount.
     *
     * @param test The string to test for validity.
     * @return true if the given string is a valid money amount.
     */
    public static boolean isValidMoney(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Money // instanceof handles nulls
                && value.equals(((Money) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
