package seedu.address.logic.commands;

//import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
//import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEntry.getTypicalPennyWise;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
//import seedu.address.model.entry.EntryType;
//import seedu.address.model.entry.Expenditure;

public class SummaryCommandTest {
    private Model model = new ModelManager(getTypicalPennyWise(), new UserPrefs());
    @Test
    public void execute_noSpecifiedDate_summarySuccessful() {
        SummaryCommand summaryCommand = new SummaryCommand();
        Double totalExpenditure = model.getFilteredExpenditureList()
                .stream()
                .mapToDouble(entry -> Double.parseDouble(entry.getAmount().amount))
                .sum();
        Double totalIncome = model.getFilteredIncomeList()
                .stream()
                .mapToDouble(entry -> Double.parseDouble(entry.getAmount().amount))
                .sum();
        String expectedMessage = String.format(
                SummaryCommand.MESSAGE_SUCCESS,
                totalExpenditure,
                totalIncome,
                totalIncome - totalExpenditure);
        assertCommandSuccess(summaryCommand, model, expectedMessage, model);
    }
}
