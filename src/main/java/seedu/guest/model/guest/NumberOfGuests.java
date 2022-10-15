package seedu.guest.model.guest;

import static java.util.Objects.requireNonNull;
import static seedu.guest.commons.util.AppUtil.checkArgument;

/**
 * Represents the total number of guests a Guest has in the guest book.
 * Guarantees: immutable; is valid as declared in {@link #isValidNumberOfGuests(String)}
 */
public class NumberOfGuests {


    public static final String MESSAGE_CONSTRAINTS =
            "Number of Guests should only contain numbers, and it should be at least 1 and at most 4";
    public static final String VALIDATION_REGEX = "\\d{1,}";
    public final String value;

    /**
     * Constructs a {@code NumberOfGuests}.
     *
     * @param number A valid number of guests.
     */
    public NumberOfGuests(String number) {
        requireNonNull(number);
        checkArgument(isValidNumberOfGuests(number), MESSAGE_CONSTRAINTS);
        value = number;
    }

    /**
     * Returns true if a given string is a valid number of guests.
     */
    public static boolean isValidNumberOfGuests(String test) {
        return test.matches(VALIDATION_REGEX) && Integer.parseInt(test) >= 1 && Integer.parseInt(test) <= 4;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NumberOfGuests // instanceof handles nulls
                && value.equals(((NumberOfGuests) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
