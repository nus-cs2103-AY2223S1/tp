package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.PrevModContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FilterPrevModCommand}.
 */
public class FilterPrevModCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        PrevModContainsKeywordsPredicate firstPredicate =
                new PrevModContainsKeywordsPredicate("first");
        PrevModContainsKeywordsPredicate secondPredicate =
                new PrevModContainsKeywordsPredicate("second");

        FilterPrevModCommand filterPrevModFirstCommand = new FilterPrevModCommand(firstPredicate);
        FilterPrevModCommand filterPrevModSecondCommand = new FilterPrevModCommand(secondPredicate);

        // same object -> returns true
        assertTrue(filterPrevModFirstCommand.equals(filterPrevModFirstCommand));

        // same values -> returns true
        FilterPrevModCommand filterPrevModFirstCommandCopy = new FilterPrevModCommand(firstPredicate);
        assertTrue(filterPrevModFirstCommand.equals(filterPrevModFirstCommandCopy));

        // different types -> returns false
        assertFalse(filterPrevModFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterPrevModFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(filterPrevModFirstCommand.equals(filterPrevModSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PrevModContainsKeywordsPredicate predicate = preparePredicate(" ");
        FilterPrevModCommand command = new FilterPrevModCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        PrevModContainsKeywordsPredicate predicate = preparePredicate("CS2030S");
        FilterPrevModCommand command = new FilterPrevModCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code PrevModContainsKeywordsPredicate}.
     */
    private PrevModContainsKeywordsPredicate preparePredicate(String userInput) {
        return new PrevModContainsKeywordsPredicate(userInput);
    }
}
