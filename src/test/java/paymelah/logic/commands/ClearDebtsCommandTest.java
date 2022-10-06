package paymelah.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static paymelah.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static paymelah.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static paymelah.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import paymelah.model.Model;
import paymelah.model.ModelManager;
import paymelah.model.UserPrefs;


/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class ClearDebtsCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    //TODO Add more tests

    @Test
    public void equals() {
        ClearDebtsCommand clearDebtsFirstCommand = new ClearDebtsCommand(INDEX_FIRST_PERSON);
        ClearDebtsCommand clearDebtsSecondCommand = new ClearDebtsCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(clearDebtsFirstCommand.equals(clearDebtsFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_PERSON);
        assertTrue(clearDebtsFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(clearDebtsFirstCommand.equals(1));

        // null -> returns false
        assertFalse(clearDebtsFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(clearDebtsFirstCommand.equals(clearDebtsSecondCommand));
    }

}
