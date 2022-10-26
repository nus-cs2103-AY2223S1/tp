package gim.model.exercise;

import static gim.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

/**
 * Represents an Exercise's weight number in the exercise tracker.
 * Guarantees: immutable; is valid as declared in {@link #isValidWeight(String)}
 */
public class Weight implements Comparable<Weight> {


    public static final String MESSAGE_CONSTRAINTS =
            "Weight should only contain non negative numbers, up to 2 decimal places";
    public static final String VALIDATION_REGEX = "\\d{1,}(\\.\\d{1,2})?";
    public final String value;

    /**
     * Constructs a {@code Weight}.
     *
     * @param weight A valid Weight.
     */
    public Weight(String weight) {
        requireNonNull(weight);
        checkArgument(isValidWeight(weight), MESSAGE_CONSTRAINTS);
        value = weight;
    }

    /**
     * Returns true if a given String is a valid Weight.
     */
    public static boolean isValidWeight(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Weight // instanceof handles nulls
                && value.equals(((Weight) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int compareTo(Weight o) {
        return (int) Math.ceil(Double.parseDouble(this.value) - Double.parseDouble(o.value));
    }
}
