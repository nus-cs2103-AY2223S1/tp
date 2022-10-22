package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's country in the address book.
 */
public class Country {

    public static final String MESSAGE_CONSTRAINTS = "Any string can be accepted";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[A-Za-z]*?";

    public final String country;

    /**
     * Constructs a {@code Country}.
     * @param fullCountry
     */
    public Country(String fullCountry) {
        requireNonNull(fullCountry);
        checkArgument(isValidCountry(fullCountry), MESSAGE_CONSTRAINTS);
        country = fullCountry;
    }

    public static boolean isValidCountry(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public static String getMessageConstraints() {
        return MESSAGE_CONSTRAINTS;
    }

    @Override
    public boolean equals(Object other) {
        return this == other //short circuit if same object
                || (other instanceof Country
                && country.equals(((Country) other).country));
    }

    @Override
    public String toString() {
        return country;
    }

}
