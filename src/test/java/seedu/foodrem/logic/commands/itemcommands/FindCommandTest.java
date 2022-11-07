package seedu.foodrem.logic.commands.itemcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.foodrem.commons.core.Messages.MESSAGE_ITEMS_FILTERED_OVERVIEW;
import static seedu.foodrem.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.foodrem.testutil.TypicalFoodRem.getTypicalFoodRem;
import static seedu.foodrem.testutil.TypicalItems.CUCUMBERS;
import static seedu.foodrem.testutil.TypicalItems.POTATOES;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.foodrem.model.Model;
import seedu.foodrem.model.ModelManager;
import seedu.foodrem.model.UserPrefs;
import seedu.foodrem.model.item.NameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private final Model model = new ModelManager(getTypicalFoodRem(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalFoodRem(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertEquals(findFirstCommand, findFirstCommand);

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertEquals(findFirstCommand, findFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, findFirstCommand);

        // null -> returns false
        assertNotEquals(null, findFirstCommand);

        // different item -> returns false
        assertNotEquals(findFirstCommand, findSecondCommand);
    }

    @Test
    public void execute_zeroKeywords_noItemsFound() {
        String expectedMessage = String.format(MESSAGE_ITEMS_FILTERED_OVERVIEW, 2);
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredItemList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(POTATOES, CUCUMBERS), model.getCurrentList());
    }

    @Test
    public void execute_multipleKeywords_zeroItemsFound() {
        String expectedMessage = String.format(MESSAGE_ITEMS_FILTERED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = preparePredicate("Potatoes Cucumbers Carrots");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredItemList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getCurrentList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
