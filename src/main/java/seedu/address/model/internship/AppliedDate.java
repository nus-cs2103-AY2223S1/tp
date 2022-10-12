package seedu.address.model.internship;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Internship's applied date in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAppliedDate(String)}
 */
public class AppliedDate {

    public static final String MESSAGE_CONSTRAINTS = "To be added";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[0-3][0-9]/[0-3][0-9]/(?:[0-9][0-9])?[0-9][0-9]$";

    public final String value;

    /**
     * Constructs an {@code AppliedDate}.
     *
     * @param appliedDate A valid appliedDate.
     */
    public AppliedDate(String appliedDate) {
        requireNonNull(appliedDate);
        checkArgument(isValidAppliedDate(appliedDate), MESSAGE_CONSTRAINTS);
        value = appliedDate;
    }

    /**
     * Returns true if a given string is a valid appliedDate.
     */
    public static boolean isValidAppliedDate(String test) {
        return true;
        // return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AppliedDate // instanceof handles nulls
                && value.equals(((AppliedDate) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
