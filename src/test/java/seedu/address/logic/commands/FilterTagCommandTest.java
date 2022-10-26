package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.TagsContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FilterTagCommand}.
 */
public class FilterTagCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        TagsContainsKeywordsPredicate firstPredicate =
                new TagsContainsKeywordsPredicate("first");
        TagsContainsKeywordsPredicate secondPredicate =
                new TagsContainsKeywordsPredicate("second");

        FilterTagCommand filterTagFirstCommand = new FilterTagCommand(firstPredicate);
        FilterTagCommand filterTagSecondCommand = new FilterTagCommand(secondPredicate);

        // same object -> returns true
        assertTrue(filterTagFirstCommand.equals(filterTagFirstCommand));

        // same values -> returns true
        FilterTagCommand filterTagFirstCommandCopy = new FilterTagCommand(firstPredicate);
        assertTrue(filterTagFirstCommand.equals(filterTagFirstCommandCopy));

        // different types -> returns false
        assertFalse(filterTagFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterTagFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(filterTagFirstCommand.equals(filterTagSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        TagsContainsKeywordsPredicate predicate = preparePredicate(" ");
        FilterTagCommand command = new FilterTagCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        TagsContainsKeywordsPredicate predicate = preparePredicate("friends");
        FilterTagCommand command = new FilterTagCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, DANIEL), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code TagsContainsKeywordsPredicate}.
     */
    private TagsContainsKeywordsPredicate preparePredicate(String userInput) {
        return new TagsContainsKeywordsPredicate(userInput);
    }
}
