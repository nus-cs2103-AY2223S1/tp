package seedu.rc4hdb.model.resident.fields;

import static seedu.rc4hdb.commons.util.AppUtil.checkArgument;

import seedu.rc4hdb.commons.util.StringUtil;

/**
 * Represents a Resident's house in RC4HDB.
 * Guarantees: immutable; is valid as declared in {@link #isValidHouse(String)}
 */
public class House extends ResidentField {

    public static final String IDENTIFIER = "House";

    public static final String MESSAGE_CONSTRAINTS =
            "House should only contain the following characters: 'D', 'U', 'L', 'A' or 'N'";

    public static final String VALIDATION_REGEX = "^[DULAN]$";

    /**
     * Constructs a {@code House}.
     *
     * @param house A valid house string.
     */
    public House(String house) {
        super(house);
        checkArgument(isValidHouse(house), MESSAGE_CONSTRAINTS);
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
        switch (value) {
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
        return value;
    }

    /**
     * Returns true if given {@code House} is contained in this House
     * @param house a substring of house
     * @return true if the given house is a substring of house
     */
    public boolean contains(String house) {
        return StringUtil.containsWordIgnoreCase(this.value, house);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof House // instanceof handles nulls
                && value.equals(((House) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
