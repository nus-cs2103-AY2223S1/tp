package seedu.address.model.client;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Client's phone number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class ClientPhone {
    public static final String WARNING =
            "Your phone number input does not follow the normal convention of containing only numbers, "
            + "and being at least 3 digits long";

    public static final String VALIDATION_REGEX = "\\d{3,}";

    public final String value;

    private boolean hasWarning;

    /**
     * Constructs a {@code ClientPhone}.
     *
     * @param phone A valid phone number.
     */
    public ClientPhone(String phone) {
        requireNonNull(phone);
        value = phone;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidPhone(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public void setWarning() {
        this.hasWarning = true;
    }

    public boolean hasWarning() {
        return this.hasWarning;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClientPhone // instanceof handles nulls
                && value.equals(((ClientPhone) other).value)); // state check
    }

}
