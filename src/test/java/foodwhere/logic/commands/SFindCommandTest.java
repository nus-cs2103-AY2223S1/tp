package foodwhere.logic.commands;

import static foodwhere.logic.commands.CommandTestUtil.assertCommandSuccess;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import foodwhere.commons.core.Messages;
import foodwhere.model.Model;
import foodwhere.model.ModelManager;
import foodwhere.model.UserPrefs;
import foodwhere.model.stall.NameContainsKeywordsPredicate;
import foodwhere.testutil.TypicalStalls;

/**
 * Contains integration tests (interaction with the Model) for {@code SFindCommand}.
 */
public class SFindCommandTest {
    private Model model = new ModelManager(TypicalStalls.getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalStalls.getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        SFindCommand findFirstCommand = new SFindCommand(firstPredicate);
        SFindCommand findSecondCommand = new SFindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        SFindCommand findFirstCommandCopy = new SFindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different stall -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noStallFound() {
        String expectedMessage = String.format(Messages.MESSAGE_STALLS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        SFindCommand command = new SFindCommand(predicate);
        expectedModel.updateFilteredStallList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStallList());
    }

    @Test
    public void execute_multipleKeywords_multipleStallsFound() {
        String expectedMessage = String.format(Messages.MESSAGE_STALLS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        SFindCommand command = new SFindCommand(predicate);
        expectedModel.updateFilteredStallList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalStalls.CARL,
                TypicalStalls.ELLE,
                TypicalStalls.FIONA),
                model.getFilteredStallList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
