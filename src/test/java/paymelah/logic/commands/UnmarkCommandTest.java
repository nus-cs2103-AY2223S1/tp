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
 * {@code UnmarkCommand}.
 */
public class UnmarkCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexFollowedByValidDebtOnUnfilteredList_success() {
        Person debtor = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Set<Index> debtIndexSet = new HashSet<>(List.of(INDEX_FIRST_DEBT));
        UnmarkCommand unmarkCommand = new UnmarkCommand(INDEX_FIRST_PERSON, debtIndexSet);

        Debt toUnmark = debtor.getDebts().asList().get(INDEX_FIRST_DEBT.getZeroBased());
        DebtList expectedDebtList = new DebtListBuilder(debtor.getDebts()).build().unmarkDebt(toUnmark);
        Person editedDebtor = new PersonBuilder(debtor).withDebts(expectedDebtList).build();

        String mainMessage = String.format(UnmarkCommand.MESSAGE_UNMARK_DEBT_SUCCESS, editedDebtor.getName());
        StringBuilder expectedMessage = new StringBuilder(mainMessage);
        expectedMessage.append("1. ").append(toUnmark.toString()).append("\n");

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()), editedDebtor);

        assertCommandSuccess(unmarkCommand, model, expectedMessage.toString(), expectedModel);
    }

    @Test
    public void execute_validIndexFollowedByValidDebtOnFilteredList_success() {
        showDebtors(model);

        Person debtor = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Set<Index> debtIndexSet = new HashSet<>(List.of(INDEX_FIRST_DEBT));
        UnmarkCommand unmarkCommand = new UnmarkCommand(INDEX_FIRST_PERSON, debtIndexSet);

        Debt toUnmark = debtor.getDebts().asList().get(INDEX_FIRST_DEBT.getZeroBased());
        DebtList expectedDebtList = new DebtListBuilder(debtor.getDebts()).build().unmarkDebt(toUnmark);
        Person editedDebtor = new PersonBuilder(debtor).withDebts(expectedDebtList).build();

        String mainMessage = String.format(UnmarkCommand.MESSAGE_UNMARK_DEBT_SUCCESS, editedDebtor.getName());
        StringBuilder expectedMessage = new StringBuilder(mainMessage);
        expectedMessage.append("1. ").append(toUnmark.toString()).append("\n");

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        showDebtors(expectedModel);
        expectedModel.setPerson(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()), editedDebtor);

        assertCommandSuccess(unmarkCommand, model, expectedMessage.toString(), expectedModel);
    }

    @Test
    public void execute_validIndexFollowedByValidDebtsOnUnfilteredList_success() {
        Person debtor = model.getFilteredPersonList().get(INDEX_SEVENTH_PERSON.getZeroBased());
        Set<Index> debtIndexSet = new HashSet<>(List.of(INDEX_SECOND_DEBT, INDEX_FIRST_DEBT));
        UnmarkCommand unmarkCommand = new UnmarkCommand(INDEX_SEVENTH_PERSON, debtIndexSet);

        Debt toUnmarkOne = debtor.getDebts().asList().get(INDEX_FIRST_DEBT.getZeroBased());
        Debt toUnmarkTwo = debtor.getDebts().asList().get(INDEX_SECOND_DEBT.getZeroBased());
        DebtList expectedDebtList = new DebtListBuilder(debtor.getDebts()).build()
                .unmarkDebt(toUnmarkOne).unmarkDebt(toUnmarkTwo);
        Person editedDebtor = new PersonBuilder(debtor).withDebts(expectedDebtList).build();

        String mainMessage = String.format(UnmarkCommand.MESSAGE_UNMARK_DEBT_SUCCESS, editedDebtor.getName());
        StringBuilder expectedMessage = new StringBuilder(mainMessage);
        expectedMessage.append("1. ").append(toUnmarkOne.toString()).append("\n");
        expectedMessage.append("2. ").append(toUnmarkTwo.toString()).append("\n");

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(INDEX_SEVENTH_PERSON.getZeroBased()), editedDebtor);

        assertCommandSuccess(unmarkCommand, model, expectedMessage.toString(), expectedModel);
    }

    @Test
    public void execute_validIndexFollowedByValidDebtsOnFilteredList_success() {
        showDebtors(model);

        Person debtor = model.getFilteredPersonList().get(INDEX_FIFTH_PERSON.getZeroBased());
        Set<Index> debtIndexSet = new HashSet<>(List.of(INDEX_SECOND_DEBT, INDEX_FIRST_DEBT));
        UnmarkCommand unmarkCommand = new UnmarkCommand(INDEX_FIFTH_PERSON, debtIndexSet);

        Debt toUnmarkOne = debtor.getDebts().asList().get(INDEX_FIRST_DEBT.getZeroBased());
        Debt toUnmarkTwo = debtor.getDebts().asList().get(INDEX_SECOND_DEBT.getZeroBased());
        DebtList expectedDebtList = new DebtListBuilder(debtor.getDebts()).build()
                .unmarkDebt(toUnmarkOne).unmarkDebt(toUnmarkTwo);
        Person editedDebtor = new PersonBuilder(debtor).withDebts(expectedDebtList).build();

        String mainMessage = String.format(UnmarkCommand.MESSAGE_UNMARK_DEBT_SUCCESS, editedDebtor.getName());
        StringBuilder expectedMessage = new StringBuilder(mainMessage);
        expectedMessage.append("1. ").append(toUnmarkOne.toString()).append("\n");
        expectedMessage.append("2. ").append(toUnmarkTwo.toString()).append("\n");

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        showDebtors(expectedModel);
        expectedModel.setPerson(model.getFilteredPersonList().get(INDEX_FIFTH_PERSON.getZeroBased()), editedDebtor);

        assertCommandSuccess(unmarkCommand, model, expectedMessage.toString(), expectedModel);
    }

    @Test
    public void execute_invalidIndexOnUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);

        Set<Index> debtIndexSet = new HashSet<>(List.of(INDEX_FIRST_DEBT));
        UnmarkCommand unmarkCommand = new UnmarkCommand(outOfBoundIndex, debtIndexSet);
        assertCommandFailure(unmarkCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexOnFilteredList_throwsCommandException() {
        showDebtors(model);

        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        Set<Index> debtIndexSet = new HashSet<>(List.of(INDEX_FIRST_DEBT));
        UnmarkCommand unmarkCommand = new UnmarkCommand(outOfBoundIndex, debtIndexSet);
        assertCommandFailure(unmarkCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFollowedByInvalidDebtOnUnfilteredList_throwsCommandException() {
        Person debtor = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        int debtCount = debtor.getDebts().asList().size();
        Set<Index> debtIndexSet = new HashSet<>(List.of(Index.fromZeroBased(debtCount)));
        UnmarkCommand unmarkCommand = new UnmarkCommand(INDEX_FIRST_PERSON, debtIndexSet);
        assertCommandFailure(unmarkCommand, model, Messages.MESSAGE_INVALID_DEBT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFollowedByInvalidDebtOnFilteredList_throwsCommandException() {
        showDebtors(model);

        Person debtor = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        int debtCount = debtor.getDebts().asList().size();
        Set<Index> debtIndexSet = new HashSet<>(List.of(Index.fromZeroBased(debtCount)));
        UnmarkCommand unmarkCommand = new UnmarkCommand(INDEX_FIRST_PERSON, debtIndexSet);
        assertCommandFailure(unmarkCommand, model, Messages.MESSAGE_INVALID_DEBT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        List<Index> listFirst = Arrays.asList(INDEX_FIRST_DEBT, INDEX_THIRD_DEBT);
        List<Index> listSecond = Arrays.asList(INDEX_THIRD_DEBT);

        HashSet<Index> indexSetFirst = new HashSet<>(listFirst);
        HashSet<Index> indexSetSecond = new HashSet<>(listSecond);

        UnmarkCommand unmarkFirstCommand = new UnmarkCommand(INDEX_FIRST_PERSON, indexSetFirst);
        UnmarkCommand unmarkSecondCommand = new UnmarkCommand(INDEX_SECOND_PERSON, indexSetFirst);
        UnmarkCommand unmarkThirdCommand = new UnmarkCommand(INDEX_FIRST_PERSON, indexSetSecond);

        // same object -> returns true

        assertTrue(unmarkFirstCommand.equals(unmarkFirstCommand));

        // same values -> returns true
        UnmarkCommand unmarkFirstCommandCopy = new UnmarkCommand(INDEX_FIRST_PERSON, indexSetFirst);
        assertTrue(unmarkFirstCommand.equals(unmarkFirstCommandCopy));

        // different types -> returns false
        assertFalse(unmarkFirstCommand.equals(1));

        // null -> returns false
        assertFalse(unmarkFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(unmarkFirstCommand.equals(unmarkSecondCommand));

        // different set of Debts -> returns false
        assertFalse(unmarkFirstCommand.equals(unmarkThirdCommand));
    }
}
