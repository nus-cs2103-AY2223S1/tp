package gim.model.exercise;

import static gim.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

/**
 * Represents an Exercise's weight number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidWeight(String)}
 */
public class Weight {


    public static final String MESSAGE_CONSTRAINTS =
            "Weight numbers should only contain numbers, and it should be at least 3 digits long";
    public static final String VALIDATION_REGEX = "\\d{3,}";
    public final String value;

    /**
     * Constructs a {@code Weight}.
     *
     * @param weight A valid weight number.
     */
    public Weight(String weight) {
        requireNonNull(weight);
        checkArgument(isValidWeight(weight), MESSAGE_CONSTRAINTS);
        value = weight;
    }

    /**
     * Returns true if a given string is a valid weight number.
     */
    public static boolean isValidWeight(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Weight // instanceof handles nulls
                && value.equals(((Weight) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
