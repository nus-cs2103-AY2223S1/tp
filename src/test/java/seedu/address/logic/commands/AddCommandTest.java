package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.PennyWise;
import seedu.address.model.ReadOnlyPennyWise;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.entry.Entry;
import seedu.address.model.entry.EntryType;
import seedu.address.testutil.ExpenditureBuilder;
import seedu.address.testutil.IncomeBuilder;


public class AddCommandTest {

    @Test
    public void constructor_nullEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null, new EntryType(EntryType.ENTRY_TYPE_EXPENDITURE)));
        assertThrows(NullPointerException.class, () -> new AddCommand(null, new EntryType(EntryType.ENTRY_TYPE_INCOME)));

    }

    // @Test
    // public void constructor_nullEntryType_throwsNullPointerException() {
    //     assertThrows(NullPointerException.class, () -> new AddCommand(new ExpenditureBuilder().build(), null));
    // }

    @Test
    public void execute_expenditureAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingEntriesAdded modelStub = new ModelStubAcceptingEntriesAdded();
        Entry validExpenditure = new ExpenditureBuilder().build();
        EntryType validEntryType = new EntryType("e");
        CommandResult commandResult = new AddCommand(validExpenditure, validEntryType).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validExpenditure), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validExpenditure), modelStub.expenditureAdded);
    }

    @Test
    public void execute_duplicateExpenditure_throwsCommandException() {
        Entry validExpenditure = new ExpenditureBuilder().build();
        EntryType validEntryType = new EntryType(EntryType.ENTRY_TYPE_EXPENDITURE);
        AddCommand addCommand = new AddCommand(validExpenditure, validEntryType);
        ModelStub modelStub = new ModelStubWithExpenditure(validExpenditure);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_ENTRY, () -> addCommand.execute(modelStub));
    }
    @Test
    public void execute_incomeAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingEntriesAdded modelStub = new ModelStubAcceptingEntriesAdded();
        Entry validIncome = new IncomeBuilder().build();
        EntryType validEntryType = new EntryType(EntryType.ENTRY_TYPE_INCOME);
        CommandResult commandResult = new AddCommand(validIncome, validEntryType).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validIncome), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validIncome), modelStub.incomeAdded);
    }

    @Test
    public void execute_duplicateIncome_throwsCommandException() {
        Entry validIncome = new IncomeBuilder().build();
        EntryType validEntryType = new EntryType("e");
        AddCommand addCommand = new AddCommand(validIncome, validEntryType);
        ModelStub modelStub = new ModelStubWithExpenditure(validIncome);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_ENTRY, () -> addCommand.execute(modelStub));
    }


    @Test
    public void equals() {
        EntryType expenditureType = new EntryType(EntryType.ENTRY_TYPE_EXPENDITURE);
        EntryType incomeType = new EntryType(EntryType.ENTRY_TYPE_INCOME);
        Entry expenditureLunch = new ExpenditureBuilder().withDescription("Lunch").build();
        Entry expenditureDinner = new ExpenditureBuilder().withDescription("Dinner").build();
        Entry incomeAllowance = new IncomeBuilder().withDescription("Allowance").build();
        Entry incomeInvestment = new IncomeBuilder().withDescription("Investment").build();

        AddCommand addExpenditureLunchCommand = new AddCommand(expenditureLunch, expenditureType);
        AddCommand addExpenditureDinnerCommand = new AddCommand(expenditureDinner, expenditureType);
        AddCommand addIncomeAllowanceCommand = new AddCommand(incomeAllowance, incomeType);
        AddCommand addIncomeInvestmentCommand = new AddCommand(incomeInvestment, incomeType);

        // same object -> returns true
        assertEquals(addExpenditureLunchCommand, addExpenditureLunchCommand);
        assertEquals(addIncomeAllowanceCommand, addIncomeAllowanceCommand);

        // same values -> returns true
        AddCommand addExpenditureLunchCommandCopy = new AddCommand(expenditureLunch, expenditureType);
        assertEquals(addExpenditureLunchCommand, addExpenditureLunchCommandCopy);
        AddCommand addIncomeAllowanceCommandCopy = new AddCommand(incomeAllowance, incomeType);
        assertEquals(addIncomeAllowanceCommand, addIncomeAllowanceCommandCopy);

        // different types -> returns false
        assertNotEquals(addExpenditureLunchCommandCopy, 1);
        assertNotEquals(addIncomeAllowanceCommandCopy, 1);

        // null -> returns false
        assertNotEquals(addExpenditureDinnerCommand, null);
        assertNotEquals(addIncomeInvestmentCommand, null);

        // different entry -> returns false
        assertNotEquals(addExpenditureLunchCommand, addExpenditureDinnerCommand);
        assertNotEquals(addIncomeAllowanceCommand, addIncomeInvestmentCommand);
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getPennyWiseFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPennyWiseFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPennyWise(ReadOnlyPennyWise newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyPennyWise getPennyWise() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasExpenditure(Entry expenditure) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteExpenditure(Entry expenditure) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addExpenditure(Entry expenditure) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setExpenditure(Entry target, Entry editedExpenditure) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasIncome(Entry income) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteIncome(Entry income) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addIncome(Entry income) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setIncome(Entry target, Entry editedIncome) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Entry> getFilteredExpenditureList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Entry> getFilteredIncomeList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<PieChart.Data> getIncomePieChartData() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<PieChart.Data> getExpensePieChartData() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public XYChart.Series<String, Number> getExpenseLineChartData() {
            return null;
        }

        @Override
        public XYChart.Series<String, Number> getIncomeLineChartData() {
            return null;
        }

        @Override
        public void updateFilteredEntryList(Predicate<Entry> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredExpenditureList(Predicate<Entry> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredIncomeList(Predicate<Entry> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMonthForChart(YearMonth month) {

        }
    }

    /**
     * A Model stub that contains a single expenditure
     */
    private class ModelStubWithExpenditure extends ModelStub {
        private final Entry expenditure;

        ModelStubWithExpenditure(Entry entry) {
            requireNonNull(entry);
            this.expenditure = entry;
        }

        @Override
        public boolean hasExpenditure(Entry expenditure) {
            requireNonNull(expenditure);
            return this.expenditure.isSameEntry(expenditure);
        }
    }

    /**
     * A Model stub that contains a single income
     */
    private class ModelStubWithIncome extends ModelStub {
        private final Entry income;

        ModelStubWithIncome(Entry entry) {
            requireNonNull(entry);
            this.income = entry;
        }

        @Override
        public boolean hasIncome(Entry income) {
            requireNonNull(income);
            return this.income.isSameEntry(income);
        }
    }

    /**
     * A Model stub that always accept the expenditure being added.
     */
    private class ModelStubAcceptingEntriesAdded extends ModelStub {
        final ArrayList<Entry> expenditureAdded = new ArrayList<>();
        final ArrayList<Entry> incomeAdded = new ArrayList<>();

        @Override
        public boolean hasIncome(Entry income) {
            requireNonNull(income);
            return incomeAdded.stream().anyMatch(income::isSameEntry);
        }

        @Override
        public void addIncome(Entry income) {
            requireNonNull(income);
            incomeAdded.add(income);
        }
        @Override
        public boolean hasExpenditure(Entry expenditure) {
            requireNonNull(expenditure);
            return expenditureAdded.stream().anyMatch(expenditure::isSameEntry);
        }

        @Override
        public void addExpenditure(Entry expenditure) {
            requireNonNull(expenditure);
            expenditureAdded.add(expenditure);
        }

        @Override
        public ReadOnlyPennyWise getPennyWise() {
            return new PennyWise();
        }
    }

}
