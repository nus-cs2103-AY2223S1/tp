package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_NOT_AT_HOMEPAGE;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBookWithOnlyPersons;
import static seedu.address.testutil.TypicalPersons.ALICE;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NameStartsWithKeywordPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindPersonCommand}.
 */
public class FindPersonCommandTest {
    private Model model = new ModelManager(getTypicalAddressBookWithOnlyPersons(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBookWithOnlyPersons(), new UserPrefs());

    @Test
    public void equals() {
        NameStartsWithKeywordPredicate firstPredicate =
                new NameStartsWithKeywordPredicate("first");
        NameStartsWithKeywordPredicate secondPredicate =
                new NameStartsWithKeywordPredicate("second");

        FindPersonCommand findPersonFirstCommand = new FindPersonCommand(firstPredicate);
        FindPersonCommand findPersonSecondCommand = new FindPersonCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findPersonFirstCommand.equals(findPersonFirstCommand));

        // same keyword -> returns true
        FindPersonCommand findPersonFirstCommandCopy = new FindPersonCommand(firstPredicate);
        assertTrue(findPersonFirstCommand.equals(findPersonFirstCommandCopy));

        // different types -> returns false
        assertFalse(findPersonFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findPersonFirstCommand.equals(null));

        // different keyword -> returns false
        assertFalse(findPersonFirstCommand.equals(findPersonSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        NameStartsWithKeywordPredicate predicate = preparePredicate(" ");
        FindPersonCommand command = new FindPersonCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_validKeywords_personFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        NameStartsWithKeywordPredicate predicate = preparePredicate("A");
        FindPersonCommand command = new FindPersonCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredPersonList());
    }

    @Test
    public void execute_notAtHomePage_throwsCommandException() {
        NameStartsWithKeywordPredicate predicate = preparePredicate("A");
        FindPersonCommand command = new FindPersonCommand(predicate);
        model.setHomeStatus(false);
        assertCommandFailure(command, model, MESSAGE_NOT_AT_HOMEPAGE);
    }

    /**
     * Parses {@code testInput} into a {@code NameStartsWithKeywordPredicate}.
     */
    private NameStartsWithKeywordPredicate preparePredicate(String testInput) {
        return new NameStartsWithKeywordPredicate(testInput);
    }
}
