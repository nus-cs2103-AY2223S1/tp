package seedu.rc4hdb.model.resident.fields;

import static seedu.rc4hdb.commons.util.AppUtil.checkArgument;

import seedu.rc4hdb.commons.util.StringUtil;

/**
 * Represents a Resident's matriculation number in RC4HDB.
 * Guarantees: immutable; is valid as declared in {@link #isValidMatricNumber(String)}
 */
public class MatricNumber extends ResidentField {

    public static final String IDENTIFIER = "Matric";

    public static final String MESSAGE_CONSTRAINTS = "Matriculation Number should begin with 'A', followed by "
            + "7 non-negative digits, and ends with an uppercase alphabet";

    /**
     * The first character of the Matriculation Number must be 'A'.
     */
    public static final String VALIDATION_REGEX = "^[A][0-9]{7}[A-Z]$";

    /**
     * Constructs a {@code MatricNumber}.
     *
     * @param matricNumber A valid matriculation number string.
     */
    public MatricNumber(String matricNumber) {
        super(matricNumber);
        checkArgument(isValidMatricNumber(matricNumber), MESSAGE_CONSTRAINTS);
    }

    /**
     * Returns true if a given string is a valid matriculation number.
     */
    public static boolean isValidMatricNumber(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    /**
     * Returns true if given {@code MatricNumber} is contained in this MatricNumber
     * @param matric a substring of a matric number
     * @return true if the given matric number is a substring of matric number
     */
    public boolean contains(String matric) {
        return StringUtil.containsWordIgnoreCase(this.value, matric);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MatricNumber // instanceof handles nulls
                && value.equals(((MatricNumber) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
