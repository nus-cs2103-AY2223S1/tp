package seedu.guest.model.guest;

import static java.util.Objects.requireNonNull;
import static seedu.guest.commons.util.AppUtil.checkArgument;

/**
 * Represents the total number of guests a Guest has in the guest book.
 * Guarantees: immutable; is valid as declared in {@link #isValidNumberOfGuests(String)}
 */
public class NumberOfGuests {

    // value constraints
    private static final int MIN_NUM_GUESTS = 1;
    private static final int MAX_NUM_GUESTS = 4;

    public static final String MESSAGE_CONSTRAINTS =
            "NumberOfGuests should only contain 1 numeric character from " + MIN_NUM_GUESTS + " to "
                    + MAX_NUM_GUESTS + ".";
    public static final String VALIDATION_REGEX = String.format("^[%d-%d]$", MIN_NUM_GUESTS, MAX_NUM_GUESTS);
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
        return test.matches(VALIDATION_REGEX);
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
