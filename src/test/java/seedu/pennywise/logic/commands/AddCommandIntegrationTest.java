package seedu.pennywise.logic.commands;

import static seedu.pennywise.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.pennywise.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.pennywise.testutil.TypicalEntry.getTypicalPennyWise;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.pennywise.model.Model;
import seedu.pennywise.model.ModelManager;
import seedu.pennywise.model.UserPrefs;
import seedu.pennywise.model.entry.Entry;
import seedu.pennywise.model.entry.EntryType;
import seedu.pennywise.testutil.ExpenditureBuilder;
import seedu.pennywise.testutil.IncomeBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalPennyWise(), new UserPrefs());
    }

    @Test
    public void execute_newExpenditure_success() {
        Entry validExpenditure = new ExpenditureBuilder().build();
        Model expectedModel = new ModelManager(model.getPennyWise(), new UserPrefs());
        expectedModel.addExpenditure(validExpenditure);
        assertCommandSuccess(
                new AddCommand(validExpenditure, new EntryType(EntryType.ENTRY_TYPE_EXPENDITURE)),
                model,
                String.format(AddCommand.MESSAGE_SUCCESS, validExpenditure),
                expectedModel);
    }

    @Test
    public void execute_duplicateExpenditure_throwsCommandException() {
        Entry expenditureInList = model.getPennyWise().getExpenditureList().get(0);
        assertCommandFailure(
                new AddCommand(expenditureInList, new EntryType(EntryType.ENTRY_TYPE_EXPENDITURE)),
                model,
                AddCommand.MESSAGE_DUPLICATE_ENTRY);
    }

    @Test
    public void execute_newIncome_success() {
        Entry validIncome = new IncomeBuilder().build();
        Model expectedModel = new ModelManager(model.getPennyWise(), new UserPrefs());
        expectedModel.addIncome(validIncome);
        assertCommandSuccess(
                new AddCommand(validIncome, new EntryType(EntryType.ENTRY_TYPE_INCOME)),
                model,
                String.format(AddCommand.MESSAGE_SUCCESS, validIncome),
                expectedModel);
    }

    @Test
    public void execute_duplicateIncome_throwsCommandException() {
        Entry incomeInList = model.getPennyWise().getIncomeList().get(0);
        assertCommandFailure(
                new AddCommand(incomeInList, new EntryType(EntryType.ENTRY_TYPE_INCOME)),
                model,
                AddCommand.MESSAGE_DUPLICATE_ENTRY);
    }

}
