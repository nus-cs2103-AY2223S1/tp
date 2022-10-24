package seedu.rc4hdb.model.resident.fields;

import static seedu.rc4hdb.commons.util.AppUtil.checkArgument;

import seedu.rc4hdb.model.StringField;

/**
 * Represents a Resident's phone number in RC4HDB.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class Phone extends StringField implements ResidentField {

    public static final String IDENTIFIER = "Phone";

    public static final String MESSAGE_CONSTRAINTS =
            "Phone numbers should only contain numbers, and it should be exactly 8 digits long";

    public static final String VALIDATION_REGEX = "\\d{8}";

    /**
     * Constructs a {@code Phone}.
     *
     * @param phone A valid phone number string.
     */
    public Phone(String phone) {
        super(phone);
        checkArgument(isValidPhone(phone), MESSAGE_CONSTRAINTS);
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidPhone(String test) {
        return test.matches(VALIDATION_REGEX);
    }

}
