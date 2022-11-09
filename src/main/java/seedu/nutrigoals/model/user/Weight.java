package seedu.nutrigoals.model.user;

import static java.util.Objects.requireNonNull;
import static seedu.nutrigoals.commons.util.AppUtil.checkArgument;

/**
 * Represents a Weight object
 */
public class Weight {
    public static final int MAX_WEIGHT = 200;
    public static final int MIN_WEIGHT = 9;
    public static final String DEFAULT_VALUE = "0";
    public static final String UNIT = "kg";
    public static final String VALIDATION_REGEX = "\\d+";
    public static final String MESSAGE_CONSTRAINTS = "Weight must take on a positive number that "
            + "is between 10 and 199.";
    public final String value;

    /**
     * Initialises a weight object and checks if specified weight is valid.
     * @param weight User's weight
     */
    public Weight(String weight) {
        requireNonNull(weight);
        checkArgument(isValidWeight(weight), MESSAGE_CONSTRAINTS);
        value = weight;
    }

    public Weight() {
        value = DEFAULT_VALUE;
    }

    public boolean isZero() {
        return value.equals(DEFAULT_VALUE);
    }

    /**
     * Checks if input is valid weight
     * @param weight Input weight
     * @return true if weight is a positive integer and weight < 200
     */
    public static boolean isValidWeight(String weight) {
        if (!weight.isEmpty() && weight.matches(VALIDATION_REGEX)) {
            try {
                int weightValue = Integer.parseInt(weight);
                return weightValue > MIN_WEIGHT && weightValue < MAX_WEIGHT;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }

    /**
     * Returns the weight recorded as an integer
     * @return The user's weight in kilogram
     */
    public int getWeight() {
        return Integer.parseInt(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Weight) {
            Weight other = (Weight) obj;
            return this.value.equals(other.value);
        }
        return false;
    }

    @Override
    public String toString() {
        return getWeight() + UNIT;
    }
}

