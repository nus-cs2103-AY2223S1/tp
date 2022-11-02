package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents Person's gender in the address book.
 * Guarantees: immutable; is always valid
 */
public class Gender {

    public static final String MESSAGE_CONSTRAINTS =
        "Gender should only be male or female, case-insensitive, and it should not be blank";

    /*
     * The first character of the gender name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^(?i)(male|female)$";

    public final String value;

    /**
     * Constructs an {@code Gender}.
     *
     * @param genderType A valid gender type.
     */
    public Gender(String genderType) {
        requireNonNull(genderType);
        checkArgument(isValidGender(genderType), MESSAGE_CONSTRAINTS);
        value = genderType.toLowerCase();
    }

    /**
     * Returns true if a given string is a valid gender type.
     */
    public static boolean isValidGender(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
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
