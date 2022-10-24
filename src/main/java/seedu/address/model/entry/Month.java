package seedu.address.model.entry;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Entry's date in the penny wise application.
 * Guarantees: immutable; is valid as declared in {@link #isValidMonth(String)}
 */
public class Month {
    public static final String MESSAGE_CONSTRAINTS =
            "Months should be of the format yyyy-mm and it should only contain numbers";
    public static final String VALIDATION_REGEX =
            "^([0-9][0-9])?[0-9][0-9]-(0[1-9]||[1-9]||1[0-2])$";

    public final String month;

    /**
     * Constructs a {@code Month}.
     *
     * @param month A valid month.
     */
    public Month(String month) {
        requireNonNull(month);
        checkArgument(isValidMonth(month), MESSAGE_CONSTRAINTS);
        this.month = month;
    }

    /**
     * Returns true if a given string is a valid month.
     */
    public static boolean isValidMonth(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return month;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Month // instanceof handles nulls
                && month.equals(((Month) other).month)); // state check
    }

    @Override
    public int hashCode() {
        return month.hashCode();
    }
}

