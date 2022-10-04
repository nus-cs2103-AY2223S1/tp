package seedu.address.model.person;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the Ward Number of an patient in the database.
 * Guarantees: immutable; name is valid as declared in {@link #isValidWardNumber(Integer)}
 */
public class WardNumber {
    public static final String MESSAGE_CONSTRAINTS = "Ward Number is a positive integer for inpatient patients "
            + "and blank for outpatient patients";

    public final Integer value;

    /**
     * Constructs an {@code Ward Number}.
     *
     * @param wardNumber A valid ward number.
     */
    public WardNumber(Integer wardNumber) {
        checkArgument(isValidWardNumber(wardNumber), MESSAGE_CONSTRAINTS);
        value = wardNumber;
    }

    /**
     * Returns true if a given string is a valid ward number.
     */
    public static boolean isValidWardNumber(Integer test) {
        return test > 0;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof WardNumber // instanceof handles nulls
                && value.equals(((WardNumber) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
