package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Money {
    public static final String MESSAGE_CONSTRAINTS = "MoneyOwed/MoneyPaid can take any positive integer values,"
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
                || (other instanceof MoneyOwed // instanceof handles nulls
                && value.equals(((MoneyOwed) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
