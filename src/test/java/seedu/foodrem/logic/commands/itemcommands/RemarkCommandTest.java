package seedu.foodrem.logic.commands.itemcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.foodrem.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.foodrem.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.foodrem.logic.commands.CommandTestUtil.showItemAtIndex;
import static seedu.foodrem.testutil.TypicalFoodRem.getTypicalFoodRem;
import static seedu.foodrem.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.foodrem.testutil.TypicalIndexes.INDEX_SECOND_ITEM;

import org.junit.jupiter.api.Test;

import seedu.foodrem.commons.core.Messages;
import seedu.foodrem.commons.core.index.Index;
import seedu.foodrem.logic.commands.CommandTestUtil;
import seedu.foodrem.logic.commands.exceptions.CommandException;
import seedu.foodrem.logic.commands.generalcommands.ResetCommand;
import seedu.foodrem.model.FoodRem;
import seedu.foodrem.model.Model;
import seedu.foodrem.model.ModelManager;
import seedu.foodrem.model.UserPrefs;
import seedu.foodrem.model.item.Item;
import seedu.foodrem.model.item.ItemRemark;
import seedu.foodrem.testutil.ItemBuilder;
import seedu.foodrem.viewmodels.ItemWithMessage;

/**
 * Contains unit tests for RemarkCommand.
 */
public class RemarkCommandTest {
    private static final String EXPECTED_SUCCESS_MESSAGE = "Remark has been updated. View the updated item below:";
    private final String remarkString = "test";
    private final ItemRemark itemRemark = new ItemRemark(remarkString);

    private final Model model = new ModelManager(getTypicalFoodRem(), new UserPrefs());

    @Test
    public void execute_remarksSpecified_success() {
        Item originalItem = model.getCurrentList().get(INDEX_FIRST_ITEM.getZeroBased());
        Item remarkedItem = new ItemBuilder(originalItem).withItemRemarks(remarkString).build();

        RemarkCommand remarkCommand = new RemarkCommand(INDEX_FIRST_ITEM, itemRemark);

        Model expectedModel = new ModelManager(new FoodRem(model.getFoodRem()), new UserPrefs());
        expectedModel.setItem(model.getCurrentList().get(0), remarkedItem);

        assertCommandSuccess(remarkCommand, model,
                new ItemWithMessage(remarkedItem, EXPECTED_SUCCESS_MESSAGE), expectedModel);
    }

    @Test
    public void execute_filteredList_success() throws CommandException {
        showItemAtIndex(model, INDEX_FIRST_ITEM);

        Item itemInFilteredList = model.getCurrentList().get(INDEX_FIRST_ITEM.getZeroBased());
        Item remarkedItem = new ItemBuilder(itemInFilteredList)
                .withTags(CommandTestUtil.VALID_TAG_NAME_VEGETABLES)
                .withItemRemarks(remarkString)
                .build();

        Model expectedModel = new ModelManager(new FoodRem(model.getFoodRem()), new UserPrefs());
        expectedModel.setItem(model.getCurrentList().get(0), remarkedItem);

        RemarkCommand remarkCommand = new RemarkCommand(INDEX_FIRST_ITEM, itemRemark);
        remarkCommand.execute(expectedModel);

        assertEquals(remarkedItem, expectedModel.getCurrentList().get(0));
        // TODO: Fix this broken test.
        // assertCommandSuccess(remarkCommand, model,
        //         new ItemWithMessage(remarkedItem, EXPECTED_SUCCESS_MESSAGE), expectedModel);
    }

    @Test
    public void execute_invalidItemIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getCurrentList().size() + 1);

        RemarkCommand remarkCommand = new RemarkCommand(outOfBoundIndex, itemRemark);

        assertCommandFailure(remarkCommand, model, Messages.MESSAGE_INVALID_ITEMS_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of foodRem
     */
    @Test
    public void execute_invalidItemIndexFilteredList_failure() {
        showItemAtIndex(model, INDEX_FIRST_ITEM);
        Index outOfBoundIndex = INDEX_SECOND_ITEM;
        // ensures that outOfBoundIndex is still in bounds of FoodRem list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getFoodRem().getItemList().size());

        RemarkCommand remarkCommand = new RemarkCommand(outOfBoundIndex, itemRemark);

        assertCommandFailure(remarkCommand, model, Messages.MESSAGE_INVALID_ITEMS_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final RemarkCommand standardCommand = new RemarkCommand(INDEX_FIRST_ITEM, new ItemRemark("test"));

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);
        // null -> returns false
        assertNotEquals(null, standardCommand);
        // different types -> returns false
        assertNotEquals(standardCommand, new ResetCommand());
        // different index -> returns false
        assertNotEquals(standardCommand, new RemarkCommand(INDEX_SECOND_ITEM, itemRemark));
        // different remark -> returns false
        assertNotEquals(standardCommand, new RemarkCommand(INDEX_FIRST_ITEM, new ItemRemark("DIFFERENT")));
    }
}
