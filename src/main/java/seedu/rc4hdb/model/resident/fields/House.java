package seedu.rc4hdb.model.resident.fields;

import static seedu.rc4hdb.commons.util.AppUtil.checkArgument;

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

}
