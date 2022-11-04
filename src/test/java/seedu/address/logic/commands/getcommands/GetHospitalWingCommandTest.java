package seedu.address.logic.commands.getcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.HospitalWingContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code GetHospitalWingCommand}.
 */
public class GetHospitalWingCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        HospitalWingContainsKeywordsPredicate firstPredicate =
                new HospitalWingContainsKeywordsPredicate(Collections.singletonList("south"));
        HospitalWingContainsKeywordsPredicate secondPredicate =
                new HospitalWingContainsKeywordsPredicate(Collections.singletonList("north"));

        GetHospitalWingCommand getFirstHospitalWingCommand = new GetHospitalWingCommand(firstPredicate);
        GetHospitalWingCommand getSecondHospitalWingCommand = new GetHospitalWingCommand(secondPredicate);

        // same object -> returns true
        assertTrue(getFirstHospitalWingCommand.equals(getFirstHospitalWingCommand));

        // same values -> returns true
        GetHospitalWingCommand getFirstHospitalWingCommandCopy = new GetHospitalWingCommand(firstPredicate);
        assertTrue(getFirstHospitalWingCommand.equals(getFirstHospitalWingCommandCopy));

        // different types -> returns false
        assertFalse(getFirstHospitalWingCommand.equals(1));

        // null -> returns false
        assertFalse(getFirstHospitalWingCommand.equals(null));

        // different person -> returns false
        assertFalse(getFirstHospitalWingCommand.equals(getSecondHospitalWingCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        HospitalWingContainsKeywordsPredicate predicate = preparePredicate(" ");
        GetHospitalWingCommand command = new GetHospitalWingCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        HospitalWingContainsKeywordsPredicate predicate = preparePredicate("south");
        GetHospitalWingCommand command = new GetHospitalWingCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, DANIEL), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code HospitalWingContainsKeywordsPredicate}.
     */
    private HospitalWingContainsKeywordsPredicate preparePredicate(String userInput) {
        String[] st = userInput.split("\\s+");
        List<String> hospitalWings = new ArrayList<>();
        for (String i : st) {
            hospitalWings.add(i);
        }
        return new HospitalWingContainsKeywordsPredicate(hospitalWings);
    }
}
