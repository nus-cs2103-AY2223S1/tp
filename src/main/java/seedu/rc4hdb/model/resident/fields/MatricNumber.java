package seedu.rc4hdb.model.resident.fields;

import static java.util.Objects.requireNonNull;
import static seedu.rc4hdb.commons.util.AppUtil.checkArgument;

/**
 * Represents a Resident's matriculation number in RC4HDB.
 * Guarantees: immutable; is valid as declared in {@link #isValidMatricNumber(String)}
 */
public class MatricNumber extends Field {

    public static final String MESSAGE_CONSTRAINTS =
            "Matriculation Number should begin with 'A', followed by "
                    + "7 non-negative digits, and ends with an uppercase alphabet";

    /**
     * The first character of the Matriculation Number must be 'A'.
     */
    public static final String VALIDATION_REGEX = "^[A][0-9]{7}[A-Z]$";

    public final String matricNumber;

    /**
     * Constructs a {@code MatricNumber}.
     *
     * @param matricNumber A valid matriculation number.
     */
    public MatricNumber(String matricNumber) {
        requireNonNull(matricNumber);
        checkArgument(isValidMatricNumber(matricNumber), MESSAGE_CONSTRAINTS);
        this.matricNumber = matricNumber;
    }

    /**
     * Returns true if a given string is a valid matriculation number.
     */
    public static boolean isValidMatricNumber(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return matricNumber;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MatricNumber // instanceof handles nulls
                && matricNumber.equals(((MatricNumber) other).matricNumber)); // state check
    }

    @Override
    public int hashCode() {
        return matricNumber.hashCode();
    }

}
