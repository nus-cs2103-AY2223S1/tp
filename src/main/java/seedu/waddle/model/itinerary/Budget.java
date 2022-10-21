package seedu.waddle.model.itinerary;

import static java.util.Objects.requireNonNull;
import static seedu.waddle.commons.util.AppUtil.checkArgument;

public class Budget {
    public static final String MESSAGE_CONSTRAINTS =
            "Budget should only contain numbers.";
    public static final String VALIDATION_REGEX = "\\d+([.][0-9]+)?$";
    private final float budget;

    /**
     * Constructs a {@code Budget}.
     *
     * @param budgetStr A valid value.
     */
    public Budget(String budgetStr) {
        requireNonNull(budgetStr);
        checkArgument(isValidBudget(budgetStr), MESSAGE_CONSTRAINTS);
        this.budget = Float.parseFloat(budgetStr);
    }

    /**
     * Returns true if a given string is a valid budget.
     */
    public static boolean isValidBudget(String test) {
        if (!test.matches(VALIDATION_REGEX)) {
            return false;
        }
        float budget;
        try {
            budget = Float.valueOf(test);
        } catch (NumberFormatException e) {
            return false;
        }
        return budget >= 0;
    }


    @Override
    public String toString() {
        return String.valueOf(this.budget);
    }

    public float getBudget() {
        return this.budget;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Budget // instanceof handles nulls
                && this.budget == (((Budget) other).getBudget())); // state check
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

}
