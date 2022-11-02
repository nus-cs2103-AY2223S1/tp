package seedu.rc4hdb.model.resident.fields;

import static seedu.rc4hdb.commons.util.AppUtil.checkArgument;

import java.util.Locale;

import seedu.rc4hdb.model.StringField;

/**
 * Represents a Resident's matriculation number in RC4HDB.
 * Guarantees: immutable; is valid as declared in {@link #isValidMatricNumber(String)}
 */
public class MatricNumber extends StringField implements ResidentField {

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
        super(matricNumber.toUpperCase());
        checkArgument(isValidMatricNumber(matricNumber), MESSAGE_CONSTRAINTS);
    }

    /**
     * Returns true if a given string is a valid matriculation number.
     */
    public static boolean isValidMatricNumber(String test) {
        return test.toUpperCase().matches(VALIDATION_REGEX);
    }

}
