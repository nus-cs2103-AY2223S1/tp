package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class MoneyOwed {
    public static final String MESSAGE_CONSTRAINTS = "MoneyOwed can take any positive integer values,"
            + " and its default value is 0";

    public final Integer value;

    /**
     * Constructs an {@code MoneyOwed}.
     *
     * @param amount A valid amount
     */
    public MoneyOwed(Integer amount) {
        requireNonNull(amount);
        checkArgument(isValidMoneyOwed(amount), MESSAGE_CONSTRAINTS);
        value = amount;
    }

    /**
     * Constructs an {@code MoneyOwed} with default value 0.
     */
    public MoneyOwed() {
        value = 0;
    }

    /**
     * Validates whether amount is valid.
     * @param amount the value to be validated
     * @return true if a given integer is non-negative
     */
    public static boolean isValidMoneyOwed(Integer amount) {
        return amount >= 0;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof  MoneyOwed // instanceof handles nulls
                && value.equals(((MoneyOwed) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
