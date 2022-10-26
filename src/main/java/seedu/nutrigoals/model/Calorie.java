package seedu.nutrigoals.model;

import static java.util.Objects.requireNonNull;
import static seedu.nutrigoals.commons.util.AppUtil.checkArgument;
/**
 * Represents a calorie object
 */
public class Calorie {
    public static final String VALIDATION_REGEX = "\\d+";
    public static final String MESSAGE_CONSTRAINTS =
        "Calorie must take on a non negative number that is not too large.";
    public final String value;

    /**
     * Initialises a Calorie object.
     */
    public Calorie() {
        this.value = "2000";
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
        return Integer.parseInt(value);
    }

    /**
     * Calculates the caloric difference
     * @param other Calorie to subtract
     * @return Caloric difference
     */
    public int calculateDifference(Calorie other) {
        int operand1 = this.getCalorieValue();
        int operand2 = other.getCalorieValue();
        return operand1 - operand2;
    }

    /**
     * Adds up two calorie
     * @param other Calorie to add
     * @return Sum of two calorie
     */
    public Calorie addCalorie(Calorie other) {
        int operand1 = this.getCalorieValue();
        int operand2 = other.getCalorieValue();
        int sum = operand1 + operand2;

        return new Calorie(Integer.toString(sum));
    }

    /**
     * Calculates the proportion of this {@code Calorie} to the given {@code Calorie}.
     * @param other The {@code Calorie} to calculate the proportion with respect to.
     * @return The proportion of this {@code Calorie} to the given {@code Calorie}.
     */
    public double calculateProportion(Calorie other) {
        int operand1 = this.getCalorieValue();
        int operand2 = other.getCalorieValue();
        return (double) operand1 / operand2;
    }

    @Override
    public String toString() {
        return value + " calories";
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Calorie) {
            return this.value.equals(((Calorie) other).value);
        }
        return false;
    }
}
