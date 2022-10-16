package seedu.workbook.model.date;

import static java.util.Objects.requireNonNull;
import static seedu.workbook.commons.util.AppUtil.checkArgument;

/**
 * Represents a Date in WorkBook.
 * Guarantees: immutable; name is valid as declared in {@link #isValidDate(String)}
 */
public class Date {

    public static final String MESSAGE_CONSTRAINTS = "Dates can only be represented in 'DD/MM/YYYY HH:MM' format.";
    public static final String VALIDATION_REGEX = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|" +
            "(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|" +
            "^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|" +
            "(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|" +
            "(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})";

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

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + value + ']';
    }
}
