package seedu.hrpro.model.staff;

import static java.util.Objects.requireNonNull;
import static seedu.hrpro.commons.util.AppUtil.checkArgument;

/**
 * Represents a staff title in HR Pro Max++.
 * Guarantees: immutable; is valid as declared in {@link #isValidStaffTitle(String)}.
 */
public class StaffTitle {
    public static final String MESSAGE_CONSTRAINTS =
            "Staff title should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the staff title must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String staffTitle;

    /**
     * Constructs a {@code StaffTitle}.
     *
     * @param title A valid staff title.
     */
    public StaffTitle(String title) {
        requireNonNull(title);
        checkArgument(isValidStaffTitle(title), MESSAGE_CONSTRAINTS);
        staffTitle = title;
    }

    /**
     * Returns true if a given string is a valid staff title.
     */
    public static boolean isValidStaffTitle(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return staffTitle;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StaffTitle // instanceof handles nulls
                && staffTitle.equals(((StaffTitle) other).staffTitle)); // state check
    }

    @Override
    public int hashCode() {
        return staffTitle.hashCode();
    }
}
