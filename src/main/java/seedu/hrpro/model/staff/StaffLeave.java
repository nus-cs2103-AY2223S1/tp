package seedu.hrpro.model.staff;

import static java.util.Objects.requireNonNull;
import static seedu.hrpro.commons.util.AppUtil.checkArgument;

/**
 * Represents a staff leave in HR Pro Max++.
 * Guarantees: immutable; is valid as declared in {@link #isValidStaffLeave(String)}.
 */
public class StaffLeave {
    public static final String MESSAGE_CONSTRAINTS =
            "Staff Leave should only be true or false (case-sensitive) and not blank";

    /*
     * Staff leave should either be true or false.
     */
    public static final String VALIDATION_REGEX = "(true|false)";

    private final String staffLeave;

    /**
     * Constructs a {@code StaffLeave}.
     *
     * @param leave A valid StaffName.
     */
    public StaffLeave(String leave) {
        requireNonNull(leave);
        checkArgument(isValidStaffLeave(leave), MESSAGE_CONSTRAINTS);
        staffLeave = leave;
    }

    /**
     * Returns true if a given string is a valid staff leave.
     */
    public static boolean isValidStaffLeave(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return staffLeave;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StaffLeave // instanceof handles nulls
                && staffLeave.equals(((StaffLeave) other).staffLeave)); // state check
    }

    @Override
    public int hashCode() {
        return staffLeave.hashCode();
    }

    public String getStaffLeave() {
        return staffLeave;
    }
}
