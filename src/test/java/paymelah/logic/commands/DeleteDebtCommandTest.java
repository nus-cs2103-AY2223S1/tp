package paymelah.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static paymelah.logic.commands.CommandTestUtil.assertCommandFailure;
import static paymelah.logic.commands.CommandTestUtil.assertCommandSuccess;
import static paymelah.logic.commands.CommandTestUtil.showDebtors;
import static paymelah.testutil.TypicalIndexes.INDEX_FIFTH_PERSON;
import static paymelah.testutil.TypicalIndexes.INDEX_FIRST_DEBT;
import static paymelah.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static paymelah.testutil.TypicalIndexes.INDEX_SECOND_DEBT;
import static paymelah.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static paymelah.testutil.TypicalIndexes.INDEX_SEVENTH_PERSON;
import static paymelah.testutil.TypicalIndexes.INDEX_THIRD_DEBT;
import static paymelah.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import paymelah.commons.core.Messages;
import paymelah.commons.core.index.Index;
import paymelah.model.AddressBook;
import paymelah.model.Model;
import paymelah.model.ModelManager;
import paymelah.model.UserPrefs;
import paymelah.model.debt.Debt;
import paymelah.model.debt.DebtList;
import paymelah.model.person.Person;
import paymelah.testutil.DebtListBuilder;
import paymelah.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteDebtCommand}.
 */
public class DeleteDebtCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexFollowedByValidDebtOnUnfilteredList_success() {
        Person debtor = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Set<Index> debtIndexSet = new HashSet<>(List.of(INDEX_FIRST_DEBT));
        DeleteDebtCommand deleteDebtCommand = new DeleteDebtCommand(INDEX_FIRST_PERSON, debtIndexSet);

        Debt toDelete = debtor.getDebts().asList().get(INDEX_FIRST_DEBT.getZeroBased());
        DebtList expectedDebtList = new DebtListBuilder(debtor.getDebts()).build().removeDebt(toDelete);
        Person editedDebtor = new PersonBuilder(debtor).withDebts(expectedDebtList).build();

        String mainMessage = String.format(DeleteDebtCommand.MESSAGE_DELETE_DEBT_SUCCESS, editedDebtor.getName());
        StringBuilder expectedMessage = new StringBuilder(mainMessage);
        expectedMessage.append("1. ").append(toDelete.toString()).append("\n");

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()), editedDebtor);

        assertCommandSuccess(deleteDebtCommand, model, expectedMessage.toString(), expectedModel);
    }

    @Test
    public void execute_validIndexFollowedByValidDebtOnFilteredList_success() {
        showDebtors(model);

        Person debtor = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Set<Index> debtIndexSet = new HashSet<>(List.of(INDEX_FIRST_DEBT));
        DeleteDebtCommand deleteDebtCommand = new DeleteDebtCommand(INDEX_FIRST_PERSON, debtIndexSet);

        Debt toDelete = debtor.getDebts().asList().get(INDEX_FIRST_DEBT.getZeroBased());
        DebtList expectedDebtList = new DebtListBuilder(debtor.getDebts()).build().removeDebt(toDelete);
        Person editedDebtor = new PersonBuilder(debtor).withDebts(expectedDebtList).build();

        String mainMessage = String.format(DeleteDebtCommand.MESSAGE_DELETE_DEBT_SUCCESS, editedDebtor.getName());
        StringBuilder expectedMessage = new StringBuilder(mainMessage);
        expectedMessage.append("1. ").append(toDelete.toString()).append("\n");

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()), editedDebtor);

        assertCommandSuccess(deleteDebtCommand, model, expectedMessage.toString(), expectedModel);
    }

    @Test
    public void execute_validIndexFollowedByValidDebtsOnUnfilteredList_success() {
        Person debtor = model.getFilteredPersonList().get(INDEX_SEVENTH_PERSON.getZeroBased());
        Set<Index> debtIndexSet = new HashSet<>(List.of(INDEX_SECOND_DEBT, INDEX_FIRST_DEBT));
        DeleteDebtCommand deleteDebtCommand = new DeleteDebtCommand(INDEX_SEVENTH_PERSON, debtIndexSet);

        Debt toDeleteOne = debtor.getDebts().asList().get(INDEX_FIRST_DEBT.getZeroBased());
        Debt toDeleteTwo = debtor.getDebts().asList().get(INDEX_SECOND_DEBT.getZeroBased());
        DebtList expectedDebtList = new DebtListBuilder(debtor.getDebts()).build()
                .removeDebt(toDeleteOne).removeDebt(toDeleteTwo);
        Person editedDebtor = new PersonBuilder(debtor).withDebts(expectedDebtList).build();

        String mainMessage = String.format(DeleteDebtCommand.MESSAGE_DELETE_DEBT_SUCCESS, editedDebtor.getName());
        StringBuilder expectedMessage = new StringBuilder(mainMessage);
        expectedMessage.append("1. ").append(toDeleteOne.toString()).append("\n");
        expectedMessage.append("2. ").append(toDeleteTwo.toString()).append("\n");

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(INDEX_SEVENTH_PERSON.getZeroBased()), editedDebtor);

        assertCommandSuccess(deleteDebtCommand, model, expectedMessage.toString(), expectedModel);
    }

    @Test
    public void execute_validIndexFollowedByValidDebtsOnFilteredList_success() {
        showDebtors(model);

        Person debtor = model.getFilteredPersonList().get(INDEX_FIFTH_PERSON.getZeroBased());
        Set<Index> debtIndexSet = new HashSet<>(List.of(INDEX_SECOND_DEBT, INDEX_FIRST_DEBT));
        DeleteDebtCommand deleteDebtCommand = new DeleteDebtCommand(INDEX_FIFTH_PERSON, debtIndexSet);

        Debt toDeleteOne = debtor.getDebts().asList().get(INDEX_FIRST_DEBT.getZeroBased());
        Debt toDeleteTwo = debtor.getDebts().asList().get(INDEX_SECOND_DEBT.getZeroBased());
        DebtList expectedDebtList = new DebtListBuilder(debtor.getDebts()).build()
                .removeDebt(toDeleteOne).removeDebt(toDeleteTwo);
        Person editedDebtor = new PersonBuilder(debtor).withDebts(expectedDebtList).build();

        String mainMessage = String.format(DeleteDebtCommand.MESSAGE_DELETE_DEBT_SUCCESS, editedDebtor.getName());
        StringBuilder expectedMessage = new StringBuilder(mainMessage);
        expectedMessage.append("1. ").append(toDeleteOne.toString()).append("\n");
        expectedMessage.append("2. ").append(toDeleteTwo.toString()).append("\n");

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(INDEX_FIFTH_PERSON.getZeroBased()), editedDebtor);

        assertCommandSuccess(deleteDebtCommand, model, expectedMessage.toString(), expectedModel);
    }

    @Test
    public void execute_invalidIndexOnUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);

        Set<Index> debtIndexSet = new HashSet<>(List.of(INDEX_FIRST_DEBT));
        DeleteDebtCommand deleteDebtCommand = new DeleteDebtCommand(outOfBoundIndex, debtIndexSet);
        assertCommandFailure(deleteDebtCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexOnFilteredList_throwsCommandException() {
        showDebtors(model);

        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        Set<Index> debtIndexSet = new HashSet<>(List.of(INDEX_FIRST_DEBT));
        DeleteDebtCommand deleteDebtCommand = new DeleteDebtCommand(outOfBoundIndex, debtIndexSet);
        assertCommandFailure(deleteDebtCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFollowedByInvalidDebtOnUnfilteredList_throwsCommandException() {
        Person debtor = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        int debtCount = debtor.getDebts().asList().size();
        Set<Index> debtIndexSet = new HashSet<>(List.of(Index.fromZeroBased(debtCount)));
        DeleteDebtCommand deleteDebtCommand = new DeleteDebtCommand(INDEX_FIRST_PERSON, debtIndexSet);
        assertCommandFailure(deleteDebtCommand, model, Messages.MESSAGE_INVALID_DEBT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFollowedByInvalidDebtOnFilteredList_throwsCommandException() {
        showDebtors(model);

        Person debtor = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        int debtCount = debtor.getDebts().asList().size();
        Set<Index> debtIndexSet = new HashSet<>(List.of(Index.fromZeroBased(debtCount)));
        DeleteDebtCommand deleteDebtCommand = new DeleteDebtCommand(INDEX_FIRST_PERSON, debtIndexSet);
        assertCommandFailure(deleteDebtCommand, model, Messages.MESSAGE_INVALID_DEBT_DISPLAYED_INDEX);
    }

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
