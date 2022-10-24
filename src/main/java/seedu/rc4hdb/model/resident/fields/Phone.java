package seedu.rc4hdb.model.resident.fields;

import static seedu.rc4hdb.commons.util.AppUtil.checkArgument;

import seedu.rc4hdb.commons.util.StringUtil;

/**
 * Represents a Resident's phone number in RC4HDB.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class Phone extends ResidentField {

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

    @Override
    public String toString() {
        return value;
    }

    /**
     * Returns true if given {@code Phone} is contained in this phone
     * @param phone a substring of a phone number
     * @return true if the given phone value is a substring of value
     */
    public boolean contains(String phone) {
        return StringUtil.containsWordIgnoreCase(this.value, phone);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Phone // instanceof handles nulls
                && value.equals(((Phone) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
