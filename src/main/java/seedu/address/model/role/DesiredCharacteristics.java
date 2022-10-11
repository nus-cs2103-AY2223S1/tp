package seedu.address.model.role;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Arrays;

/**
 * Represents the desired characteristics of a property that will interest a particular buyer.
 * Individual characteristics are separated by semicolons.
 */
public class DesiredCharacteristics {
    public static final String MESSAGE_CONSTRAINTS = "Desired characteristics can take any value, "
            + "and can also be left blank.";

    /*
     * The first character of the desired characteristics must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input (when it should be a null wrapped in an Optional).
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public static final String CHARACTERISTIC_DELIMITER = ";";

    public final String[] characteristics;

    /**
     * Constructs a {@code DesiredCharacteristics}.
     * Guarantees: Immutable, is valid as declared in isValidDesiredCharacteristics.
     */
    public DesiredCharacteristics(String desiredCharacteristics) {
        requireNonNull(desiredCharacteristics);
        checkArgument(isValidDesiredCharacteristics(desiredCharacteristics), MESSAGE_CONSTRAINTS);
        characteristics = desiredCharacteristics.split(CHARACTERISTIC_DELIMITER);

        for (String characteristic: characteristics) {
            characteristic.strip();
        }
    }

    /**
     * Returns true if a given string is valid to be used in desired characteristics.
     */
    public static boolean isValidDesiredCharacteristics(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return Arrays.toString(characteristics);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DesiredCharacteristics // instanceof handles nulls
                && characteristics.equals(((DesiredCharacteristics) other).characteristics)); // state check
    }

    @Override
    public int hashCode() {
        return characteristics.hashCode();
    }

}
