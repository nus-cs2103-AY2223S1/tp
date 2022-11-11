package paymelah.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static paymelah.logic.commands.CommandTestUtil.assertCommandFailure;
import static paymelah.logic.commands.CommandTestUtil.assertCommandSuccess;
import static paymelah.logic.commands.CommandTestUtil.showDebtors;
import static paymelah.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static paymelah.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static paymelah.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import paymelah.commons.core.Messages;
import paymelah.commons.core.index.Index;
import paymelah.model.AddressBook;
import paymelah.model.Model;
import paymelah.model.ModelManager;
import paymelah.model.UserPrefs;
import paymelah.model.person.Person;
import paymelah.testutil.PersonBuilder;


/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code ClearDebtsCommand}.
 */
public class ClearDebtsCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person debtorToClear = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        ClearDebtsCommand clearDebtsCommand = new ClearDebtsCommand(INDEX_FIRST_PERSON);

        Person clearedDebtor = new PersonBuilder(debtorToClear).withDebts().build();

        String expectedMessage = String.format(ClearDebtsCommand.MESSAGE_CLEAR_DEBTS_SUCCESS,
                clearedDebtor.getName());
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), clearedDebtor);

        assertCommandSuccess(clearDebtsCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        ClearDebtsCommand clearDebtsCommand = new ClearDebtsCommand(outOfBoundIndex);

        assertCommandFailure(clearDebtsCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showDebtors(model);

        Person debtorToClear = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        ClearDebtsCommand clearDebtsCommand = new ClearDebtsCommand(INDEX_SECOND_PERSON);

        Person clearedDebtor = new PersonBuilder(debtorToClear).withDebts().build();

        String expectedMessage = String.format(ClearDebtsCommand.MESSAGE_CLEAR_DEBTS_SUCCESS,
                clearedDebtor.getName());
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList()
                .get(INDEX_SECOND_PERSON.getZeroBased()), clearedDebtor);

        assertCommandSuccess(clearDebtsCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showDebtors(model);

        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);

        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        ClearDebtsCommand clearDebtsCommand = new ClearDebtsCommand(outOfBoundIndex);

        assertCommandFailure(clearDebtsCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ClearDebtsCommand clearDebtsFirstCommand = new ClearDebtsCommand(INDEX_FIRST_PERSON);
        ClearDebtsCommand clearDebtsSecondCommand = new ClearDebtsCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(clearDebtsFirstCommand.equals(clearDebtsFirstCommand));

        // same values -> returns true
        ClearDebtsCommand clearDebtsFirstCommandCopy = new ClearDebtsCommand(INDEX_FIRST_PERSON);
        assertTrue(clearDebtsFirstCommand.equals(clearDebtsFirstCommandCopy));

        // different types -> returns false
        assertFalse(clearDebtsFirstCommand.equals(1));

        // null -> returns false
        assertFalse(clearDebtsFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(clearDebtsFirstCommand.equals(clearDebtsSecondCommand));
    }

}
