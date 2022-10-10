package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's Money in the Address Book.
 * Guarantees: immutable; is valid as declared in {@link #isValidMoney(Integer)}
 */
public class Money {
    public static final String MESSAGE_CONSTRAINTS = "Money can take any positive integer values,"
            + " and its default value is 0";

    public final Integer value;

    /**
     * Constructs an {@code Money}.
     *
     * @param amount A valid amount
     */
    public Money(Integer amount) {
        requireNonNull(amount);
        checkArgument(isValidMoney(amount), MESSAGE_CONSTRAINTS);
        value = amount;
    }

    /**
     * Constructs an {@code Money} with default value 0.
     */
    public Money() {
        value = 0;
    }

    /**
     * Validates whether amount is valid.
     * @param amount the value to be validated
     * @return true if a given integer is non-negative
     */
    public static boolean isValidMoney(Integer amount) {
        return amount >= 0;
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
