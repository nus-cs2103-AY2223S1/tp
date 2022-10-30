package seedu.phu.model.internship;

import static java.util.Objects.requireNonNull;
import static seedu.phu.commons.util.AppUtil.checkArgument;

/**
 * Represents an Internship's contact number in the internship book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class Phone {


    public static final String MESSAGE_CONSTRAINTS =
            "Phone numbers follows the following formats: [+COUNTRY_CODE] PHONE_NUMBER"
                    + "They must adhere to the following constraints:\n"
                    + "1. Country code is optional. "
                    + "You may include + at the beginning of your phone number to denote the country code.\n"
                    + "2. The country code can only be 1 to 3 digits long.\n"
                    + "3. The space in-between the country code and the phone number is optional.\n"
                    + "4. The phone number may only contain numbers (no whitespace allowed).\n"
                    + "5. The phone number can only be 3 to 15 digits long.\n"
                    + "Valid example: +65 12345678\n"
                    + "Invalid example: +65 1234 5678";
    public static final String VALIDATION_REGEX = "^(\\+\\d{1,3}( )?)?\\d{3,15}";
    public static final String DEFAULT_VALUE = "NA";

    public final String value;

    /**
     * Constructs a {@code Phone}.
     *
     * @param phone A valid phone number.
     */
    public Phone(String phone) {
        requireNonNull(phone);
        checkArgument(isValidPhone(phone), MESSAGE_CONSTRAINTS);
        value = phone;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidPhone(String test) {
        return test.matches(VALIDATION_REGEX) || test.equals(DEFAULT_VALUE);
    }

    @Override
    public String toString() {
        return value;
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
