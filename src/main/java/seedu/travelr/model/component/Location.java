package seedu.travelr.model.component;

import static java.util.Objects.requireNonNull;
import static seedu.travelr.commons.util.AppUtil.checkArgument;

/**
 * Represents a Trip's location in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidLocation(String)}
 */
public class Location {

    public static final String MESSAGE_CONSTRAINTS =
            "Locations should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the location must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    
    public final String locationName;

    /**
     * Constructs a {@code Name}.
     *
     * @param location A valid name.
     */
    public Location(String location) {
        requireNonNull(location);
        checkArgument(isValidLocation(location), MESSAGE_CONSTRAINTS);
        this.locationName = location;
    }

    /**
     * Returns true if a given string is a valid title.
     */
    public static boolean isValidLocation(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return locationName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Location // instanceof handles nulls
                && locationName.equals(((Location) other).locationName)); // state check
    }

    @Override
    public int hashCode() {
        return locationName.hashCode();
    }

    public int compareTo(Location location) {
        return locationName.compareToIgnoreCase(location.locationName);
    }

}
