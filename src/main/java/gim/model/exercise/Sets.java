package gim.model.exercise;

import static gim.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

/**
 * Represents an Exercise's sets in Gim.
 * Guarantees: immutable; is valid as declared in {@link #isValidSets(String)}
 */
public class Sets {

    public static final String MESSAGE_CONSTRAINTS = "Sets can only take positive integer values, up to 3 digits, "
            + "leading zeros are not allowed.";
    public static final String VALIDATION_REGEX = "^(?:([1-9])|([1-9][0-9])|([1-9][0-9][0-9]))$";


    public final String value;

    /**
     * Constructs {@code Sets}.
     *
     * @param sets A valid number of Sets.
     */
    public Sets(String sets) {
        requireNonNull(sets);
        checkArgument(isValidSets(sets), MESSAGE_CONSTRAINTS);
        value = sets;
    }

    /**
     * Returns if a given String is a valid number of Sets.
     */
    public static boolean isValidSets(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Sets // instanceof handles nulls
                && value.equals(((Sets) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
