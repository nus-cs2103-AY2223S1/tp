package seedu.nutrigoals.testutil;

import seedu.nutrigoals.model.NutriGoals;
import seedu.nutrigoals.model.meal.Food;

/**
 * A utility class to help with building NutriGoals objects.
 * Example usage: <br>
 *     {@code NutriGoals ab = new NutriGoalsBuilder().withFood("John", "Doe").build();}
 */
public class NutriGoalsBuilder {

    private NutriGoals nutriGoals;

    public NutriGoalsBuilder() {
        nutriGoals = new NutriGoals();
    }

    public NutriGoalsBuilder(NutriGoals nutriGoals) {
        this.nutriGoals = nutriGoals;
    }

    /**
     * Adds a new {@code Food} to the {@code NutriGoals} that we are building.
     */
    public NutriGoalsBuilder withFood(Food food) {
        nutriGoals.addFood(food);
        return this;
    }

    public NutriGoals build() {
        return nutriGoals;
    }
}
