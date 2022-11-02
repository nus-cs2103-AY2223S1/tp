package seedu.address.model.staff;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a staff name in HR Pro Max++.
 * Guarantees: immutable; is valid as declared in {@link #isValidStaffName(String)}
 */
public class StaffName {
    public static final String MESSAGE_CONSTRAINTS =
            "Staff name should only contain alphabetic characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alpha}][\\p{Alpha} ]*";

    public final String staffName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public StaffName(String name) {
        requireNonNull(name);
        checkArgument(isValidStaffName(name), MESSAGE_CONSTRAINTS);
        staffName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidStaffName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return staffName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StaffName // instanceof handles nulls
                && staffName.equalsIgnoreCase(((StaffName) other).staffName)); // state check
    }

    @Override
    public int hashCode() {
        return staffName.hashCode();
    }
}
