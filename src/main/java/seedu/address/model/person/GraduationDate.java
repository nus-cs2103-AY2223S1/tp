package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the Graduation Date of a Person in the address book, as combination of a Month and Year
 * Guarantees: immutable; is valid as declared in {@link #isValidGraduationDate(String)}
 */
public class GraduationDate {

    public static final String MESSAGE_CONSTRAINTS = "Graduation Date should be of the format month-year"
        + " and adhere to the following constraints:\n"
        + "1. year should contain only numbers, and is exactly 4 digit long\n"
        + "2. month should contain only numbers, is exactly 2 digit long, and is a valid month number\n"
        + "year and month values are separated by '-'";
    public static final String VALIDATION_REGEX_MONTH_YEAR = "^((1[0-2]|0[1-9])-(\\d{4}))$";
    //public static final String MONTH_YEAR_PATTERN = "MM-yyyy";
    //public static final String DISPLAY_DATE_FORMAT = "MMM yyyy";

    public final String value;

    /**
     * Constructs an {@code GraduationDate}.
     *
     * @param yearMonthString A valid graduation month year.
     */
    public GraduationDate(String yearMonthString) {
        requireNonNull(yearMonthString);
        checkArgument(isValidGraduationDate(yearMonthString));
        value = yearMonthString;
    }

    /**
     * Returns if a given string is a valid graduation date.
     */
    public static boolean isValidGraduationDate(String test) {
        return isPatternYearMonth(test);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GraduationDate // instanceof handles nulls
                && value.equals(((GraduationDate) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    private static boolean isPatternYearMonth(String test) {
        return test.matches(VALIDATION_REGEX_MONTH_YEAR);
    }
}
