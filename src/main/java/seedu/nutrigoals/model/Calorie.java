package seedu.nutrigoals.model;

import static java.util.Objects.requireNonNull;
import static seedu.nutrigoals.commons.util.AppUtil.checkArgument;
/**
 * Represents a calorie object
 */
public class Calorie {
    // The calorie value cannot contain leading 0s.
    public static final String VALIDATION_REGEX = "^(0|[1-9]\\d*)$";
    public static final String MESSAGE_CONSTRAINTS =
        "Calorie must take on a non negative number that is not too large.";
    public final String value;

    /**
     * Initialises a Calorie object.
     */
    public Calorie() {
        value = "2000";
    }

    /**
     * @param calorie
     */
    public Calorie(String calorie) {
        requireNonNull(calorie);
        checkArgument(isValidCalorie(calorie), MESSAGE_CONSTRAINTS);
        value = calorie;
    }

    /**
     * Returns true if a given string is a valid calorie value.
     */
    public static boolean isValidCalorie(String calorie) {
        if (!calorie.isEmpty() && calorie.matches(VALIDATION_REGEX)) {
            try {
                int calorieValue = Integer.parseInt(calorie);
                return calorieValue >= 0;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }

    /**
     * Returns the calorie value as an integer.
     * @return the calorie value.
     */
    public int getCalorieValue() {
        return Integer.parseInt(getValue());
    }

    public String getValue() {
        return value;
    }

    /**
     * Calculates the caloric difference
     * @param other Calorie to subtract
     * @return Caloric difference
     */
    public int calculateDifference(Calorie other) {
        int operand1 = getCalorieValue();
        int operand2 = other.getCalorieValue();
        return operand1 - operand2;
    }

    /**
     * Adds up two calorie
     * @param other Calorie to add
     * @return Sum of two calorie
     */
    public Calorie addCalorie(Calorie other) {
        int operand1 = getCalorieValue();
        int operand2 = other.getCalorieValue();
        int sum = operand1 + operand2;

        return new Calorie(Integer.toString(sum));
    }

    /**
     * Checks if the sum of the values of two {@code Calorie} objects will result in integer overflow.
     * @param other {@code Calorie} to add.
     * @return True if the calorie sum results in integer overflow.
     */
    public boolean isCalorieSumTooLarge(Calorie other) {
        int operand1 = getCalorieValue();
        int operand2 = other.getCalorieValue();
        try {
            Math.addExact(operand1, operand2);
            return false;
        } catch (ArithmeticException e) {
            return true;
        }
    }

    /**
     * Calculates the proportion of this {@code Calorie} to the given {@code Calorie}.
     * @param other The {@code Calorie} to calculate the proportion with respect to.
     * @return The proportion of this {@code Calorie} to the given {@code Calorie}.
     */
    public double calculateProportion(Calorie other) {
        int numerator = getCalorieValue();
        int denominator = other.getCalorieValue();
        if (denominator == 0) {
            return 1.0;
        }
        return (double) numerator / denominator;
    }

    @Override
    public String toString() {
        return getValue() + " calories";
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Calorie) {
            return getValue().equals(((Calorie) other).getValue());
        }
        return false;
    }
}
