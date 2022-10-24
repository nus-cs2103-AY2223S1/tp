package seedu.rc4hdb.model.resident.fields;

import static seedu.rc4hdb.commons.util.AppUtil.checkArgument;

import seedu.rc4hdb.commons.util.StringUtil;

/**
 * Represents a Resident's gender in RC4HDB.
 * Guarantees: immutable; is valid as declared in {@link #isValidGender(String)}
 */
public class Gender extends ResidentField {

    public static final String IDENTIFIER = "Gender";

    public static final String MESSAGE_CONSTRAINTS = "Gender should be either 'M' or 'F'";

    public static final String VALIDATION_REGEX = "^[MF]$";

    /**
     * Constructs a {@code Gender}.
     *
     * @param gender A valid gender string.
     */
    public Gender(String gender) {
        super(gender);
        checkArgument(isValidGender(gender), MESSAGE_CONSTRAINTS);
    }

    /**
     * Returns true if a given string is a valid gender.
     */
    public static boolean isValidGender(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns gender string as a full word.
     * @return gender string as a full word.
     */
    public String asFullWord() {
        if (this.value == "M") {
            return "Male";
        } else {
            return "Female";
        }
    }

    @Override
    public String toString() {
        return value;
    }

    /**
     * Returns true if given {@code Gender} is contained in this gender
     * @param gender a substring of gender
     * @return true if the gender is a substring of gender
     */
    public boolean contains(String gender) {
        return StringUtil.containsWordIgnoreCase(value, gender);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Gender // instanceof handles nulls
                && value.equals(((Gender) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

