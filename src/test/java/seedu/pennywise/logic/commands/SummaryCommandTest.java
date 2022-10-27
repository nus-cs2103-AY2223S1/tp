package seedu.pennywise.logic.commands;

import static seedu.pennywise.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.pennywise.testutil.TypicalEntry.getTypicalPennyWise;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.pennywise.model.Model;
import seedu.pennywise.model.ModelManager;
import seedu.pennywise.model.UserPrefs;
import seedu.pennywise.model.entry.Date;
import seedu.pennywise.model.entry.EntryInYearMonthPredicate;

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
