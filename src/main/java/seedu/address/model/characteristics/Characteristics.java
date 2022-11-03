package seedu.address.model.characteristics;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Arrays;

/**
 * Represents the characteristics of a property.
 * Individual characteristics are separated by semicolons.
 */
public class Characteristics {
    public static final String MESSAGE_CONSTRAINTS = "If -c flag is used, "
            + "characteristics entry cannot be left blank.";

    public static final Characteristics RESET_CHARACTERISTICS = new Characteristics("RESET");

    private static final String CHARACTERISTIC_DELIMITER = ";";

    private final String[] characteristicsArray;

    /**
     * Constructs a {@code Characteristics}.
     * Guarantees: Immutable, is valid as declared in isValidCharacteristics.
     */
    public Characteristics(String characteristics) {
        requireNonNull(characteristics);
        checkArgument(isValidCharacteristics(characteristics), MESSAGE_CONSTRAINTS);

        characteristicsArray = characteristics.split(CHARACTERISTIC_DELIMITER);
        for (int i = 0; i < characteristicsArray.length; i++) {
            characteristicsArray[i] = characteristicsArray[i].trim();
        }
    }

    /**
     * Returns the characteristics array represented by {@code Characteristics}.
     */
    public String[] getCharacteristicsArray() {
        return characteristicsArray;
    }

    /**
     * Any string input, including an empty one, is a valid characteristic.
     */
    public static boolean isValidCharacteristics(String test) {
        requireNonNull(test);
        return true;
    }

    /**
     * Returns true if a given string is contained in the
     * characteristics array.
     */
    private boolean containsCharacteristic(String characteristic) {
        return Arrays.stream(characteristicsArray)
                .anyMatch(c -> c.toLowerCase().contains(characteristic.toLowerCase()));
    }

    /**
     * Returns true if this {@code Characteristic} contains all the characteristics
     * of the given {@code Characteristic}.
     */
    public boolean containsAllGivenCharacteristics(Characteristics other) {
        return Arrays.stream(other.getCharacteristicsArray())
                .allMatch(c -> containsCharacteristic(c));
    }

    /**
     * Returns true if this {@code Characteristic} contains at least 1 characteristic
     * of the given {@code Characteristic}.
     */
    public boolean containsAnyGivenCharacteristic(Characteristics other) {
        return Arrays.stream(other.getCharacteristicsArray())
                .anyMatch(c -> containsCharacteristic(c));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // string representation has to be exactly the same as in user input format
        // so that when saved and then retrieved from storage, can be parsed back directly
        for (int i = 0; i < characteristicsArray.length; i++) {
            sb.append(characteristicsArray[i])
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
                && Arrays.equals(characteristicsArray, ((Characteristics) other).characteristicsArray)); // state check
    }

    @Override
    public int hashCode() {
        return characteristicsArray.hashCode();
    }

    public boolean isReset() {
        return characteristicsArray[0].equals("RESET");
    }
}
