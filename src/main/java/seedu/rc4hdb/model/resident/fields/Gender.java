package seedu.rc4hdb.model.resident.fields;

import static seedu.rc4hdb.commons.util.AppUtil.checkArgument;

import seedu.rc4hdb.model.StringField;

/**
 * Represents a Resident's gender in RC4HDB.
 * Guarantees: immutable; is valid as declared in {@link #isValidGender(String)}
 */
public class Gender extends StringField implements ResidentField {

    public static final String IDENTIFIER = "Gender";

    public static final String MESSAGE_CONSTRAINTS = "Gender should be either 'M' or 'F'";

    public static final String VALIDATION_REGEX = "^[MF]$";

    /**
     * Constructs a {@code Gender}.
     *
     * @param gender A valid gender string.
     */
    public Gender(String gender) {
        super(gender.toUpperCase());
        checkArgument(isValidGender(gender), MESSAGE_CONSTRAINTS);
    }

    /**
     * Returns true if a given string is a valid gender.
     */
    public static boolean isValidGender(String test) {
        return test.toUpperCase().matches(VALIDATION_REGEX);
    }

}

