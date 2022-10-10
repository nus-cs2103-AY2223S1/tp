package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's birthday in the address book.
 * Guarantees: immutable; is always valid
 */
public class Birthday {
    public final String value;

    /**
     * Constructor for Birthday object.
     *
     * @param birthday
     */
    public Birthday(String birthday) {
        requireNonNull(birthday);
        value = birthday;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Birthday // instanceof handles nulls
                && value.equals(((Birthday) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
