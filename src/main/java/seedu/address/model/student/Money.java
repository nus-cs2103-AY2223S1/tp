package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.logic.commands.exceptions.CommandException;

/**
 * Represents a Student's Money in the Address Book.
 * Guarantees: immutable; is valid as declared in {@link #isValidMoney(Integer)}
 */
public class Money implements Comparable<Money> {
    public static final String MESSAGE_CONSTRAINTS = "Money can take any positive integer values,"
            + " and its default value is 0 and maximum is 2147483647 at all times.";

    public static final String MESSAGE_AMOUNT_TOO_LARGE = "Amount of money is too large to handle.";

    public static final String MESSAGE_NEGATIVE_AMOUNT = "Amount of money cannot be negative";

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
     *
     * @param amount the value to be validated
     * @return true if a given integer is non-negative
     */
    public static boolean isValidMoney(Integer amount) {
        return amount >= 0;
    }

    /**
     * Computes the sum of a predefined value and the current value.
     *
     * @param amountToAdd the money to be added.
     * @return Money that consists of the sum of the 2 amounts.
     * @throws CommandException if an error occurs during addition.
     */
    public Money addTo(Money amountToAdd) throws CommandException {
        try {
            Integer amountSum = Math.addExact(this.value, amountToAdd.value);
            return new Money(amountSum);
        } catch (ArithmeticException e) {
            throw new CommandException(MESSAGE_AMOUNT_TOO_LARGE);
        }
    }

    /**
     * Computes the subtraction of a predefined value from the current value.
     *
     * @param amountToSubtract the money to be subtracted.
     * @return Money that consists of amount after subtraction.
     * @throws CommandException if the final value is negative.
     */
    public Money subtract(Money amountToSubtract) throws CommandException {
        Integer finalAmount = this.value - amountToSubtract.value;

        try {
            return new Money(finalAmount);
        } catch (IllegalArgumentException e) {
            throw new CommandException(MESSAGE_NEGATIVE_AMOUNT);
        }
    }

    /**
     * Validates whether amount is more than zero.
     *
     * @return true if amount is positive
     */
    public boolean isGreaterThanZero() {
        return value > 0;
    }

    /**
     * Returns 1 if this is greater than the given {@code money}, 0 if equal, -1 if smaller.
     */
    @Override
    public int compareTo(Money money) {
        requireNonNull(money);
        return this.value.compareTo(money.value);
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
