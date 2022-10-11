package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's next of kin's phone number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidNokPhone(String)}
 */
public class NokPhone {


    public static final String MESSAGE_CONSTRAINTS =
            "Phone numbers should only contain numbers, and it should be at least 3 digits long";
    public static final String VALIDATION_REGEX = "\\d{3,}";
    public final String value;

    /**
     * Constructs a {@code NokPhone}.
     *
     * @param nokPhone A valid next of kin phone number.
     */
    public NokPhone(String nokPhone) {
        requireNonNull(nokPhone);
        checkArgument(isValidNokPhone(nokPhone), MESSAGE_CONSTRAINTS);
        value = nokPhone;
    }

    /**
     * Constructs a {@code NokPhone} with default value "000".
     */
    public NokPhone() {
        value = "000";
    }

    /**
     * Returns true if a given string is a valid next of kin phone number.
     */
    public static boolean isValidNokPhone(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NokPhone // instanceof handles nulls
                && value.equals(((NokPhone) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
