package seedu.nutrigoals.model;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import javafx.beans.property.DoubleProperty;
import javafx.collections.ObservableList;
import seedu.nutrigoals.commons.core.GuiSettings;
import seedu.nutrigoals.model.meal.Food;
import seedu.nutrigoals.model.meal.IsFoodAddedOnThisDatePredicate;
import seedu.nutrigoals.model.meal.Name;
import seedu.nutrigoals.model.user.User;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Food> PREDICATE_SHOW_ALL_FOODS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' food list
     * file path.
     */
    Path getNutriGoalsFilePath();

    /**
     * Sets the user prefs' food list
     * file path.
     */
    void setNutriGoalsFilePath(Path nutriGoalsFilePath);

    /**
     * Replaces food list
     * data with the data in {@code nutriGoals}.
     */
    void setNutriGoals(ReadOnlyNutriGoals nutriGoals);

    /** Returns the NutriGoals */
    ReadOnlyNutriGoals getNutriGoals();

    /**
     * @param calorieTarget Sets the user's calorie target
     */
    void setCalorieTarget(Calorie calorieTarget);

    /**
     * @return Calorie User's calorie target
     */
    Calorie getCalorieTarget();

    /**
     * Returns true if a food with the same identity as {@code food} exists in the food list.
     */
    boolean hasFood(Food food);

    /**
     * Deletes the given food.
     * The food must exist in the food list.
     */
    void deleteFood(Food target);

    /**
     * Adds the given food.
     * {@code food} food to be added.
     */
    void addFood(Food food);

    /**
     * Replaces the given food {@code target} with {@code editedfood}.
     * {@code target} must exist in the food list.
     * The food identity of {@code editedfood} must not be the same as another existing food in the food list.
     */
    void setFood(Food target, Food editedFood);

    /** Returns an unmodifiable view of the filtered food list */
    ObservableList<Food> getFilteredFoodList();

    /**
     * Updates the filter of the filtered food list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredFoodList(Predicate<Food> predicate);

    void setUserDetails(User user);

    User getUserDetails();

    /** Returns the most recent {@code IsFoodAddedOnThisDatePredicate} applied on the filtered food list */
    IsFoodAddedOnThisDatePredicate getDatePredicate();

    /** Returns true if the filtered food list has no food items */
    boolean isFilteredFoodListEmpty();

    Calorie calculateSuggestedCalorie();

    List<Location> getNusGymLocations();

    boolean isUserCreated();

    int getCalorieDifference();

    Calorie getTotalCalorie();

    ObservableList<Food> getUnFilteredFoodList();

    boolean isAddedTotalCalorieTooLarge(Food toAdd);

    boolean isEditedTotalCalorieTooLarge(Food toAdd, Food toDelete);

    /** Returns the {@code Map} of food items and their calorie content */
    Map<Name, Calorie> getFoodCalorieList();

    /** Retrieves and returns a random tip from TipList */
    Tip getTip();

    double calculateCalorieIntakeProgress();

    DoubleProperty getCalorieIntakeProgress();
}
