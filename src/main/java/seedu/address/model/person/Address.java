package seedu.address.model.person;

import seedu.address.ui.PersonProfile;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is always valid
 */
public class Address {
    private final String EMPTY_ADDRESS = "";
    private final String value;

    /**
     * Constructs an {@code Address}.
     *
     * @param address A valid address.
     */
    public Address(String address) {
        requireNonNull(address);
        value = address;
    }

    /**
     * Returns value of address.
     */
    public String getValue() {
        return value;
    }

    /**
     * Returns value of address if not null else, EMPTY_DISPLAY_VALUE.
     */
    public String getDisplayValue() {
        return value == EMPTY_ADDRESS ? PersonProfile.EMPTY_DISPLAY_VALUE : value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
                && value.equals(((Address) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
