package seedu.nutrigoals.model;

import static java.util.Objects.requireNonNull;
import static seedu.nutrigoals.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.nutrigoals.commons.core.GuiSettings;
import seedu.nutrigoals.commons.core.LogsCenter;
import seedu.nutrigoals.model.meal.DateTime;
import seedu.nutrigoals.model.meal.Food;
import seedu.nutrigoals.model.meal.IsFoodAddedOnThisDatePredicate;
import seedu.nutrigoals.model.meal.Name;
import seedu.nutrigoals.model.user.User;

/**
 * Represents the in-memory model of the nutrigoals data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final NutriGoals nutriGoals;
    private final UserPrefs userPrefs;
    private final FilteredList<Food> filteredFoods;
    private final FilteredList<Food> unFilteredFoods;
    private final DoubleProperty calorieIntakeProgress;

    private IsFoodAddedOnThisDatePredicate currentDatePredicate;

    /**
     * Initializes a ModelManager with the given nutriGoals and userPrefs.
     */
    public ModelManager(ReadOnlyNutriGoals nutriGoals, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(nutriGoals, userPrefs);

        logger.fine("Initializing with NutriGoals: " + nutriGoals + " and user prefs " + userPrefs);
        this.nutriGoals = new NutriGoals(nutriGoals);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredFoods = new FilteredList<>(this.nutriGoals.getFoodList());
        unFilteredFoods = new FilteredList<>(this.nutriGoals.getFoodList());
        currentDatePredicate = new IsFoodAddedOnThisDatePredicate(new DateTime());
        updateFilteredFoodList(currentDatePredicate);
        calorieIntakeProgress = new SimpleDoubleProperty(calculateCalorieIntakeProgress());
        filteredFoods.addListener((ListChangeListener<Food>) change -> {
            if (currentDatePredicate.getDate().equals(new DateTime().getDate())) {
                calorieIntakeProgress.set(calculateCalorieIntakeProgress());
            }
        });
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
        IsFoodAddedOnThisDatePredicate predicate = new IsFoodAddedOnThisDatePredicate(new DateTime());
        updateFilteredFoodList(predicate);
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
        IsFoodAddedOnThisDatePredicate predicate = new IsFoodAddedOnThisDatePredicate(new DateTime());
        updateFilteredFoodList(predicate);
        nutriGoals.addFood(food);
    }

    @Override
    public void setFood(Food target, Food editedFood) {
        requireAllNonNull(target, editedFood);
        nutriGoals.setFood(target, editedFood);
    }

    @Override
    public List<Location> getNusGymLocations() {
        return this.nutriGoals.getGymLocations();
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
        if (predicate instanceof IsFoodAddedOnThisDatePredicate) {
            currentDatePredicate = (IsFoodAddedOnThisDatePredicate) predicate;
        }
        filteredFoods.setPredicate(predicate);
    }

    /**
     * Returns the most recent {@code IsFoodAddedOnThisDatePredicate} applied on the filtered food list.
     * @return The {@code IsFoodAddedOnThisDatePredicate} that was last applied on the filtered food list.
     */
    @Override
    public IsFoodAddedOnThisDatePredicate getDatePredicate() {
        return currentDatePredicate;
    }

    /**
     * Checks if the filtered food list contains any food items.
     * @return True if the filtered food list contains no food items.
     */
    @Override
    public boolean isFilteredFoodListEmpty() {
        return filteredFoods.isEmpty();
    }

    @Override
    public void setUserDetails(User user) {
        requireNonNull(user);
        nutriGoals.setUser(user);
    }

    @Override
    public User getUserDetails() {
        return nutriGoals.getUser();
    }

    @Override
    public Calorie calculateSuggestedCalorie() {
        return nutriGoals.calculateSuggestedCalorie();
    }

    /**
     * Checks if a profile has been created
     * @return True if a profile has been created, false otherwise
     */
    @Override
    public boolean isUserCreated() {
        return getUserDetails().isUserCreated();
    }

    @Override
    public int getCalorieDifference() {
        Calorie target = nutriGoals.getCalorieTarget();
        Calorie actual = getTotalCalorie();
        return target.calculateDifference(actual);
    }

    @Override
    public Calorie getTotalCalorie() {
        return filteredFoods.stream()
                .map(Food::getCalorie)
                .reduce(new Calorie("0"), Calorie::addCalorie);
    }

    @Override
    public ObservableList<Food> getUnFilteredFoodList() {
        unFilteredFoods.setPredicate(PREDICATE_SHOW_ALL_FOODS);
        return unFilteredFoods;
    }

    public boolean isAddedTotalCalorieTooLarge(Food toAdd) {
        return getTotalCalorie().isCalorieSumTooLarge(toAdd.getCalorie());
    }

    @Override
    public boolean isEditedTotalCalorieTooLarge(Food toAdd, Food toDelete) {
        Calorie calorieToAdd = toAdd.getCalorie();
        Calorie calorieToDelete = toDelete.getCalorie();
        int calorieDifference = calorieToAdd.calculateDifference(calorieToDelete);
        if (calorieDifference <= 0) {
            return false;
        }
        Calorie calorieDifferenceToAdd = new Calorie(Integer.toString(calorieDifference));
        return getTotalCalorie().isCalorieSumTooLarge(calorieDifferenceToAdd);
    }

    @Override
    public Map<Name, Calorie> getFoodCalorieList() {
        return nutriGoals.getFoodCalorieList();
    }

    @Override
    public double calculateCalorieIntakeProgress() {
        Calorie totalCalorie = getTotalCalorie();
        Calorie calorieTarget = getCalorieTarget();
        return totalCalorie.calculateProportion(calorieTarget);
    }

    @Override
    public DoubleProperty getCalorieIntakeProgress() {
        return calorieIntakeProgress;
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
        return nutriGoals.equals(other.nutriGoals)
            && userPrefs.equals(other.userPrefs)
            && filteredFoods.equals(other.filteredFoods)
            && calorieIntakeProgress.getValue().equals(other.calorieIntakeProgress.getValue())
            && currentDatePredicate.equals(other.currentDatePredicate);
    }

    @Override
    public Tip getTip() {
        return nutriGoals.getTip();
    }
}
