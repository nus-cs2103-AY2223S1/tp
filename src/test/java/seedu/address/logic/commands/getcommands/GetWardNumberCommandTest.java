package seedu.address.logic.commands.getcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.WardNumber;
import seedu.address.model.person.WardNumberPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code GetWardNumberCommand}.
 */
public class GetWardNumberCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        WardNumberPredicate firstPredicate =
                new WardNumberPredicate(Collections.singletonList("F312"));
        WardNumberPredicate secondPredicate =
                new WardNumberPredicate(Collections.singletonList("D102"));

        GetWardNumberCommand getFirstWardNumberCommand = new GetWardNumberCommand(firstPredicate);
        GetWardNumberCommand getSecondWardNumberCommand = new GetWardNumberCommand(secondPredicate);

        // same object -> returns true
        assertTrue(getFirstWardNumberCommand.equals(getFirstWardNumberCommand));

        // same values -> returns true
        GetWardNumberCommand getFirstWardNumberCommandCopy = new GetWardNumberCommand(firstPredicate);
        assertTrue(getFirstWardNumberCommand.equals(getFirstWardNumberCommandCopy));

        // different types -> returns false
        assertFalse(getFirstWardNumberCommand.equals(1));

        // different person -> returns false
        assertFalse(getFirstWardNumberCommand.equals(getSecondWardNumberCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        WardNumberPredicate predicate = preparePredicate(" ");
        GetWardNumberCommand command = new GetWardNumberCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        WardNumberPredicate predicate = preparePredicate("F528 B690");
        GetWardNumberCommand command = new GetWardNumberCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, FIONA), model.getFilteredPersonList());
    }

    @Test
    public void execute_noneMatching_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        WardNumberPredicate predicate = preparePredicate("Z999 P986");
        GetWardNumberCommand command = new GetWardNumberCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code WardNumberContainsKeywordsPredicate}.
     */
    private WardNumberPredicate preparePredicate(String userInput) {
        String[] st = userInput.split("\\s+");
        List<String> wardNumbers = new ArrayList<>();
        for (String i : st) {
            wardNumbers.add(i);
        }
        return new WardNumberPredicate(wardNumbers);
    }
}
