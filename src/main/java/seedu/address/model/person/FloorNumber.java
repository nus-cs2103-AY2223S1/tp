package seedu.address.model.person;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the Floor Number of an inpatient in the database.
 * Guarantees: immutable; name is valid as declared in {@link #isValidFloorNumber(Integer)}
 */
public class FloorNumber {
    public static final String MESSAGE_CONSTRAINTS = "Floor Number is a positive integer for inpatient patients "
            + "and blank for outpatient patients";

    public final Integer value;

    /**
     * Constructs an {@code Floor Number}.
     *
     * @param floorNumber A valid floor number.
     */
    public FloorNumber(Integer floorNumber) {
        checkArgument(isValidFloorNumber(floorNumber), MESSAGE_CONSTRAINTS);
        value = floorNumber;
    }

    /**
     * Returns true if a given string is a valid floor number.
     */
    public static boolean isValidFloorNumber(Integer test) {
        return test > 0;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FloorNumber // instanceof handles nulls
                && value.equals(((FloorNumber) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
