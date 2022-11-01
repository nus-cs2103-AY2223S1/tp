package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's location in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidLoc(String)}
 */
public class Location {
    public static final String MESSAGE_CONSTRAINTS =
            "Locations should only contain alphanumeric characters and spaces, and it should not be blank";

    public final String location;

    /**
     * Constructs a {@code Location}.
     *
     * @param location A valid location.
     */
    public Location(String location) {
        requireNonNull(location);
        this.location = location;
    }

    /**
     * Compares a location with another location.
     * @param loc The other location.
     */
    public int compareTo(Location loc) {
        return this.location.compareTo(loc.location);
    }

    @Override
    public String toString() {
        return location;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Location // instanceof handles nulls
                && location.equals(((Location) other).location)); // state check
    }

    @Override
    public int hashCode() {
        return location.hashCode();
    }

}
