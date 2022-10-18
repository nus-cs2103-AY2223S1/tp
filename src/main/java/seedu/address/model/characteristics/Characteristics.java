package seedu.address.model.characteristics;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Arrays;

import seedu.address.commons.util.StringUtil;

/**
 * Represents the characteristics of a property.
 * Individual characteristics are separated by semicolons.
 */
public class Characteristics {
    public static final String MESSAGE_CONSTRAINTS = "If -c flag is used, "
            + "characteristics entry cannot be left blank.";

    /*
     * The first character of the characteristics must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public static final String CHARACTERISTIC_DELIMITER = ";";

    public final String[] characteristicsArr;

    /**
     * Constructs a {@code Characteristics}.
     * Guarantees: Immutable, is valid as declared in isValidCharacteristics.
     */
    public Characteristics(String characteristics) {
        requireNonNull(characteristics);
        checkArgument(isValidCharacteristics(characteristics), MESSAGE_CONSTRAINTS);

        characteristicsArr = characteristics.split(CHARACTERISTIC_DELIMITER);
        for (int i = 0; i < characteristicsArr.length; i++) {
            characteristicsArr[i] = characteristicsArr[i].trim();
        }
    }

    /**
     * Returns true if a given user-input string is valid to be used in desired characteristics.
     */
    public static boolean isValidCharacteristics(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given characteristic is contained in the
     * characteristics array.
     */
    public boolean containsCharacteristic(String characteristic) {
        return Arrays.stream(characteristicsArr)
                .anyMatch(c -> c.contains(characteristic));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // string representation has to be exactly the same as in user input format
        // so that when saved and then retrieved from storage, can be parsed back directly
        for (int i = 0; i < characteristicsArr.length; i++) {
            sb.append(characteristicsArr[i])
                    .append("; ");
        }
        // remove last "; " as we don't want it to be repeatedly appended
        // as we store the string and retrieve it repeatedly
        return sb.substring(0, sb.length() - 2);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Characteristics // instanceof handles nulls
                && Arrays.equals(characteristicsArr, ((Characteristics) other).characteristicsArr)); // state check
    }

    @Override
    public int hashCode() {
        return characteristicsArr.hashCode();
    }

}
