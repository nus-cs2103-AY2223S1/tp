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

/**
 * A class to test the ViewCommand.
 */
class ViewCommandTest {
    private final Model model = new ModelManager(getTypicalFoodRem(), new UserPrefs());

    @Test
    void execute_success() {
        Item expectedItem = model.getCurrentList().get(0);
        ViewCommand viewCommand = new ViewCommand(INDEX_FIRST_ITEM);
        Model expectedModel = new ModelManager(new FoodRem(model.getFoodRem()), new UserPrefs());
        assertCommandSuccess(viewCommand, model, expectedItem, expectedModel);
    }

    @Test
    public void execute_failure() {
        assertCommandFailure(new ViewCommand(Index.fromOneBased(100)),
                model, Messages.MESSAGE_INVALID_ITEMS_DISPLAYED_INDEX);
    }

    @Test
    void testEquals() {
        ViewCommand incrementCommandSameFirst = new ViewCommand(Index.fromOneBased(1));
        ViewCommand incrementCommandSameSecond = new ViewCommand(Index.fromOneBased(1));

        ViewCommand incrementCommandDifferentIndexFirst = new ViewCommand(Index.fromOneBased(1));
        ViewCommand incrementCommandDifferentIndexSecond = new ViewCommand(Index.fromOneBased(2));

        // Exactly the same
        assertEquals(incrementCommandSameFirst, incrementCommandSameSecond);
        // Different index
        assertNotEquals(incrementCommandDifferentIndexFirst, incrementCommandDifferentIndexSecond);
    }
}
