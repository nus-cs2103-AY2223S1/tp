package paymelah.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static paymelah.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static paymelah.logic.commands.CommandTestUtil.assertCommandSuccess;
import static paymelah.testutil.TypicalPersons.BENSON;
import static paymelah.testutil.TypicalPersons.GEORGE;
import static paymelah.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import paymelah.model.Model;
import paymelah.model.ModelManager;
import paymelah.model.UserPrefs;
import paymelah.model.person.DebtsContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindDebtCommand}.
 */
public class FindDebtCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        DebtsContainsKeywordsPredicate firstPredicate =
                new DebtsContainsKeywordsPredicate(Collections.singletonList("first"));
        DebtsContainsKeywordsPredicate secondPredicate =
                new DebtsContainsKeywordsPredicate(Collections.singletonList("second"));

        FindDebtCommand findDebtFirstCommand = new FindDebtCommand(firstPredicate);
        FindDebtCommand findDebtSecondCommand = new FindDebtCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findDebtFirstCommand.equals(findDebtFirstCommand));

        // same values -> returns true
        FindDebtCommand findDebtFirstCommandCopy = new FindDebtCommand(firstPredicate);
        assertTrue(findDebtFirstCommand.equals(findDebtFirstCommandCopy));

        // different types -> returns false
        assertFalse(findDebtFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findDebtFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findDebtFirstCommand.equals(findDebtSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        DebtsContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindDebtCommand command = new FindDebtCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        DebtsContainsKeywordsPredicate predicate = preparePredicate("burger");
        FindDebtCommand command = new FindDebtCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, GEORGE), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private DebtsContainsKeywordsPredicate preparePredicate(String userInput) {
        return new DebtsContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
