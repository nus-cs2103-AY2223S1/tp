package seedu.foodrem.logic.commands.itemcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.foodrem.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.foodrem.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.foodrem.logic.commands.CommandTestUtil.showItemAtIndex;
import static seedu.foodrem.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.foodrem.testutil.TypicalIndexes.INDEX_SECOND_ITEM;

import org.junit.jupiter.api.Test;

import seedu.foodrem.commons.core.Messages;
import seedu.foodrem.commons.core.index.Index;
import seedu.foodrem.model.Model;
import seedu.foodrem.model.ModelManager;
import seedu.foodrem.model.UserPrefs;
import seedu.foodrem.model.item.Item;
import seedu.foodrem.testutil.TypicalFoodRem;
import seedu.foodrem.viewmodels.ItemWithMessage;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {
    private static final String EXPECTED_SUCCESS_MESSAGE = "Successfully deleted the following item:";

    private final Model model = new ModelManager(TypicalFoodRem.getTypicalFoodRem(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Item itemToDelete = model.getCurrentList().get(INDEX_FIRST_ITEM.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_ITEM);

        ModelManager expectedModel = new ModelManager(model.getFoodRem(), new UserPrefs());
        expectedModel.deleteItem(itemToDelete);

        assertCommandSuccess(deleteCommand, model,
                new ItemWithMessage(itemToDelete, EXPECTED_SUCCESS_MESSAGE), expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getCurrentList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_ITEMS_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showItemAtIndex(model, INDEX_FIRST_ITEM);

        Item itemToDelete = model.getCurrentList().get(INDEX_FIRST_ITEM.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_ITEM);

        Model expectedModel = new ModelManager(model.getFoodRem(), new UserPrefs());
        expectedModel.deleteItem(itemToDelete);
        showNoItem(expectedModel);

        assertCommandSuccess(deleteCommand, model,
                new ItemWithMessage(itemToDelete, EXPECTED_SUCCESS_MESSAGE), expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showItemAtIndex(model, INDEX_FIRST_ITEM);
        Index outOfBoundIndex = INDEX_SECOND_ITEM;
        // ensures that outOfBoundIndex is still in bounds of foodRem list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getFoodRem().getItemList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_ITEMS_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_ITEM);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_ITEM);

        // same object -> returns true
        assertEquals(deleteFirstCommand, deleteFirstCommand);
        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_ITEM);
        assertEquals(deleteFirstCommand, deleteFirstCommandCopy);
        // different types -> returns false
        assertNotEquals(1, deleteFirstCommand);
        // null -> returns false
        assertNotEquals(null, deleteFirstCommand);
        // different item -> returns false
        assertNotEquals(deleteFirstCommand, deleteSecondCommand);
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoItem(Model model) {
        model.updateFilteredItemList(p -> false);
        assertTrue(model.getCurrentList().isEmpty());
    }
}
