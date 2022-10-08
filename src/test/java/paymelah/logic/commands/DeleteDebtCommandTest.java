package paymelah.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static paymelah.testutil.TypicalIndexes.INDEX_FIRST_DEBT;
import static paymelah.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static paymelah.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static paymelah.testutil.TypicalIndexes.INDEX_THIRD_DEBT;
import static paymelah.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import paymelah.commons.core.index.Index;
import paymelah.model.Model;
import paymelah.model.ModelManager;
import paymelah.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code ClearDebtsCommand}.
 */
public class DeleteDebtCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    //TODO Add more tests


    @Test
    public void equals() {
        List<Index> listFirst = Arrays.asList(INDEX_FIRST_DEBT, INDEX_THIRD_DEBT);
        List<Index> listSecond = Arrays.asList(INDEX_THIRD_DEBT);

        HashSet<Index> indexSetFirst = new HashSet<>(listFirst);
        HashSet<Index> indexSetSecond = new HashSet<>(listSecond);

        DeleteDebtCommand deleteDebtFirstCommand = new DeleteDebtCommand(INDEX_FIRST_PERSON, indexSetFirst);
        DeleteDebtCommand deleteDebtSecondCommand = new DeleteDebtCommand(INDEX_SECOND_PERSON, indexSetFirst);
        DeleteDebtCommand deleteDebtThirdCommand = new DeleteDebtCommand(INDEX_FIRST_PERSON, indexSetSecond);

        // same object -> returns true

        assertTrue(deleteDebtFirstCommand.equals(deleteDebtFirstCommand));

        // same values -> returns true
        DeleteDebtCommand deleteDebtFirstCommandCopy = new DeleteDebtCommand(INDEX_FIRST_PERSON, indexSetFirst);
        assertTrue(deleteDebtFirstCommand.equals(deleteDebtFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteDebtFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteDebtFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteDebtFirstCommand.equals(deleteDebtSecondCommand));

        // different set of Debts -> returns false
        assertFalse(deleteDebtFirstCommand.equals(deleteDebtThirdCommand));
    }

}
