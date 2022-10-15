package seedu.nutrigoals.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.nutrigoals.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.nutrigoals.commons.core.GuiSettings;
import seedu.nutrigoals.model.Calorie;
import seedu.nutrigoals.model.Location;
import seedu.nutrigoals.model.Model;
import seedu.nutrigoals.model.NutriGoals;
import seedu.nutrigoals.model.ReadOnlyNutriGoals;
import seedu.nutrigoals.model.ReadOnlyUserPrefs;
import seedu.nutrigoals.model.meal.Food;
import seedu.nutrigoals.model.meal.IsFoodAddedOnThisDatePredicate;
import seedu.nutrigoals.model.user.User;
import seedu.nutrigoals.testutil.UserBuilder;



public class SetupCommandTest {
    @Test
    public void constructor_nullUserThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SetupCommand(null));
    }

    @Test
    public void execute_userAcceptedByModelSuccess() throws Exception {
        ModelStubWithUser modelStub = new ModelStubWithUser();
        User validUser = new UserBuilder().build();

        CommandResult commandResult = new SetupCommand(validUser).execute(modelStub);

        assertEquals(String.format(SetupCommand.MESSAGE_SUCCESS, validUser), commandResult.getFeedbackToUser());
        assertEquals(validUser, modelStub.user);
    }

    @Test
    public void equals() {
        User userA = new UserBuilder().build();
        User userB = new UserBuilder().withGender("F").build();

        SetupCommand addUserA = new SetupCommand(userA);
        SetupCommand addUserB = new SetupCommand(userB);

        assertTrue(addUserA.equals(addUserA));

        assertFalse(addUserA.equals(2));

        assertFalse(addUserA.equals(null));

        assertFalse(addUserA.equals(addUserB));
    }

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

        @Override
        public IsFoodAddedOnThisDatePredicate getDatePredicate() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public boolean isFilteredFoodListEmpty() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public List<Location> getNusGymLocations() {
            throw new AssertionError("This method should not be called");
        }


    }


    private class ModelStubWithUser extends ModelStub {
        final ArrayList<Food> foodsAdded = new ArrayList<>();
        private User user = new User();

        @Override
        public User getUserDetails() {
            return this.user;
        }

        @Override
        public void setUserDetails(User newUser) {
            this.user = newUser;
        }

        @Override
        public ReadOnlyNutriGoals getNutriGoals() {
            return new NutriGoals();
        }
    }
}
