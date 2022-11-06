package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_TEAMMATES_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskPanel;
import static seedu.address.testutil.TypicalTeammates.CARL;
import static seedu.address.testutil.TypicalTeammates.ELLE;
import static seedu.address.testutil.TypicalTeammates.FIONA;
import static seedu.address.testutil.TypicalTeammates.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.teammate.ContainsTagPredicate;
import seedu.address.model.teammate.NameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskPanel(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), getTypicalTaskPanel(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));
        ContainsTagPredicate emptyTagsPredicate = new ContainsTagPredicate(new ArrayList<>());

        FindCommand findFirstCommand = new FindCommand(firstPredicate, emptyTagsPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate, emptyTagsPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate, emptyTagsPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different teammate -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noTeammateFound() {
        String expectedMessage = String.format(MESSAGE_TEAMMATES_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        ContainsTagPredicate emptyTagsPredicate = new ContainsTagPredicate(new ArrayList<>());
        FindCommand command = new FindCommand(predicate, emptyTagsPredicate);
        expectedModel.updateFilteredTeammateList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTeammateList());
    }

    @Test
    public void execute_multipleKeywords_multipleTeammatesFound() {
        String expectedMessage = String.format(MESSAGE_TEAMMATES_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        ContainsTagPredicate emptyTagsPredicate = new ContainsTagPredicate(new ArrayList<>());
        FindCommand command = new FindCommand(predicate, emptyTagsPredicate);
        expectedModel.updateFilteredTeammateList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredTeammateList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
