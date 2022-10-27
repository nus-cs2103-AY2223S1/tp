package seedu.pennyWise.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.pennyWise.commons.core.Messages.MESSAGE_ENTRIES_LISTED_OVERVIEW;
import static seedu.pennyWise.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.pennyWise.testutil.TypicalEntry.DINNER;
import static seedu.pennyWise.testutil.TypicalEntry.LUNCH;
import static seedu.pennyWise.testutil.TypicalEntry.MOVIE;
import static seedu.pennyWise.testutil.TypicalEntry.getTypicalPennyWise;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.pennyWise.model.Model;
import seedu.pennyWise.model.ModelManager;
import seedu.pennyWise.model.UserPrefs;
import seedu.pennyWise.model.entry.NameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalPennyWise(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalPennyWise(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different entry -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }
    @Test
    public void execute_zeroKeywords_noEntryFound() {
        String expectedMessage = String.format(MESSAGE_ENTRIES_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredExpenditureList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredExpenditureList());
    }
    @Test
    public void execute_multipleKeywords_multipleEntriesFound() {
        String expectedMessage = String.format(MESSAGE_ENTRIES_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = preparePredicate("Lunch Dinner Movie");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredExpenditureList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(LUNCH, DINNER, MOVIE), model.getFilteredExpenditureList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
