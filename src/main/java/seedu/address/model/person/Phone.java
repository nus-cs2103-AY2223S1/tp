package seedu.address.model.person;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's phone number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class Phone {


    public static final String MESSAGE_CONSTRAINTS =
            "Phone numbers should only contain numbers, and it should be at least 3 digits long";
    public static final String VALIDATION_REGEX = "\\d{3,}";
    public final String value;

    /**
     * Constructs a {@code Phone}.
     *
     * @param phone A valid phone number.
     */
    public Phone(String phone) {
        if (phone != null) {
            checkArgument(isValidPhone(phone), MESSAGE_CONSTRAINTS);
        }
        value = phone;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidPhone(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        if (value == null) {
            return "No phone number";
        }
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            // short circuit if same object
            return true;
        } else if (other instanceof Phone) {
            // instanceof handles nulls
            return value == ((Phone) other).value || value.equals(((Phone) other).value); // state check
        }
        return false; // this is not null while other is null
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
