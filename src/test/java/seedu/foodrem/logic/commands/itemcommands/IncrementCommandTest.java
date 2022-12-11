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
 * A class to test the IncrementCommand.
 */
class IncrementCommandTest {
    private static final String EXPECTED_SUCCESS_MESSAGE = "Incremented successfully and updated item as follows:";

    private final Model model = new ModelManager(getTypicalFoodRem(), new UserPrefs());

    @Test
    public void execute_success() {
        Item originalItem = model.getCurrentList().get(0);
        Item expectedItem = new ItemBuilder(originalItem).withItemQuantity("20").build();

        IncrementCommand incrementCommand = new IncrementCommand(INDEX_FIRST_ITEM,
                new ItemQuantity("10"));

        Model expectedModel = new ModelManager(new FoodRem(model.getFoodRem()), new UserPrefs());
        expectedModel.setItem(model.getCurrentList().get(0), expectedItem);

        assertCommandSuccess(incrementCommand, model,
                new ItemWithMessage(expectedItem, EXPECTED_SUCCESS_MESSAGE), expectedModel);
    }

    @Test
    public void execute_failure() {
        // Value too large after increment
        assertCommandFailure(new IncrementCommand(INDEX_FIRST_ITEM,
                new ItemQuantity("999999")), model, MessageToUser.MESSAGE_FOR_FINAL_QUANTITY_TOO_LARGE);

        // Index out of bounds
        assertCommandFailure(new IncrementCommand(Index.fromOneBased(100),
                new ItemQuantity("11")), model, Messages.MESSAGE_INVALID_ITEMS_DISPLAYED_INDEX);
    }

    @Test
    void testEquals() {
        IncrementCommand incrementCommandSameFirst = new IncrementCommand(Index.fromOneBased(1),
                new ItemQuantity("1"));
        IncrementCommand incrementCommandSameSecond = new IncrementCommand(Index.fromOneBased(1),
                new ItemQuantity("1"));

        IncrementCommand incrementCommandSameIndexFirst = new IncrementCommand(Index.fromOneBased(1),
                new ItemQuantity("1"));
        IncrementCommand incrementCommandSameIndexSecond = new IncrementCommand(Index.fromOneBased(1),
                new ItemQuantity("2"));

        IncrementCommand incrementCommandSameQuantityFirst = new IncrementCommand(Index.fromOneBased(1),
                new ItemQuantity("1"));
        IncrementCommand incrementCommandSameQuantitySecond = new IncrementCommand(Index.fromOneBased(2),
                new ItemQuantity("1"));

        IncrementCommand incrementCommandDifferentFirst = new IncrementCommand(Index.fromOneBased(1),
                new ItemQuantity("1"));
        IncrementCommand incrementCommandDifferentSecond = new IncrementCommand(Index.fromOneBased(2),
                new ItemQuantity("2"));

        // Exactly the same
        assertEquals(incrementCommandSameFirst, incrementCommandSameSecond);
        // Same quantity different index
        assertNotEquals(incrementCommandSameQuantityFirst, incrementCommandSameQuantitySecond);
        // Same index different quantity
        assertNotEquals(incrementCommandSameIndexFirst, incrementCommandSameIndexSecond);
        // Different index different quantity
        assertNotEquals(incrementCommandDifferentFirst, incrementCommandDifferentSecond);
    }
}
