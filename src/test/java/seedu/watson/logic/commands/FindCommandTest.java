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

        FindNameCommand findFirstCommand = new FindNameCommand(firstPredicate);
        FindNameCommand findSecondCommand = new FindNameCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindNameCommand findFirstCommandCopy = new FindNameCommand(firstPredicate);
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
        FindNameCommand command = new FindNameCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        FindCommandPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindNameCommand command = new FindNameCommand(predicate);
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
}
