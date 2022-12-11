package seedu.foodrem.logic.commands.itemcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.foodrem.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.foodrem.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.foodrem.testutil.TypicalFoodRem.getTypicalFoodRem;
import static seedu.foodrem.testutil.TypicalIndexes.INDEX_FIRST_ITEM;

import org.junit.jupiter.api.Test;

import seedu.foodrem.commons.core.Messages;
import seedu.foodrem.commons.core.index.Index;
import seedu.foodrem.model.FoodRem;
import seedu.foodrem.model.Model;
import seedu.foodrem.model.ModelManager;
import seedu.foodrem.model.UserPrefs;
import seedu.foodrem.model.item.Item;
import seedu.foodrem.model.item.ItemQuantity;
import seedu.foodrem.testutil.ItemBuilder;
import seedu.foodrem.testutil.MessageToUser;
import seedu.foodrem.viewmodels.ItemWithMessage;

/**
 * A class to test the DecrementCommand.
 */
class DecrementCommandTest {
    private static final String EXPECTED_SUCCESS_MESSAGE = "Decremented successfully and updated item as follows:";

    private final Model model = new ModelManager(getTypicalFoodRem(), new UserPrefs());

    @Test
    public void execute_success() {
        Item originalItem = model.getCurrentList().get(0);
        Item expectedItem = new ItemBuilder(originalItem).withItemQuantity("4").build();

        DecrementCommand decrementCommand = new DecrementCommand(INDEX_FIRST_ITEM,
                new ItemQuantity("6"));

        Model expectedModel = new ModelManager(new FoodRem(model.getFoodRem()), new UserPrefs());
        expectedModel.setItem(model.getCurrentList().get(0), expectedItem);

        assertCommandSuccess(decrementCommand, model,
                new ItemWithMessage(expectedItem, EXPECTED_SUCCESS_MESSAGE), expectedModel);
    }

    @Test
    public void execute_failure() {
        // Negative value after decrement
        assertCommandFailure(new DecrementCommand(INDEX_FIRST_ITEM,
                new ItemQuantity("11")), model, MessageToUser.MESSAGE_FOR_FINAL_QUANTITY_IS_NEGATIVE);

        // Index out of bounds
        assertCommandFailure(new DecrementCommand(Index.fromOneBased(100),
                new ItemQuantity("11")), model, Messages.MESSAGE_INVALID_ITEMS_DISPLAYED_INDEX);
    }

    @Test
    void testEquals() {
        DecrementCommand decrementCommandSameFirst = new DecrementCommand(Index.fromOneBased(1),
                new ItemQuantity("1"));
        DecrementCommand decrementCommandSameSecond = new DecrementCommand(Index.fromOneBased(1),
                new ItemQuantity("1"));

        DecrementCommand decrementCommandSameIndexFirst = new DecrementCommand(Index.fromOneBased(1),
                new ItemQuantity("1"));
        DecrementCommand decrementCommandSameIndexSecond = new DecrementCommand(Index.fromOneBased(1),
                new ItemQuantity("2"));

        DecrementCommand decrementCommandSameQuantityFirst = new DecrementCommand(Index.fromOneBased(1),
                new ItemQuantity("1"));
        DecrementCommand decrementCommandSameQuantitySecond = new DecrementCommand(Index.fromOneBased(2),
                new ItemQuantity("1"));

        DecrementCommand decrementCommandDifferentFirst = new DecrementCommand(Index.fromOneBased(1),
                new ItemQuantity("1"));
        DecrementCommand decrementCommandDifferentSecond = new DecrementCommand(Index.fromOneBased(2),
                new ItemQuantity("2"));

        // Exactly the same
        assertEquals(decrementCommandSameFirst, decrementCommandSameSecond);
        // Same quantity different index
        assertNotEquals(decrementCommandSameQuantityFirst, decrementCommandSameQuantitySecond);
        // Same index different quantity
        assertNotEquals(decrementCommandSameIndexFirst, decrementCommandSameIndexSecond);
        // Different index different quantity
        assertNotEquals(decrementCommandDifferentFirst, decrementCommandDifferentSecond);
    }
}
