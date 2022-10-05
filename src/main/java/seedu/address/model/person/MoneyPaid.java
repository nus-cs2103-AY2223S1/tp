package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's Money Paid in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidMoneyPaid(Integer)}
 */
public class MoneyPaid {
    public static final String MESSAGE_CONSTRAINTS = "MoneyPaid can take any positive integer values,"
            + " and its default value is 0";

    public final Integer value;

    /**
     * Constructs an {@code MoneyPaid}.
     *
     * @param amount A valid amount
     */
    public MoneyPaid(Integer amount) {
        requireNonNull(amount);
        checkArgument(isValidMoneyPaid(amount), MESSAGE_CONSTRAINTS);
        value = amount;
    }

    /**
     * Constructs an {@code MoneyPaid} with default value 0.
     */
    public MoneyPaid() {
        value = 0;
    }

    /**
     * Validates whether amount is valid.
     * @param amount the value to be validated
     * @return true if a given integer is non-negative
     */
    public static boolean isValidMoneyPaid(Integer amount) {
        return amount >= 0;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MoneyPaid // instanceof handles nulls
                && value.equals(((MoneyPaid) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
