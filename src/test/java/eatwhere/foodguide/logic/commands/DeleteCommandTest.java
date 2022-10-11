package eatwhere.foodguide.logic.commands;

import static eatwhere.foodguide.logic.commands.CommandTestUtil.assertCommandSuccess;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import eatwhere.foodguide.commons.core.Messages;
import eatwhere.foodguide.commons.core.index.Index;
import eatwhere.foodguide.model.Model;
import eatwhere.foodguide.model.ModelManager;
import eatwhere.foodguide.model.UserPrefs;
import eatwhere.foodguide.model.eatery.Eatery;
import eatwhere.foodguide.testutil.TypicalEateries;
import eatwhere.foodguide.testutil.TypicalIndexes;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(TypicalEateries.getTypicalFoodGuide(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Eatery eateryToDelete = model.getFilteredEateryList().get(TypicalIndexes.INDEX_FIRST_EATERY.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(TypicalIndexes.INDEX_FIRST_EATERY);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_EATERY_SUCCESS, eateryToDelete);

        ModelManager expectedModel = new ModelManager(model.getFoodGuide(), new UserPrefs());
        expectedModel.deleteEatery(eateryToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredEateryList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        CommandTestUtil.assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_EATERY_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        CommandTestUtil.showEateryAtIndex(model, TypicalIndexes.INDEX_FIRST_EATERY);

        Eatery eateryToDelete = model.getFilteredEateryList().get(TypicalIndexes.INDEX_FIRST_EATERY.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(TypicalIndexes.INDEX_FIRST_EATERY);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_EATERY_SUCCESS, eateryToDelete);

        Model expectedModel = new ModelManager(model.getFoodGuide(), new UserPrefs());
        expectedModel.deleteEatery(eateryToDelete);
        showNoEatery(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        CommandTestUtil.showEateryAtIndex(model, TypicalIndexes.INDEX_FIRST_EATERY);

        Index outOfBoundIndex = TypicalIndexes.INDEX_SECOND_EATERY;
        // ensures that outOfBoundIndex is still in bounds of food guide list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getFoodGuide().getEateryList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        CommandTestUtil.assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_EATERY_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(TypicalIndexes.INDEX_FIRST_EATERY);
        DeleteCommand deleteSecondCommand = new DeleteCommand(TypicalIndexes.INDEX_SECOND_EATERY);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(TypicalIndexes.INDEX_FIRST_EATERY);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different eatery -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoEatery(Model model) {
        model.updateFilteredEateryList(p -> false);

        assertTrue(model.getFilteredEateryList().isEmpty());
    }
}
