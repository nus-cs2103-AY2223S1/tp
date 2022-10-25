package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.DeepCopyable;

/**
 * Represents a Person's birthday in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidBirthday(String)}
 */
public class Birthday implements DeepCopyable {

    public static final String MESSAGE_CONSTRAINTS = "Birthdays should only contain numbers and forward slashes";

    private static final String DAY_REGEX = "^([0-2][0-9]||3[0-1])";
    private static final String MONTH_REGEX = "(0[0-9]||1[0-2])";
    private static final String YEAR_REGEX = "([0-9][0-9])?[0-9][0-9]$";
    public static final String DATE_REGEX = DAY_REGEX + "/" + MONTH_REGEX + "/" + YEAR_REGEX;


    public final String value;

    /**
     * Constructs a {@code Birthday}.
     *
     * @param birthday A valid birthday.
     */
    public Birthday(String birthday) {
        requireNonNull(birthday);
        checkArgument(isValidBirthday(birthday), MESSAGE_CONSTRAINTS);
        value = birthday;
    }

    /**
     * Returns true if a given string is a valid Birthday.
     */
    public static boolean isValidBirthday(String test) {
        return test.matches(DATE_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Birthday // instanceof handles nulls
                && value.equals(((Birthday) other).value)); // state check
    }

    @Override
    public Birthday deepCopy() {
        return new Birthday(value);
    }
}
