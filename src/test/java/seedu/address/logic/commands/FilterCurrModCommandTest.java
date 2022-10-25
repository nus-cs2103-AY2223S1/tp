package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.CurrModContainsKeywordsPredicate;
import seedu.address.model.tag.TagsContainsKeywordsPredicate;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.*;

/**
 * Contains integration tests (interaction with the Model) for {@code FilterCurrModCommand}.
 */
public class FilterCurrModCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        CurrModContainsKeywordsPredicate firstPredicate =
                new CurrModContainsKeywordsPredicate("first");
        CurrModContainsKeywordsPredicate secondPredicate =
                new CurrModContainsKeywordsPredicate("second");

        FilterCurrModCommand filterCurrModFirstCommand = new FilterCurrModCommand(firstPredicate);
        FilterCurrModCommand filterCurrModSecondCommand = new FilterCurrModCommand(secondPredicate);

        // same object -> returns true
        assertTrue(filterCurrModFirstCommand.equals(filterCurrModFirstCommand));

        // same values -> returns true
        FilterCurrModCommand filterCurrModFirstCommandCopy = new FilterCurrModCommand(firstPredicate);
        assertTrue(filterCurrModFirstCommand.equals(filterCurrModFirstCommandCopy));

        // different types -> returns false
        assertFalse(filterCurrModFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterCurrModFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(filterCurrModFirstCommand.equals(filterCurrModSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        CurrModContainsKeywordsPredicate predicate = preparePredicate(" ");
        FilterCurrModCommand command = new FilterCurrModCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        CurrModContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FilterCurrModCommand command = new FilterCurrModCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code CurrModContainsKeywordsPredicate}.
     */
    private CurrModContainsKeywordsPredicate preparePredicate(String userInput) {
        return new CurrModContainsKeywordsPredicate(userInput);
    }
}
