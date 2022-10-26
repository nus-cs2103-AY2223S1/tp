package tracko.logic.commands.item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tracko.commons.core.Messages.MESSAGE_ITEMS_FOUND_OVERVIEW;
import static tracko.testutil.TypicalItems.INVENTORY_ITEM_1;
import static tracko.testutil.TypicalItems.getTrackOWithTypicalItems;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import tracko.logic.commands.CommandTestUtil;
import tracko.model.Model;
import tracko.model.ModelManager;
import tracko.model.UserPrefs;
import tracko.model.item.ItemContainsKeywordsPredicate;

public class FindItemCommandTest {

    private Model model = new ModelManager(getTrackOWithTypicalItems(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTrackOWithTypicalItems(), new UserPrefs());

    @Test
    public void equals() {
        ItemContainsKeywordsPredicate firstPredicate =
                new ItemContainsKeywordsPredicate(Collections.singletonList("first"));
        ItemContainsKeywordsPredicate secondPredicate =
                new ItemContainsKeywordsPredicate(Collections.singletonList("second"));

        FindItemCommand findFirstCommand = new FindItemCommand(firstPredicate);
        FindItemCommand findSecondCommand = new FindItemCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindItemCommand findFirstCommandCopy = new FindItemCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_ITEMS_FOUND_OVERVIEW, 0);
        ItemContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindItemCommand command = new FindItemCommand(predicate);
        expectedModel.updateFilteredItemList(predicate);
        CommandTestUtil.assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredItemList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_ITEMS_FOUND_OVERVIEW, 1);
        ItemContainsKeywordsPredicate predicate = preparePredicate("Sofa");
        FindItemCommand command = new FindItemCommand(predicate);
        expectedModel.updateFilteredItemList(predicate);
        CommandTestUtil.assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(INVENTORY_ITEM_1), model.getFilteredItemList());
    }

    /**
     * Parses {@code userInput} into a {@code ItemContainsKeywordsPredicate}.
     */
    private ItemContainsKeywordsPredicate preparePredicate(String userInput) {
        return new ItemContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
