package seedu.address.model.desiredcharacteristics;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Arrays;

/**
 * Represents the desired characteristics of a property that will interest a particular buyer.
 * Individual characteristics are separated by semicolons.
 */
public class DesiredCharacteristics {
    public static final String MESSAGE_CONSTRAINTS = "If -c flag is used, desired characteristics entry cannot be left blank.";

    /*
     * The first character of the desired characteristics must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
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
        for (int i = 0; i < characteristics.length; i++) {
            characteristics[i] = characteristics[i].trim();
        }
    }

    /**
     * Returns true if a given user-input string is valid to be used in desired characteristics.
     */
    public static boolean isValidDesiredCharacteristics(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // string representation has to be exactly the same as in user input format
        // so that when saved and then retrieved from storage, can be parsed back directly 
        for (int i = 0; i < characteristics.length; i++) {
            sb.append(characteristics[i])
                    .append("; ");
        }
        // remove last "; " as we don't want it to be repeatedly appended
        // as we store the string and retrieve it repeatedly 
        return sb.substring(0, sb.length() - 2);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DesiredCharacteristics // instanceof handles nulls
                && Arrays.equals(characteristics, ((DesiredCharacteristics) other).characteristics)); // state check
    }

    @Override
    public int hashCode() {
        return characteristics.hashCode();
    }

}
