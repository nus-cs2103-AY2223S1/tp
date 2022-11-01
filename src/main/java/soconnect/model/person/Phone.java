package soconnect.model.person;

import static java.util.Objects.requireNonNull;
import static soconnect.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's phone number in the SoConnect.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}.
 */
public class Phone {

    public static final String VALIDATION_REGEX = "\\d{3,}";
    public static final String MESSAGE_CONSTRAINTS =
            "Phone numbers should only contain numbers, and it should be at least 3 digits long";
    public static final String MESSAGE_PHONE_TOO_LONG =
            "The Phone is too long. Keep it within 45 characters, including whitespaces.";
    private static final int CHARACTER_LIMIT = 45;
    public final String value;

    /**
     * Constructs a {@code Phone}.
     *
     * @param phone A valid phone number.
     */
    public Phone(String phone) {
        requireNonNull(phone);
        checkArgument(isValidPhone(phone), MESSAGE_CONSTRAINTS);
        checkArgument(isValidLength(phone), MESSAGE_PHONE_TOO_LONG
        );
        value = phone;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidPhone(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string does not exceed the character limit.
     *
     * @param text The given string.
     * @return True if it does not exceed the character limit. False if otherwise.
     */
    public static boolean isValidLength(String text) {
        return text.length() > CHARACTER_LIMIT ? false : true;
    }

    @Override
    public String toString() {
        return value;
    }

    /**
     * Compares this Phone to another Phone.
     *
     * @param other The other Phone object.
     * @return      Negative integer if this object is lesser, 0 if they are equal, positive integer otherwise.
     */
    public int compareTo(Phone other) {
        return Integer.parseInt(value) - Integer.parseInt(other.value);
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
