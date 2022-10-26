package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEntry.getTypicalPennyWise;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.entry.Date;
import seedu.address.model.entry.EntryInYearMonthPredicate;

public class SummaryCommandTest {
    private Model model = new ModelManager(getTypicalPennyWise(), new UserPrefs());

    @Test
    public void execute_noSpecifiedDate_summarySuccessful() {
        SummaryCommand summaryCommand = new SummaryCommand();
        Double totalExpenditure = model.getFilteredExpenditureList()
                .stream()
                .mapToDouble(entry -> Double.parseDouble(entry.getAmount().toString()))
                .sum();
        Double totalIncome = model.getFilteredIncomeList()
                .stream()
                .mapToDouble(entry -> Double.parseDouble(entry.getAmount().toString()))
                .sum();
        String expectedMessage = String.format(
                SummaryCommand.MESSAGE_SUCCESS,
                totalExpenditure,
                totalIncome,
                totalIncome - totalExpenditure);
        assertCommandSuccess(summaryCommand, model, expectedMessage, model);
    }

    @Test
    public void execute_specifiedDate_summarySuccessful() {
        EntryInYearMonthPredicate predicate = new EntryInYearMonthPredicate(YearMonth.parse(
                CommandTestUtil.VALID_MONTH_APRIL,
                DateTimeFormatter.ofPattern(Date.YEAR_MONTH_PATTERN)));
        SummaryCommand summaryCommand = new SummaryCommand(predicate);
        model.updateFilteredEntryList(predicate);
        Double totalExpenditure = model.getFilteredExpenditureList()
                .stream()
                .mapToDouble(entry -> Double.parseDouble(entry.getAmount().toString()))
                .sum();
        Double totalIncome = model.getFilteredIncomeList()
                .stream()
                .mapToDouble(entry -> Double.parseDouble(entry.getAmount().toString()))
                .sum();
        String expectedMessage = String.format(
                SummaryCommand.MESSAGE_SUCCESS,
                totalExpenditure,
                totalIncome,
                totalIncome - totalExpenditure);
        assertCommandSuccess(summaryCommand, model, expectedMessage, model);
    }
}
