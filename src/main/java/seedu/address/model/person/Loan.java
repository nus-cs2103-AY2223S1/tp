package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.DeepCopyable;

/**
 * Loan represents a class encapsulating an amount of money presently owed to the club.
 */
public class Loan implements DeepCopyable {
    public static final String MESSAGE_CONSTRAINTS =
            "Loan amount should only contain numerics, possibly with decimal point, optional negative";
    public static final String VALIDATION_REGEX = "^-?[$]?[0-9]\\d*(\\.\\d+)?[$]?$";

    private double amountOwed = 0;

    /**
     * Constructs a {@code Loan}.
     *
     * @param amountString A valid amount, possibly in decimals
     */
    public Loan(String amountString) {
        requireNonNull(amountString);
        checkArgument(isValidLoan(amountString), MESSAGE_CONSTRAINTS);
        amountString = amountString.replace("$", "");
        amountOwed = Double.parseDouble(amountString);
    }

    /**
     * Constructs a {@code Loan}.
     *
     * @param amount A double value to signifies a new loan amount
     */
    public Loan(double amount) {
        checkArgument(isValidLoan(Double.toString(amount)), MESSAGE_CONSTRAINTS);
        amountOwed = amount;
    }

    public static boolean isValidLoan(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Increases the current loan by byAmount. If byAmount is negative, then the
     * resulting amountOwed will be negative.
     *
     * @param byAmount the amount to increase by
     */
    public void increaseLoan(double byAmount) {
        amountOwed += byAmount;
    }

    /**
     * Completely clears the loan amount and sets it to zero.
     */
    public void clearLoan() {
        amountOwed = 0;
    }

    public double getAmount() {
        return amountOwed;
    }

    @Override
    public String toString() {
        if (amountOwed < 0) {
            return String.format("-$%.2f", -amountOwed);
        } else {
            return String.format("$%.2f", amountOwed);
        }
    }

    /**
     * Returns a string version of the loan similar to a normal {@code toString()} call
     * and appends the plus sign at the front for positive values
     * @param withSign whether to print the sign
     * @return a string version of the amount owed with sign if needed
     */
    public String toString(boolean withSign) {
        if (!withSign) {
            return toString();
        }

        if (amountOwed < 0) {
            return String.format("-$%.2f", -amountOwed);
        } else {
            return String.format("+$%.2f", amountOwed);
        }
    }

    /**
     * Returns true if both loans have the same identity and data fields.
     * This defines the notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Loan)) {
            return false;
        }

        if (((Loan) other).amountOwed == amountOwed) {
            return ((Loan) other).amountOwed == amountOwed;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Double.hashCode(amountOwed);
    }

    @Override
    public Loan deepCopy() {
        return new Loan(String.valueOf(amountOwed));
    }
}
