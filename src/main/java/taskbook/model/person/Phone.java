package taskbook.model.person;

import static java.util.Objects.requireNonNull;

import taskbook.commons.util.AppUtil;

/**
 * Represents a Person's phone number in the task book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class Phone {

    public static final String NO_PHONE_PROVIDED = "[No phone number]";

    public static final String MESSAGE_CONSTRAINTS =
            "Phone numbers should only contain numbers, and it should be at least 3 digits long";

    public static final String VALIDATION_REGEX = "\\d{3,}";

    public final String value;

    /**
     * Constructs a {@code Phone}.
     *
     * @param phone A valid phone number.
     */
    public Phone(String phone) {
        requireNonNull(phone);
        AppUtil.checkArgument(isValidPhone(phone), MESSAGE_CONSTRAINTS);
        value = phone;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidPhone(String test) {
        return test.matches(VALIDATION_REGEX) || test.equals(NO_PHONE_PROVIDED);
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

    /**
     * Compares this phone number and another phone number to determine ascending order.
     * NO_PHONE_PROVIDED is greater than any valid phone number.
     * @param other phone number.
     * @return positive integer if this phone number is numerically greater, 0 if equal, negative otherwise.
     */
    public int comparePhoneNumberAscending(Phone other) {
        if (this.value.equals(NO_PHONE_PROVIDED) && other.value.equals(NO_PHONE_PROVIDED)) {
            return 0;
        }
        if (this.value.equals(NO_PHONE_PROVIDED)) {
            return 1;
        }
        if (other.value.equals(NO_PHONE_PROVIDED)) {
            return -1;
        }
        assert isValidPhone(this.value);
        assert isValidPhone(other.value);
        return Integer.parseInt(this.value) - Integer.parseInt(other.value);
    }

    /**
     * Compares this phone number and another phone number to determine descending order.
     * NO_PHONE_PROVIDED is greater than any valid phone number.
     * @param other phone number.
     * @return positive integer if this phone number is numerically smaller, 0 if equal, negative otherwise.
     */
    public int comparePhoneNumberDescending(Phone other) {
        if (this.value.equals(NO_PHONE_PROVIDED) && other.value.equals(NO_PHONE_PROVIDED)) {
            return 0;
        }
        if (this.value.equals(NO_PHONE_PROVIDED)) {
            return 1;
        }
        if (other.value.equals(NO_PHONE_PROVIDED)) {
            return -1;
        }
        assert isValidPhone(this.value);
        assert isValidPhone(other.value);
        return Integer.parseInt(other.value) - Integer.parseInt(this.value);
    }
}
