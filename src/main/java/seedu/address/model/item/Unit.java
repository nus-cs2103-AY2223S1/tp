package seedu.address.model.item;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Unit {
    public static final String MESSAGE_CONSTRAINTS =
            "Units should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    // TODO: Change validation
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String unitName;

    /**
     * Constructs a {@code Name}.
     *
     * @param unitName A valid unitName.
     */
    public Unit(String unitName) {
        requireNonNull(unitName);
        checkArgument(isValidUnit(unitName), MESSAGE_CONSTRAINTS);
        this.unitName = unitName;
    }

    /**
     * Returns true if a given string is a valid unitName.
     */
    public static boolean isValidUnit(String stringToTest) {
        return stringToTest.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return unitName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Unit // instanceof handles nulls
                && unitName.equals(((Unit) other).unitName)); // state check
    }

    @Override
    public int hashCode() {
        return unitName.hashCode();
    }
}
