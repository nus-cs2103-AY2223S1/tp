package seedu.pennywise.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import seedu.pennywise.commons.core.GuiSettings;
import seedu.pennywise.logic.commands.CommandResult;
import seedu.pennywise.logic.commands.exceptions.CommandException;
import seedu.pennywise.logic.parser.exceptions.ParseException;
import seedu.pennywise.model.ReadOnlyPennyWise;
import seedu.pennywise.model.entry.Entry;

/**
 * API of the Logic component.
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     *
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException   If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the PennyWise.
     *
     * @see seedu.pennywise.model.Model#getPennyWise()
     */
    ReadOnlyPennyWise getPennyWise();

    /**
     * Returns an unmodifiable view of the filtered list of expenditure.
     */
    ObservableList<Entry> getFilteredExpenditureList();

    /**
     * Returns an unmodifiable view of the filtered list of income.
     */
    ObservableList<Entry> getFilteredIncomeList();

    /**
     * Returns an unmodifiable list of pie chart data for income.
     */
    ObservableList<PieChart.Data> getIncomePieChartData();

    /**
     * Returns an unmodifiable list of pie chart data for expenditure.
     */
    ObservableList<PieChart.Data> getExpensePieChartData();

    /**
     * Returns an unmodifiable list of line chart data for expenditure.
     */
    XYChart.Series<String, Number> getExpenseLineChartData();

    /**
     * Returns an unmodifiable list of line chart data for income.
     */
    XYChart.Series<String, Number> getIncomeLineChartData();

    /**
     * Returns the user prefs' penny wise file path.
     */
    Path getPennyWiseFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
