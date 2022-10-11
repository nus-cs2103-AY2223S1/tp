package seedu.nutrigoals.model;

import static java.util.Objects.requireNonNull;
import static seedu.nutrigoals.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.nutrigoals.commons.core.GuiSettings;
import seedu.nutrigoals.commons.core.LogsCenter;
import seedu.nutrigoals.model.meal.Food;
import seedu.nutrigoals.model.meal.IsFoodAddedTodayPredicate;
import seedu.nutrigoals.model.user.User;

/**
 * Represents the in-memory model of the nutrigoals data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final NutriGoals nutriGoals;
    private final UserPrefs userPrefs;
    private final FilteredList<Food> filteredFoods;
    private User user;

    /**
     * Initializes a ModelManager with the given nutriGoals and userPrefs.
     */
    public ModelManager(ReadOnlyNutriGoals nutriGoals, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(nutriGoals, userPrefs);

        logger.fine("Initializing with NutriGoals: " + nutriGoals + " and user prefs " + userPrefs);
        this.nutriGoals = new NutriGoals(nutriGoals);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredFoods = new FilteredList<>(this.nutriGoals.getFoodList().filtered(new IsFoodAddedTodayPredicate()));
    }

    public ModelManager() {
        this(new NutriGoals(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getNutriGoalsFilePath() {
        return userPrefs.getNutriGoalsFilePath();
    }

    @Override
    public void setNutriGoalsFilePath(Path nutriGoalsFilePath) {
        requireNonNull(nutriGoalsFilePath);
        userPrefs.setNutriGoalsFilePath(nutriGoalsFilePath);
    }

    //=========== NutriGoals ================================================================================

    @Override
    public void setNutriGoals(ReadOnlyNutriGoals nutriGoals) {
        this.nutriGoals.resetData(nutriGoals);
    }

    @Override
    public ReadOnlyNutriGoals getNutriGoals() {
        return nutriGoals;
    }

    /**
     * @param calorieTarget Sets the user's calorie target
     */
    @Override
    public void setCalorieTarget(Calorie calorieTarget) {
        requireNonNull(calorieTarget);
        nutriGoals.setCalorieTarget(calorieTarget);
    }

    /**
     * @return Calorie User's calorie target
     */
    @Override
    public Calorie getCalorieTarget() {
        return this.nutriGoals.getCalorieTarget();
    }

    @Override
    public boolean hasFood(Food food) {
        requireNonNull(food);
        return nutriGoals.hasFood(food);
    }

    @Override
    public void deleteFood(Food target) {
        nutriGoals.removeFood(target);
    }

    @Override
    public void addFood(Food food) {
        nutriGoals.addFood(food);
        updateFilteredFoodList(new IsFoodAddedTodayPredicate());
    }

    @Override
    public void setFood(Food target, Food editedFood) {
        requireAllNonNull(target, editedFood);

        nutriGoals.setFood(target, editedFood);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedNutriGoals}
     */
    @Override
    public ObservableList<Food> getFilteredFoodList() {
        return filteredFoods;
    }

    @Override
    public void updateFilteredFoodList(Predicate<Food> predicate) {
        requireNonNull(predicate);
        filteredFoods.setPredicate(predicate);
    }

    @Override
    public void setUserDetails(User user) {
        requireNonNull(user);
        nutriGoals.setUser(user);
    }

    @Override
    public User getUserDetails() {
        return user;
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        boolean isEqual = nutriGoals.equals(other.nutriGoals)
            && userPrefs.equals(other.userPrefs)
            && filteredFoods.equals(other.filteredFoods);
        return isEqual;
    }

}
