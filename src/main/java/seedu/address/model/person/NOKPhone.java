package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's next of kin's phone number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidNOKPhone(String)}
 */
public class NOKPhone {


    public static final String MESSAGE_CONSTRAINTS =
            "Phone numbers should only contain numbers, and it should be at least 3 digits long";
    public static final String VALIDATION_REGEX = "\\d{3,}";
    public final String value;

    /**
     * Constructs a {@code NOKPhone}.
     *
     * @param nokPhone A valid next of kin phone number.
     */
    public NOKPhone(String nokPhone) {
        requireNonNull(nokPhone);
        checkArgument(isValidNOKPhone(nokPhone), MESSAGE_CONSTRAINTS);
        value = nokPhone;
    }

    /**
     * Returns true if a given string is a valid next of kin phone number.
     */
    public static boolean isValidNOKPhone(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NOKPhone // instanceof handles nulls
                && value.equals(((NOKPhone) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
