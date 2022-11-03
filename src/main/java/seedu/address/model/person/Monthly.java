package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's Income Level in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidMonthly(String)}
 */
public class Monthly {

    public static final String MESSAGE_CONSTRAINTS = "Monthly contribution can only take in a non-negative whole number";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^(0|[1-9][0-9]*)$";

    public final String value;

    /**
     * Constructs an {@code Address}.
     *
     * @param monthly A valid address.
     */
    public Monthly(String monthly) {
        requireNonNull(monthly);
        checkArgument(isValidMonthly(monthly), MESSAGE_CONSTRAINTS);
        value = "$" + monthly;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidMonthly(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns a long from the String of monthly contribution without the $.
     * @return a long converted from the String.
     */
    public long convertMonthlyToLong() {
        return Long.parseLong(this.value.substring(1));
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Monthly // instanceof handles nulls
                && value.equals(((Monthly) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
