package seedu.uninurse.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.uninurse.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.uninurse.testutil.TypicalPersons.CARL;
import static seedu.uninurse.testutil.TypicalPersons.ELLE;
import static seedu.uninurse.testutil.TypicalPersons.FIONA;
import static seedu.uninurse.testutil.TypicalPersons.getTypicalUninurseBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.uninurse.model.Model;
import seedu.uninurse.model.ModelManager;
import seedu.uninurse.model.UserPrefs;
import seedu.uninurse.model.person.PatientMatchPredicate;

/**
 * Contains integration tests (interaction with the Model) for FindCommand.
 */
public class FindCommandTest {
    private final Model model = new ModelManager(getTypicalUninurseBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalUninurseBook(), new UserPrefs());

    @Test
    public void equals() {
        PatientMatchPredicate firstPredicate =
                new PatientMatchPredicate(Collections.singletonList("first"));
        PatientMatchPredicate secondPredicate =
                new PatientMatchPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertEquals(findFirstCommand, findFirstCommand);

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertEquals(findFirstCommand, findFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, findFirstCommand);

        // null -> returns false
        assertNotEquals(null, findFirstCommand);

        // different person -> returns false
        assertNotEquals(findFirstCommand, findSecondCommand);
    }

    @Test
    public void execute_zeroKeywords_allPersonFound() {
        String expectedMessage = String.format(FindCommand.MESSAGE_SUCCESS, model.getFilteredPersonList().size());
        PatientMatchPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, FindCommand.COMMAND_TYPE, expectedModel);
        assertNotEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(FindCommand.MESSAGE_SUCCESS, 3);
        PatientMatchPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, FindCommand.COMMAND_TYPE, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private PatientMatchPredicate preparePredicate(String userInput) {
        return new PatientMatchPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
