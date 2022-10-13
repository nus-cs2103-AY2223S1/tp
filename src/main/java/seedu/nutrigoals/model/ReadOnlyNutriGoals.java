package seedu.nutrigoals.model;

import javafx.collections.ObservableList;
import seedu.nutrigoals.model.meal.Food;
import seedu.nutrigoals.model.user.User;

/**
 * Unmodifiable view of NutriGoals
 */
public interface ReadOnlyNutriGoals {

    /**
     * Returns an unmodifiable view of the food list.
     */
    ObservableList<Food> getFoodList();

    Calorie getCalorieTarget();

    User getUser();
}
