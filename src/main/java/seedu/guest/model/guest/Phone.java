package seedu.guest.model.guest;

import static java.util.Objects.requireNonNull;
import static seedu.guest.commons.util.AppUtil.checkArgument;

/**
 * Represents a Guest's phone number in the guest book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class Phone {

    // length constraints
    private static final int MIN_LENGTH = 3;
    private static final int MAX_LENGTH = 15;

    public static final String MESSAGE_CONSTRAINTS =
            "Phone numbers should only contain numbers. They must be at least " + MIN_LENGTH
                    + " digits and at most " + MAX_LENGTH + " digits long.";

    public static final String VALIDATION_REGEX = String.format("\\d{%d,%d}", MIN_LENGTH, MAX_LENGTH);
    public final String value;

    /**
     * Constructs a {@code Phone}.
     *
     * @param phone A valid phone number.
     */
    public Phone(String phone) {
        requireNonNull(phone);
        checkArgument(isValidPhone(phone), MESSAGE_CONSTRAINTS);
        value = phone;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidPhone(String test) {
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
