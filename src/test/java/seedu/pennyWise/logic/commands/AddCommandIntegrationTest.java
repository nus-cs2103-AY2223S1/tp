package seedu.pennyWise.logic.commands;

import static seedu.pennyWise.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.pennyWise.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.pennyWise.testutil.TypicalEntry.getTypicalPennyWise;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.pennyWise.model.Model;
import seedu.pennyWise.model.ModelManager;
import seedu.pennyWise.model.UserPrefs;
import seedu.pennyWise.model.entry.Entry;
import seedu.pennyWise.model.entry.EntryType;
import seedu.pennyWise.testutil.ExpenditureBuilder;
import seedu.pennyWise.testutil.IncomeBuilder;

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
