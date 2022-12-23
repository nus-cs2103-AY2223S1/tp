package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Plate {

    public static final String MESSAGE_CONSTRAINTS =
            "Expression should match following patterns. eg : AMN0178S\n" +
                    "\n" +
                    "vehicle registration number accepted format is : 3alphabets - 4numeric - 1alphabet.\n";
    // adapted from https://stackoverflow.com/questions/52165059/regex-for-singapore-vehicle-number
    public static final String VALIDATION_REGEX = "^[A-Za-z]{3}[\\d]{4}[A-Za-z]{1}$";
    public final String value;

    /**
     * Constructs a {@code Phone}.
     *
     * @param plate A valid Singapore car plate.
     */
    public Plate(String plate) {
        requireNonNull(plate);
        checkArgument(isValidPlate(plate), MESSAGE_CONSTRAINTS);
        value = plate;
    }

    /**
     * Returns true if a given string is a valid car plate.
     */

    public static boolean isValidPlate(String test) {
        return true;
       //         test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Plate // instanceof handles nulls
                && value.equals(((Plate) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
