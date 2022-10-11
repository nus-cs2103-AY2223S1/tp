package seedu.nutrigoals.model.user;

import static java.util.Objects.requireNonNull;
import static seedu.nutrigoals.commons.util.AppUtil.checkArgument;

/**
 * Represents a Weight object
 */
public class Weight {
    public static final String VALIDATION_REGEX = "\\d+";
    public static final String MESSAGE_CONSTRAINTS = "Weight must take on a positive number that is not too large.";
    public final String value;

    /**
     * Initialises a weight object and checks if specified weight is valid.
     * @param weight
     */
    public Weight(String weight) {
        requireNonNull(weight);
        checkArgument(isValidWeight(weight), MESSAGE_CONSTRAINTS);
        value = weight;
    }

    /**
     * Checks if input is valid weight
     * @param weight
     * @return true if weight is a positive integer and weight < 200
     */
    public static boolean isValidWeight(String weight) {
        if (!weight.isEmpty() && weight.matches(VALIDATION_REGEX)) {
            try {
                Integer weightValue = Integer.parseInt(weight);
                return weightValue >= 0 && weightValue < 200;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Weight) {
            Weight other = (Weight) obj;
            return this.value.equals(other.value);
        }
        return false;
    }
}

