package seedu.nutrigoals.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.beans.property.DoubleProperty;
import javafx.collections.ObservableList;
import seedu.nutrigoals.commons.core.GuiSettings;
import seedu.nutrigoals.logic.commands.exceptions.CommandException;
import seedu.nutrigoals.model.Calorie;
import seedu.nutrigoals.model.Location;
import seedu.nutrigoals.model.Model;
import seedu.nutrigoals.model.NutriGoals;
import seedu.nutrigoals.model.ReadOnlyNutriGoals;
import seedu.nutrigoals.model.ReadOnlyUserPrefs;
import seedu.nutrigoals.model.Tip;
import seedu.nutrigoals.model.meal.Food;
import seedu.nutrigoals.model.meal.IsFoodAddedOnThisDatePredicate;
import seedu.nutrigoals.model.meal.Name;
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
        String expectedMessage = "Here are your details: \n" + validMaleUser;
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
        expectedMessage = "Here are your details: \n" + validFemaleUser;
        expectedCommandResult = new CommandResult(expectedMessage);
        assertEquals(actualCommandResult, expectedCommandResult);
    }

    @Test
    public void execute_profileNotCreated_exceptionThrown() {
        ModelStubWithUser model = new ModelStubWithUser();
        assertThrows(CommandException.class, () -> new ProfileCommand().execute(model));
    }

    private static class ModelStub implements Model {

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
        public void setNutriGoals(ReadOnlyNutriGoals nutriGoals) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyNutriGoals getNutriGoals() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCalorieTarget(Calorie calorieTarget) {
            throw new AssertionError("This method should not be called.");
        }

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
        public void addFood(Food food) {
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
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public User getUserDetails() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public IsFoodAddedOnThisDatePredicate getDatePredicate() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isFilteredFoodListEmpty() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Calorie calculateSuggestedCalorie() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public List<Location> getNusGymLocations() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isUserCreated() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
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

        @Override
        public boolean isUserCreated() {
            return user.isUserCreated();
        }
    }

    private static class SetupCommandStub extends SetupCommand {

        private final User user;
        /**
         * Creates a SetupCommandStub for the user
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
