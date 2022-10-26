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
import seedu.address.model.module.PlanModContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FilterPlanModCommand}.
 */
public class FilterPlanModCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        PlanModContainsKeywordsPredicate firstPredicate =
                new PlanModContainsKeywordsPredicate("first");
        PlanModContainsKeywordsPredicate secondPredicate =
                new PlanModContainsKeywordsPredicate("second");

        FilterPlanModCommand filterPlanModFirstCommand = new FilterPlanModCommand(firstPredicate);
        FilterPlanModCommand filterPlanModSecondCommand = new FilterPlanModCommand(secondPredicate);

        // same object -> returns true
        assertTrue(filterPlanModFirstCommand.equals(filterPlanModFirstCommand));

        // same values -> returns true
        FilterPlanModCommand filterPlanModFirstCommandCopy = new FilterPlanModCommand(firstPredicate);
        assertTrue(filterPlanModFirstCommand.equals(filterPlanModFirstCommandCopy));

        // different types -> returns false
        assertFalse(filterPlanModFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterPlanModFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(filterPlanModFirstCommand.equals(filterPlanModSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PlanModContainsKeywordsPredicate predicate = preparePredicate(" ");
        FilterPlanModCommand command = new FilterPlanModCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        PlanModContainsKeywordsPredicate predicate = preparePredicate("CS2105");
        FilterPlanModCommand command = new FilterPlanModCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code PlanModContainsKeywordsPredicate}.
     */
    private PlanModContainsKeywordsPredicate preparePredicate(String userInput) {
        return new PlanModContainsKeywordsPredicate(userInput);
    }
}
