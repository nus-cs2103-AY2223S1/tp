package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class StaffTitle {
    public static final String MESSAGE_CONSTRAINTS =
            "Staff title should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String staffTitle;

    /**
     * Constructs a {@code Name}.
     *
     * @param title A valid name.
     */
    public StaffTitle(String title) {
        requireNonNull(title);
        checkArgument(isValidName(title), MESSAGE_CONSTRAINTS);
        staffTitle = title;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
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
