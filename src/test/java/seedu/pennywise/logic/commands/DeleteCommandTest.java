package seedu.pennywise.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.pennywise.logic.commands.CommandTestUtil.VALID_TYPE_EXPENDITURE;
import static seedu.pennywise.logic.commands.CommandTestUtil.VALID_TYPE_INCOME;
import static seedu.pennywise.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.pennywise.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.pennywise.logic.commands.CommandTestUtil.showExpenditureAtIndex;
import static seedu.pennywise.logic.commands.CommandTestUtil.showIncomeAtIndex;
import static seedu.pennywise.testutil.TypicalEntry.getTypicalPennyWise;
import static seedu.pennywise.testutil.TypicalIndexes.INDEX_FIRST_ENTRY;
import static seedu.pennywise.testutil.TypicalIndexes.INDEX_SECOND_ENTRY;

import org.junit.jupiter.api.Test;

import seedu.pennywise.commons.core.Messages;
import seedu.pennywise.commons.core.index.Index;
import seedu.pennywise.model.Model;
import seedu.pennywise.model.ModelManager;
import seedu.pennywise.model.UserPrefs;
import seedu.pennywise.model.entry.Entry;
import seedu.pennywise.model.entry.EntryType;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalPennyWise(), new UserPrefs());
    private EntryType expenditureType = new EntryType(VALID_TYPE_EXPENDITURE);
    private EntryType incomeType = new EntryType(VALID_TYPE_INCOME);
    // need income version
    @Test
    public void execute_validIndexUnfilteredExpenditureList_success() {
        Entry expenditureToDelete = model.getFilteredExpenditureList().get(INDEX_FIRST_ENTRY.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_ENTRY, this.expenditureType);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_ENTRY_SUCCESS, expenditureToDelete);

        ModelManager expectedModel = new ModelManager(model.getPennyWise(), new UserPrefs());
        expectedModel.deleteExpenditure(expenditureToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredExpenditureList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredExpenditureList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex, this.expenditureType);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredExpenditureList_success() {
        showExpenditureAtIndex(model, INDEX_FIRST_ENTRY);

        Entry expenditureToDelete = model.getFilteredExpenditureList().get(INDEX_FIRST_ENTRY.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_ENTRY, this.expenditureType);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_ENTRY_SUCCESS, expenditureToDelete);

        Model expectedModel = new ModelManager(model.getPennyWise(), new UserPrefs());
        expectedModel.deleteExpenditure(expenditureToDelete);
        showNoExpenditure(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredExpenditureList_throwsCommandException() {
        showExpenditureAtIndex(model, INDEX_FIRST_ENTRY);

        Index outOfBoundIndex = INDEX_SECOND_ENTRY;
        // ensures that outOfBoundIndex is still in bounds of expenditure list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getPennyWise().getExpenditureList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex, this.expenditureType);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexUnfilteredIncomeList_success() {
        Entry incomeToDelete = model.getFilteredIncomeList().get(INDEX_FIRST_ENTRY.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_ENTRY, this.incomeType);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_ENTRY_SUCCESS, incomeToDelete);

        ModelManager expectedModel = new ModelManager(model.getPennyWise(), new UserPrefs());
        expectedModel.deleteIncome(incomeToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredIncomeList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredIncomeList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex, this.incomeType);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredIncomeList_success() {
        showIncomeAtIndex(model, INDEX_FIRST_ENTRY);

        Entry incomeToDelete = model.getFilteredIncomeList().get(INDEX_FIRST_ENTRY.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_ENTRY, this.incomeType);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_ENTRY_SUCCESS, incomeToDelete);

        Model expectedModel = new ModelManager(model.getPennyWise(), new UserPrefs());
        expectedModel.deleteIncome(incomeToDelete);
        showNoIncome(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredIncomeList_throwsCommandException() {
        showIncomeAtIndex(model, INDEX_FIRST_ENTRY);

        Index outOfBoundIndex = INDEX_SECOND_ENTRY;
        // ensures that outOfBoundIndex is still in bounds of income list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getPennyWise().getIncomeList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex, this.incomeType);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_ENTRY, expenditureType);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_ENTRY, expenditureType);

        // same object -> returns true
        assertEquals(deleteFirstCommand, deleteFirstCommand);

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_ENTRY, expenditureType);

        assertEquals(deleteFirstCommand, deleteFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, deleteFirstCommand);

        // null -> returns false
        assertNotEquals(null, deleteFirstCommand);

        // different Entry -> returns false
        assertNotEquals(deleteFirstCommand, deleteSecondCommand);
    }

    /**
     * Updates {@code model}'s filtered list to show no expenditure.
     */
    private void showNoExpenditure(Model model) {
        model.updateFilteredExpenditureList(e -> false);

        assertTrue(model.getFilteredExpenditureList().isEmpty());
    }

    /**
     * Updates {@code model}'s filtered list to show no income.
     */
    private void showNoIncome(Model model) {
        model.updateFilteredIncomeList(e -> false);

        assertTrue(model.getFilteredIncomeList().isEmpty());
    }
}
