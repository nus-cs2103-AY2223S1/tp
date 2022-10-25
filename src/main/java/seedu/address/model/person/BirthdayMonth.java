package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's birthday in bobaBot.
 * Guarantees: immutable; is valid as declared in {@link #isValidBirthdayMonth(String)}
 */
public class BirthdayMonth {

    public static final String MESSAGE_CONSTRAINTS = "Birthday month should not be blank. It should be between 1 - 12.";

    /*
     * The first character of the birthdayMonth must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    private static final String[] monthStrings = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN",
        "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};

    public final String value;
    public final String monthString;

    /**
     * Constructs a {@code BirthdayMonth}.
     *
     * @param birthdayMonth A valid birthdayMonth.
     */
    public BirthdayMonth(String birthdayMonth) {
        requireNonNull(birthdayMonth);
        checkArgument(isValidBirthdayMonth(birthdayMonth), MESSAGE_CONSTRAINTS);
        int integerValue = Integer.valueOf(birthdayMonth);

        assert integerValue > 0 && integerValue < 13;

        monthString = monthStrings[integerValue - 1];
        value = String.valueOf(integerValue);
    }

    /**
     * Returns true if a given string is a valid birthdayMonth.
     */
    public static boolean isValidBirthdayMonth(String test) {
        return test.matches(VALIDATION_REGEX) && isNumeric(test)
            && Integer.valueOf(test) > 0 && Integer.valueOf(test) < 13;
    }

    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof BirthdayMonth // instanceof handles nulls
            && value.equals(((BirthdayMonth) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
