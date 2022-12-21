package seedu.masslinkers.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.masslinkers.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's phone number in the mass linkers.
 * Guarantees: immutable; is valid as declared in {@link #isCorrect(String)}
 */
public class Phone {

    //Phone numbers are usually between 7-16 digits long
    //Phone numbers cannot be empty after trimming
    //If phone numbers conform to the regex pattern, they are considered valid
    //If they do not, they are still valid but considered "incorrect" and a warning will be issued
    public static final String MESSAGE_CONSTRAINTS =
            "Phone numbers should not be blank and should have a length of 7-16 characters "
            + "(inclusive of country codes).\n"
            + "Phone numbers may start with \"+\" for the country code (optional).\n"
            + "Incorrect phone numbers are still accepted, though they are discouraged and a warning will be shown.";
    public static final String VALIDATION_REGEX = "(\\+)?[0-9]{7,15}";
    public final String value;
    private final boolean isIncorrect;

    /**
     * Constructs a {@code Phone}.
     *
     * @param phone A valid phone number.
     */
    public Phone(String phone) {
        requireNonNull(phone);
        checkArgument(!isEmptyPhone(phone), MESSAGE_CONSTRAINTS);
        value = phone;
        isIncorrect = !isCorrect(phone);
    }

    /**
     * Returns true if the phone number is incorrect (but still valid)
     * (ie an incorrect phone is not blank and does not conform to the regex check but is still valid).
     */
    public boolean hasIncorrectNumber() {
        return this.isIncorrect;
    }

    /**
     * Returns true if a given string is blank after trimming.
     */
    public static boolean isEmptyPhone(String test) {
        return test.trim().length() == 0;
    }

    /**
     * Returns true if a given string matches the regex pattern.
     */
    public static boolean isCorrect(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Phone // instanceof handles nulls
                && value.equals(((Phone) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
