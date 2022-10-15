package seedu.nutrigoals.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.nutrigoals.commons.core.GuiSettings;
import seedu.nutrigoals.logic.commands.exceptions.CommandException;
import seedu.nutrigoals.model.Calorie;
import seedu.nutrigoals.model.Model;
import seedu.nutrigoals.model.NutriGoals;
import seedu.nutrigoals.model.ReadOnlyNutriGoals;
import seedu.nutrigoals.model.ReadOnlyUserPrefs;
import seedu.nutrigoals.model.meal.Food;
import seedu.nutrigoals.model.meal.IsFoodAddedOnThisDatePredicate;
import seedu.nutrigoals.model.user.User;
import seedu.nutrigoals.testutil.UserBuilder;

public class ProfileCommandTest {

    @Test
    public void execute_validUser_success() throws CommandException {
        Model model = new ModelStubWithUser();
        User validMaleUser = new UserBuilder().build();

        // male user
        new SetupCommandStub(validMaleUser).execute(model); // set up the profile
        CommandResult actualCommandResult = new ProfileCommand().execute(model);
        String expectedMessage = "Profile created: \n " + validMaleUser;
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertEquals(expectedCommandResult, actualCommandResult);

        // female user
        User validFemaleUser = new UserBuilder().withGender("F")
                .withHeight("165")
                .withWeight("50")
                .withIdeal("50")
                .build();
        new SetupCommandStub(validFemaleUser).execute(model);
        actualCommandResult = new ProfileCommand().execute(model);
        expectedMessage = "Profile created: \n " + validFemaleUser;
        expectedCommandResult = new CommandResult(expectedMessage);
        assertEquals(actualCommandResult, expectedCommandResult);
    }

    private static class ModelStub implements Model {

        /**
         * Replaces user prefs data with the data in {@code userPrefs}.
         *
         * @param userPrefs
         */
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Returns the user prefs.
         */
        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Returns the user prefs' GUI settings.
         */
        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Sets the user prefs' GUI settings.
         *
         * @param guiSettings
         */
        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Returns the user prefs' food list
         * file path.
         */
        @Override
        public Path getNutriGoalsFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Sets the user prefs' food list
         * file path.
         *
         * @param nutriGoalsFilePath
         */
        @Override
        public void setNutriGoalsFilePath(Path nutriGoalsFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Replaces food list
         * data with the data in {@code nutriGoals}.
         *
         * @param nutriGoals
         */
        @Override
        public void setNutriGoals(ReadOnlyNutriGoals nutriGoals) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Returns the NutriGoals
         */
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

        /**
         * Returns true if a food with the same identity as {@code food} exists in the food list
         * .
         *
         * @param food
         */
        @Override
        public boolean hasFood(Food food) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Deletes the given food.
         * The food must exist in the food list
         * .
         *
         * @param target
         */
        @Override
        public void deleteFood(Food target) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Adds the given food.
         * {@code food} must not already exist in the food list
         * .
         *
         * @param food
         */
        @Override
        public void addFood(Food food) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Replaces the given food {@code target} with {@code editedfood}.
         * {@code target} must exist in the food list
         * .
         * The food identity of {@code editedfood} must not be the same as another existing food in the food list
         * .
         *
         * @param target
         * @param editedFood
         */
        @Override
        public void setFood(Food target, Food editedFood) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Returns an unmodifiable view of the filtered food list
         */
        @Override
        public ObservableList<Food> getFilteredFoodList() {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Updates the filter of the filtered food list to filter by the given {@code predicate}.
         *
         * @param predicate
         * @throws NullPointerException if {@code predicate} is null.
         */
        @Override
        public void updateFilteredFoodList(Predicate<Food> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * @param user
         */
        @Override
        public void setUserDetails(User user) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * @return
         */
        @Override
        public User getUserDetails() {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Returns the most recent {@code IsFoodAddedOnThisDatePredicate} applied on the filtered food list
         */
        @Override
        public IsFoodAddedOnThisDatePredicate getDatePredicate() {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Returns true if the filtered food list has no food items
         */
        @Override
        public boolean isFilteredFoodListEmpty() {
            throw new AssertionError("This method should not be called.");
        }
    }

    private static class ModelStubWithUser extends ModelStub {
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

    private static class SetupCommandStub extends SetupCommand {

        private final User user;
        /**
         * Creates a SetupCommand for the user
         *
         * @param user User profile to set up
         */
        public SetupCommandStub(User user) {
            super(user);
            this.user = user;
        }

        @Override
        public CommandResult execute(Model model) {
            requireNonNull(model);
            model.setUserDetails(user);
            return new CommandResult(String.format(MESSAGE_SUCCESS, model.getUserDetails()));
        }
    }
}
