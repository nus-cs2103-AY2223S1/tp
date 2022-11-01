package seedu.watson.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.watson.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.watson.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.watson.testutil.TypicalStudents.CARL;
import static seedu.watson.testutil.TypicalStudents.ELLE;
import static seedu.watson.testutil.TypicalStudents.FIONA;
import static seedu.watson.testutil.TypicalStudents.getTypicalDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.watson.model.Model;
import seedu.watson.model.ModelManager;
import seedu.watson.model.UserPrefs;
import seedu.watson.model.student.FindCommandPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalDatabase(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalDatabase(), new UserPrefs());

    @Test
    public void equals() {
        FindCommandPredicate firstPredicate =
            new FindCommandPredicate(Collections.singletonList("first"));
        FindCommandPredicate secondPredicate =
            new FindCommandPredicate(Collections.singletonList("second"));

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

        // different student -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        FindCommandPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        FindCommandPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code FindCommandPredicate}.
     */
    private FindCommandPredicate preparePredicate(String userInput) {
        return new FindCommandPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Creates a new {@code FindCommandPredicate} using an ArrayList containing user input.
     * ArrayList contains 3 categories obtained from user input: names,  student classes and subjects.
     *
     * @param userInputs ArrayList containing user input.
     * @return A new FindCommandPredicate.
     */
    private FindCommandPredicate preparePredicateUsingList(ArrayList<String> userInputs) {
        return new FindCommandPredicate(userInputs);
    }
}
