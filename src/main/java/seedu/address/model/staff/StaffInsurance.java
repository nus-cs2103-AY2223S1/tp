package seedu.address.model.staff;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a staff insurance in HR Pro Max++.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class StaffInsurance {
    public static final String MESSAGE_CONSTRAINTS =
            "Staff insurance should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    // TODO: 7/10/2022 check if this is the proper regex
    public static final String VALIDATION_REGEX = "(true|false)";

    public final String staffInsurance;

    /**
     * Constructs a {@code Name}.
     *
     * @param insurance A valid name.
     */
    public StaffInsurance(String insurance) {
        requireNonNull(insurance);
        checkArgument(isValidName(insurance.toLowerCase()), MESSAGE_CONSTRAINTS);
        staffInsurance = insurance;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return staffInsurance;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StaffInsurance // instanceof handles nulls
                && staffInsurance.equals(((StaffInsurance) other).staffInsurance)); // state check
    }

    @Override
    public int hashCode() {
        return staffInsurance.hashCode();
    }
}
