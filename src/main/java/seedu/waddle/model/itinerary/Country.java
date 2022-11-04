package seedu.waddle.model.itinerary;

import static java.util.Objects.requireNonNull;
import static seedu.waddle.commons.util.AppUtil.checkArgument;

/**
 * Represents an Itinerary's country in Waddle.
 * Guarantees: immutable; is valid as declared in {@link #isValidCountry(String)}
 */
public class Country {

    public static final String MESSAGE_CONSTRAINTS =
            "Country should only contain alphanumeric characters and spaces";

    /*
     * The first character of the country must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String country;

    /**
     * Constructs a {@code Name}.
     *
     * @param country A valid country.
     */
    public Country(String country) {
        requireNonNull(country);
        checkArgument(isValidCountry(country), MESSAGE_CONSTRAINTS);
        this.country = country;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidCountry(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        if (country.equals("default")) {
            return "(Not specified)";
        }
        return country;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Country // instanceof handles nulls
                && country.equals(((Country) other).country)); // state check
    }

    @Override
    public int hashCode() {
        return country.hashCode();
    }

}
