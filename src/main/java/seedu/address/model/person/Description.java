package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import seedu.address.ui.PersonProfile;

/**
 * Represents a Person's description in the address book.
 * Guarantees: immutable; is always valid
 */
public class Description {
    public static final String EMPTY_DESCRIPTION = "";
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
     * Returns value of description if not null else, EMPTY_DISPLAY_VALUE.
     */
    public String getDisplayValue() {
        return value == EMPTY_DESCRIPTION ? PersonProfile.EMPTY_DISPLAY_VALUE : value;
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
