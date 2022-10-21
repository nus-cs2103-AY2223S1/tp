package seedu.nutrigoals.model;

import java.util.List;
import java.util.Map;

import javafx.collections.ObservableList;
import seedu.nutrigoals.model.meal.Food;
import seedu.nutrigoals.model.meal.Name;
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

    List<Location> getGymLocations();

    Map<Name, Calorie> getFoodCalorieList();

    /**
     * Returns a random tip from a list of tips.
     */
    String getTip();
}
