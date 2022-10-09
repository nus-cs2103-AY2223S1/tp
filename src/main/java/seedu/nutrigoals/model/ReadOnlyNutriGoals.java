package seedu.nutrigoals.model;

import javafx.collections.ObservableList;
import seedu.nutrigoals.model.meal.Food;

/**
 * Unmodifiable view of NutriGoals
 */
public interface ReadOnlyNutriGoals {

    /**
     * Returns an unmodifiable view of the food list.
     */
    ObservableList<Food> getFoodList();

    Calorie getCalorieTarget();
}
