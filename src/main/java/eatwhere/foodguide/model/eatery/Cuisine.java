package eatwhere.foodguide.model.eatery;

import static java.util.Objects.requireNonNull;

import eatwhere.foodguide.commons.util.AppUtil;

/**
 * Represents an Eatery's email in the food guide.
 * Guarantees: immutable; is valid as declared in {@link #isValidCuisine(String)}
 */
public class Cuisine {

    public static final String MESSAGE_CONSTRAINTS =
            "Cuisine names should only contain letters, and it should not be blank";

    /*
     * The first character of the cuisine must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alpha}][\\p{Alpha} ]*";

    public final String value;

    /**
     * Constructs an {@code Cuisine}.
     *
     * @param cuisine A valid email address.
     */
    public Cuisine(String cuisine) {
        requireNonNull(cuisine);
        AppUtil.checkArgument(isValidCuisine(cuisine), MESSAGE_CONSTRAINTS);
        value = cuisine;
    }

    /**
     * Returns if a given string is a valid cuisine.
     */
    public static boolean isValidCuisine(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Cuisine // instanceof handles nulls
                && value.equals(((Cuisine) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
