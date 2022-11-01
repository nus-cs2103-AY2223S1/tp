package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.DeepCopyable;

/**
 * Loan represents a class encapsulating an amount of money presently owed to the club.
 */
public class Loan implements DeepCopyable {
    public static final String MESSAGE_CONSTRAINTS =
            "Loan amounts should only contain numerics, and a maximum of 1 decimal dot (.), an optional "
            + "dollar sign ($) and either plus or minus signs (+/-) if desired.\n"
            + "The signs, if used, must appear at the start of the parameter.\n"
            + "The maximum amount may not exceed 1 trillion, both in the positive and negative "
            + "direction, and the precision may not exceed 2 decimal places.";


    public static final String VALIDATION_REGEX = "^[-|+]?[$]?[0-9]\\d*(\\.\\d{0,2})?$";
    private static final double ONE_TRILLION = 1_000_000_000_000.00;

    private double amountOwed = 0;

    /**
     * Constructs a {@code Loan}.
     *
     * @param amountString A valid amount, possibly in decimals
     */
    public Loan(String amountString) {
        requireNonNull(amountString);
        System.out.println(amountString);
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
        amount = (Math.round(amount * 100.0)) / 100.0;
        checkArgument(isValidLoan(amount), MESSAGE_CONSTRAINTS);
        amountOwed = amount;
    }

    /**
     * Checks if the given input satisfies the constraints
     * @param test the input to test
     * @return whether the constraint is satisfied
     */
    public static boolean isValidLoan(String test) {
        if (!test.matches(VALIDATION_REGEX)) {
            return false;
        }
        test = test.replace("$", "");

        double parsedAmount;
        try {
            parsedAmount = Double.parseDouble(test);
        } catch (NumberFormatException e) {
            return false;
        }
        return isValidLoan(parsedAmount);
    }

    /**
     * Checks if the given input satisfies the constraints
     * @param test the input to test
     * @return whether the constraint is satisfied
     */
    public static boolean isValidLoan(double test) {
        return Math.abs(test) <= ONE_TRILLION;
    }


    /**
     * Increases the current loan by byAmount. If byAmount is negative, then the
     * resulting amountOwed will be negative.
     *
     * @param byLoan the amount to increase by
     */
    public Loan subtractBy(Loan byLoan) {
        return new Loan(getAmount() - byLoan.getAmount());
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

        return ((Loan) other).amountOwed == amountOwed;
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
