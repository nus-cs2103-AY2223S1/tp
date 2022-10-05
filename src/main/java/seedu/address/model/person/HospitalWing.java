package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an inpatient's currently located hospital wing in the database.
 * Guarantees: immutable; is valid as declared in {@link #isValidHospitalWing(String)}
 */
public class HospitalWing {

    public static final String MESSAGE_CONSTRAINTS = "Hospital Wing should be alphanumeric for inpatients "
            + "and blank for outpatients";
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code Hospital Wing}.
     *
     * @param hospitalWing A valid hospital wing.
     */
    public HospitalWing(String hospitalWing) {
        requireNonNull(hospitalWing);
        checkArgument(isValidHospitalWing(hospitalWing), MESSAGE_CONSTRAINTS);
        value = hospitalWing;
    }

    /**
     * Returns true if a given string is a valid hospital wing.
     */
    public static boolean isValidHospitalWing(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return "Wing: " + value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HospitalWing // instanceof handles nulls
                && value.equals(((HospitalWing) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

