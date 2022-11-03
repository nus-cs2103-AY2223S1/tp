package seedu.nutrigoals.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.nutrigoals.testutil.Assert.assertThrows;
import static seedu.nutrigoals.testutil.TypicalFoods.MAX_CALORIE_FOOD;
import static seedu.nutrigoals.testutil.TypicalFoods.getTypicalNutriGoals;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.beans.property.DoubleProperty;
import javafx.collections.ObservableList;
import seedu.nutrigoals.commons.core.GuiSettings;
import seedu.nutrigoals.model.Calorie;
import seedu.nutrigoals.model.Location;
import seedu.nutrigoals.model.Model;
import seedu.nutrigoals.model.ModelManager;
import seedu.nutrigoals.model.NutriGoals;
import seedu.nutrigoals.model.ReadOnlyNutriGoals;
import seedu.nutrigoals.model.ReadOnlyUserPrefs;
import seedu.nutrigoals.model.Tip;
import seedu.nutrigoals.model.UserPrefs;
import seedu.nutrigoals.model.meal.Food;
import seedu.nutrigoals.model.meal.IsFoodAddedOnThisDatePredicate;
import seedu.nutrigoals.model.meal.Name;
import seedu.nutrigoals.model.user.User;
import seedu.nutrigoals.testutil.FoodBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_foodAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingFoodAdded modelStub = new ModelStubAcceptingFoodAdded();
        Food validFood = new FoodBuilder().build();

        CommandResult commandResult = new AddCommand(validFood).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validFood), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validFood), modelStub.foodsAdded);
    }

    @Test
    public void execute_foodNotAcceptedByModel_addUnsuccessful() {
        Model model = new ModelManager(getTypicalNutriGoals(), new UserPrefs());

        AddCommand addCommand = new AddCommand(MAX_CALORIE_FOOD);
        String expectedMessage = String.format(AddCommand.MESSAGE_ADDED_CALORIE_TOO_LARGE, MAX_CALORIE_FOOD);

        assertCommandFailure(addCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        Food apple = new FoodBuilder().withName("Apple").build();
        Food bread = new FoodBuilder().withName("Bread").build();
        AddCommand addAppleCommand = new AddCommand(apple);
        AddCommand addBreadCommand = new AddCommand(bread);

        // same object -> returns true
        assertTrue(addAppleCommand.equals(addAppleCommand));

        // same values -> returns true
        AddCommand addAppleCommandCopy = new AddCommand(apple);
        assertTrue(addAppleCommand.equals(addAppleCommandCopy));

        // different types -> returns false
        assertFalse(addAppleCommand.equals(1));

        // null -> returns false
        assertFalse(addAppleCommand.equals(null));

        // different person -> returns false
        assertFalse(addAppleCommand.equals(addBreadCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getNutriGoalsFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setNutriGoalsFilePath(Path nutriGoalsFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addFood(Food food) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setNutriGoals(ReadOnlyNutriGoals newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyNutriGoals getNutriGoals() {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * @param calorieTarget Sets the user's calorie target
         */
        @Override
        public void setCalorieTarget(Calorie calorieTarget) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * @return Calorie User's calorie target
         */
        @Override
        public Calorie getCalorieTarget() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasFood(Food food) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteFood(Food target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFood(Food target, Food editedFood) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Food> getFilteredFoodList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredFoodList(Predicate<Food> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUserDetails(User user) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public User getUserDetails() {
            throw new AssertionError("This method should not be called");
        }

        public IsFoodAddedOnThisDatePredicate getDatePredicate() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isFilteredFoodListEmpty() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Calorie calculateSuggestedCalorie() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public List<Location> getNusGymLocations() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isUserCreated() {
            throw new AssertionError("This method should not be called.");
        }

        public int getCalorieDifference() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Calorie getTotalCalorie() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Food> getUnFilteredFoodList() {
            throw new AssertionError("This method should not be called");
        }

        @Test
        public boolean isAddedTotalCalorieTooLarge(Food toAdd) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isEditedTotalCalorieTooLarge(Food toAdd, Food toDelete) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Map<Name, Calorie> getFoodCalorieList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Tip getTip() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public double calculateCalorieIntakeProgress() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public DoubleProperty getCalorieIntakeProgress() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single food.
     */
    private class ModelStubWithFood extends ModelStub {
        private final Food food;

        ModelStubWithFood(Food food) {
            requireNonNull(food);
            this.food = food;
        }

        @Override
        public boolean hasFood(Food food) {
            requireNonNull(food);
            return this.food.isSameFood(food);
        }
    }

    /**
     * A Model stub that always accept the food being added.
     */
    private class ModelStubAcceptingFoodAdded extends ModelStub {
        final ArrayList<Food> foodsAdded = new ArrayList<>();

        @Override
        public boolean hasFood(Food food) {
            requireNonNull(food);
            return foodsAdded.stream().anyMatch(food::isSameFood);
        }

        @Override
        public void addFood(Food food) {
            requireNonNull(food);
            foodsAdded.add(food);
        }

        @Override
        public ReadOnlyNutriGoals getNutriGoals() {
            return new NutriGoals();
        }

        @Override
        public boolean isAddedTotalCalorieTooLarge(Food toAdd) {
            int totalCalorie = foodsAdded.stream()
                    .map(Food::getCalorie)
                    .map(calorie -> Integer.parseInt(calorie.value))
                    .reduce(0, Integer::sum);
            int calorieToAdd = Integer.parseInt(toAdd.getCalorie().value);
            try {
                Math.addExact(totalCalorie, calorieToAdd);
                return false;
            } catch (ArithmeticException e) {
                return true;
            }
        }
    }
}
