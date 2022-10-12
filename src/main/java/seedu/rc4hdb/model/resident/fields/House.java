package seedu.rc4hdb.model.resident.fields;

import static java.util.Objects.requireNonNull;
import static seedu.rc4hdb.commons.util.AppUtil.checkArgument;

/**
 * Represents a Resident's house in RC4HDB.
 * Guarantees: immutable; is valid as declared in {@link #isValidHouse(String)}
 */
public class House extends Field {

    public static final String MESSAGE_CONSTRAINTS =
            "House should only contain the following characters: 'D', 'U', 'L', 'A' or 'N'";

    public static final String VALIDATION_REGEX = "^[DULAN]$";

    public final String house;

    /**
     * Constructs a {@code House}.
     *
     * @param house A valid house.
     */
    public House(String house) {
        requireNonNull(house);
        checkArgument(isValidHouse(house), MESSAGE_CONSTRAINTS);
        this.house = house;
    }

    /**
     * Returns true if a given string is a valid house.
     */
    public static boolean isValidHouse(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns the house string as a full word.
     * @return the house string as a full word.
     */
    public String asFullWord() {
        switch (house) {
        case "D":
            return "Draco";
        case "U":
            return "Ursa";
        case "L":
            return "Leo";
        case "A":
            return "Aquila";
        case "N":
            return "Noctua";
        default:
            return null;
        }
    }

    @Override
    public String toString() {
        return house;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof House // instanceof handles nulls
                && house.equals(((House) other).house)); // state check
    }

    @Override
    public int hashCode() {
        return house.hashCode();
    }

}
