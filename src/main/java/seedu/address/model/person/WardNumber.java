package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the Ward Number of a patient in the database.
 * Guarantees: immutable; name is valid as declared in {@link #isValidWardNumber(String)}
 */
public class WardNumber {
    public static final String MESSAGE_CONSTRAINTS = "Ward Number is one alphabet followed by a 3 digit number for "
            + "inpatient patients and blank for outpatient patients";

    /*
    * The first character of the ward number must be an alphabet,
    * and the rest must be a 3 digit number.
    */
    public static final String VALIDATION_REGEX = "^[A-Z]\\d{3}$";

    public final String value;

    /**
     * Constructs an {@code Ward Number}.
     *
     * @param wardNumber A valid ward number.
     */
    public WardNumber(String wardNumber) {
        requireNonNull(wardNumber);
        checkArgument(isValidWardNumber(wardNumber), MESSAGE_CONSTRAINTS);
        value = wardNumber;
    }

    /**
     * Returns true if a given string is a valid ward number.
     */
    public static boolean isValidWardNumber(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return "Ward: " + value;
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
