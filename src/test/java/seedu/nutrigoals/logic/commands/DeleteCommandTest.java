package seedu.nutrigoals.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.showFoodAtIndex;
import static seedu.nutrigoals.testutil.TypicalFoods.getTypicalNutriGoals;
import static seedu.nutrigoals.testutil.TypicalIndexes.INDEX_FIRST_MEAL;
import static seedu.nutrigoals.testutil.TypicalIndexes.INDEX_SECOND_MEAL;

import org.junit.jupiter.api.Test;

import seedu.nutrigoals.commons.core.Messages;
import seedu.nutrigoals.commons.core.index.Index;
import seedu.nutrigoals.model.Model;
import seedu.nutrigoals.model.ModelManager;
import seedu.nutrigoals.model.UserPrefs;
import seedu.nutrigoals.model.meal.Food;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalNutriGoals(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Food foodToDelete = model.getFilteredFoodList().get(INDEX_FIRST_MEAL.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_MEAL);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_MEAL_SUCCESS, foodToDelete);

        ModelManager expectedModel = new ModelManager(model.getNutriGoals(), new UserPrefs());
        expectedModel.deleteFood(foodToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredFoodList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_MEAL_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showFoodAtIndex(model, INDEX_FIRST_MEAL);

        Food foodToDelete = model.getFilteredFoodList().get(INDEX_FIRST_MEAL.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_MEAL);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_MEAL_SUCCESS, foodToDelete);

        Model expectedModel = new ModelManager(model.getNutriGoals(), new UserPrefs());
        expectedModel.deleteFood(foodToDelete);
        showNoFood(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showFoodAtIndex(model, INDEX_FIRST_MEAL);

        Index outOfBoundIndex = INDEX_SECOND_MEAL;
        // ensures that outOfBoundIndex is still in bounds of food list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getNutriGoals().getFoodList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_MEAL_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_MEAL);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_MEAL);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_MEAL);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different food -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoFood(Model model) {
        model.updateFilteredFoodList(p -> false);

        assertTrue(model.getFilteredFoodList().isEmpty());
    }
}
