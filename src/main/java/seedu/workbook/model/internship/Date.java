package seedu.workbook.model.internship;

import static java.util.Objects.requireNonNull;
import static seedu.workbook.commons.util.AppUtil.checkArgument;

/**
 * Represents a Date in WorkBook.
 * Guarantees: immutable; name is valid as declared in {@link #isValidDate(String)}
 */
public class Date {

    public static final String MESSAGE_CONSTRAINTS = "Dates can only be represented in 'DD/MM/YYYY' format.";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String value;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid date.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        value = date;
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date // instanceof handles nulls
                && value.equals(((Date) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
