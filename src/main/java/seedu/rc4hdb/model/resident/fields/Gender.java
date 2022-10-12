package seedu.rc4hdb.model.resident.fields;

import static java.util.Objects.requireNonNull;
import static seedu.rc4hdb.commons.util.AppUtil.checkArgument;

/**
 * Represents a Resident's gender in RC4HDB.
 * Guarantees: immutable; is valid as declared in {@link #isValidGender(String)}
 */
public class Gender extends Field {

    public static final String MESSAGE_CONSTRAINTS =
            "Gender should be either 'M' or 'F'";

    public static final String VALIDATION_REGEX = "^[MF]$";

    public final String gender;

    /**
     * Constructs a {@code Name}.
     *
     * @param gender A valid gender.
     */
    public Gender(String gender) {
        requireNonNull(gender);
        checkArgument(isValidGender(gender), MESSAGE_CONSTRAINTS);
        this.gender = gender;
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
        if (gender == "M") {
            return "Male";
        } else {
            return "Female";
        }
    }

    @Override
    public String toString() {
        return gender;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Gender // instanceof handles nulls
                && gender.equals(((Gender) other).gender)); // state check
    }

    @Override
    public int hashCode() {
        return gender.hashCode();
    }

}

