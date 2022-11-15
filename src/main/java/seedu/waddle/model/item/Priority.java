package seedu.waddle.model.item;

import static java.util.Objects.requireNonNull;
import static seedu.waddle.commons.util.AppUtil.checkArgument;

/**
 * Represents an Item's priority in the Itinerary.
 * Guarantees: number between 1 and 5, default is 1; is valid as declared in {@link #isValidPriority(Integer)}
 */
public class Priority {

    public static final String MESSAGE_CONSTRAINTS =
            "Priority should only contain a number between 1 and 5";

    private final Integer stars;

    /**
     * Constructs a {@code Priority}.
     *
     * @param stars A valid priority.
     */
    public Priority(Integer stars) {
        requireNonNull(stars);
        checkArgument(isValidPriority(stars), MESSAGE_CONSTRAINTS);
        this.stars = stars;
    }

    /**
     * Returns true if a given string is a valid priority.
     */
    public static boolean isValidPriority(Integer test) {
        return test > 0 && test <= 5;
    }

    public int getValue() {
        return this.stars;
    }

    @Override
    public String toString() {
        return this.stars.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Priority // instanceof handles nulls
                && this.stars.equals(((Priority) other).getValue())); // state check
    }

    @Override
    public int hashCode() {
        return this.stars.hashCode();
    }

    public int compareTo(Priority p) {
        return this.stars.compareTo(p.getValue());
    }
}
