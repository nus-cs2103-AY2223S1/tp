package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's description in the address book.
 * Guarantees: immutable; is always valid
 */
public class Description {
    public final String value;

    /**
     * Constructs a {@code Description}.
     *
     * @param description A valid description.
     */
    public Description(String description) {
        requireNonNull(description);
        value = description;
    }

    /**
     * Returns value of description.
     */
    public String getValue() {
        return value;
    }

    /**
     * Returns true if value is empty.
     */
    public boolean isEmpty() {
        return value.equals(Person.EMPTY_FIELD_VALUE);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Description // instanceof handles nulls
                && value.equals(((Description) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
