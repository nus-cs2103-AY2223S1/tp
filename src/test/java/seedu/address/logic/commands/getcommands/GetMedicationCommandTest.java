package seedu.address.logic.commands.getcommands;

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

import seedu.address.logic.commands.GetCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.MedicationContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code GetMedicationCommand}.
 */
public class GetMedicationCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        MedicationContainsKeywordsPredicate firstPredicate = new MedicationContainsKeywordsPredicate(
                Collections.singletonList("paracetamol"));
        MedicationContainsKeywordsPredicate secondPredicate = new MedicationContainsKeywordsPredicate(
                Collections.singletonList("ibuprofen"));

        GetMedicationCommand getFirstMedicationCommand = new GetMedicationCommand(firstPredicate);
        GetMedicationCommand getSecondMedicationCommand = new GetMedicationCommand(secondPredicate);

        // same object -> returns true
        assertTrue(getFirstMedicationCommand.equals(getFirstMedicationCommand));

        // same values -> returns true
        GetCommand getFirstMedicationCommandCopy = new GetMedicationCommand(firstPredicate);
        assertTrue(getFirstMedicationCommand.equals(getFirstMedicationCommandCopy));

        // different types -> returns false
        assertFalse(getFirstMedicationCommand.equals(1));

        // null -> returns false
        assertFalse(getFirstMedicationCommand.equals(null));

        // different person -> returns false
        assertFalse(getFirstMedicationCommand.equals(getSecondMedicationCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        MedicationContainsKeywordsPredicate predicate = preparePredicate(" ");
        GetMedicationCommand command = new GetMedicationCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        MedicationContainsKeywordsPredicate predicate = preparePredicate("paracetamol ibuprofen");
        GetMedicationCommand command = new GetMedicationCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON), model.getFilteredPersonList());
    }

    private MedicationContainsKeywordsPredicate preparePredicate(String userInput) {
        return new MedicationContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
