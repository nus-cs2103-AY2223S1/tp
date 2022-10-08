package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Loan represents a class encapsulating an amount of money presently owed to the club.
 */
public class Loan {
    private double amountOwed = 0;

    public static final String MESSAGE_CONSTRAINTS =
            "Loan amount should only contain numerics, possibly with decimal point, optional negative";
    public static final String VALIDATION_REGEX = "^-?[0-9]\\d*(\\.\\d+)?$";

    public Loan(String amountString) {
        requireNonNull(amountString);
        checkArgument(isValidLoan(amountString), MESSAGE_CONSTRAINTS);
        amountOwed = Double.parseDouble(amountString);
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
}
