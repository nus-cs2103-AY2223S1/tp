package seedu.waddle.model.itinerary;

import static java.util.Objects.requireNonNull;
import static seedu.waddle.commons.util.AppUtil.checkArgument;

/**
 * Represents an Itinerary's people element.
 * For now, it only records the number of people, can extend to contain names or person object.
 * Guarantees: immutable; is valid as declared in {@link #isValidPeople(String)}
 */
public class People {

    public static final String MESSAGE_CONSTRAINTS =
            "Number of people should only contain numbers";
    public static final String VALIDATION_REGEX = "\\d*";

    public final String numOfPeople;

    /**
     * Constructs a {@code Name}.
     *
     * @param numOfPeople A valid value.
     */
    public People(String numOfPeople) {
        requireNonNull(numOfPeople);
        checkArgument(isValidPeople(numOfPeople), MESSAGE_CONSTRAINTS);
        this.numOfPeople = numOfPeople;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidPeople(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return numOfPeople;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof People // instanceof handles nulls
                && numOfPeople.equals(((People) other).numOfPeople)); // state check
    }

    @Override
    public int hashCode() {
        return numOfPeople.hashCode();
    }

}
