package seedu.pennywise.model;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
import static java.util.Objects.requireNonNull;
import static seedu.pennywise.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.function.Predicate;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import seedu.pennywise.commons.core.GuiSettings;
import seedu.pennywise.commons.core.LogsCenter;
import seedu.pennywise.model.entry.Entry;
import seedu.pennywise.model.entry.Tag;

/**
 * Represents the in-memory model of the PennyWise application data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final PennyWise pennyWise;
    private final UserPrefs userPrefs;
    private final FilteredList<Entry> filteredExpenditure;
    private final FilteredList<Entry> filteredIncome;

    /**
     * Initializes a ModelManager with the given {@code pennyWise} and {@code userPrefs}.
     */
    public ModelManager(ReadOnlyPennyWise pennyWise, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(pennyWise, userPrefs);

        logger.fine("Initializing with PennyWise: " + pennyWise + " and user preferences " + userPrefs);

        this.pennyWise = new PennyWise(pennyWise);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredExpenditure = new FilteredList<>(this.pennyWise.getExpenditureList());
        filteredIncome = new FilteredList<>(this.pennyWise.getIncomeList());
    }

    public ModelManager() {
        this(new PennyWise(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getPennyWiseFilePath() {
        return userPrefs.getPennyWiseFilePath();
    }

    @Override
    public void setPennyWiseFilePath(Path pennyWiseFilePath) {
        requireNonNull(pennyWiseFilePath);
        userPrefs.setPennyWiseFilePath(pennyWiseFilePath);
    }

    //=========== PennyWise ================================================================================

    @Override
    public void setPennyWise(ReadOnlyPennyWise pennyWise) {
        this.pennyWise.resetData(pennyWise);
    }

    @Override
    public ReadOnlyPennyWise getPennyWise() {
        return pennyWise;
    }

    @Override
    public boolean hasExpenditure(Entry entry) {
        requireNonNull(entry);
        return pennyWise.hasExpenditure(entry);
    }

    @Override
    public void deleteExpenditure(Entry target) {
        pennyWise.removeExpenditure(target);
    }

    @Override
    public void addExpenditure(Entry entry) {
        pennyWise.addExpenditure(entry);
    }

    @Override
    public void setExpenditure(Entry target, Entry editedEntry) {
        requireAllNonNull(target, editedEntry);

        pennyWise.setExpenditure(target, editedEntry);
    }

    @Override
    public boolean hasIncome(Entry entry) {
        requireNonNull(entry);
        return pennyWise.hasIncome(entry);
    }

    @Override
    public void deleteIncome(Entry target) {
        pennyWise.removeIncome(target);
    }

    @Override
    public void addIncome(Entry entry) {
        pennyWise.addIncome(entry);
    }

    @Override
    public void setIncome(Entry target, Entry editedEntry) {
        requireAllNonNull(target, editedEntry);
        pennyWise.setIncome(target, editedEntry);
    }

    //=========== Filtered Entry List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code filteredExpenditure} backed by the internal list of
     * {@code versionedPennyWise}
     */
    @Override
    public ObservableList<Entry> getFilteredExpenditureList() {
        return filteredExpenditure;
    }

    @Override
    public ObservableList<Entry> getFilteredIncomeList() {
        return filteredIncome;
    }

    /**
     * Updates the filter of the filtered entry list to filter by the given {@code predicate}.
     */
    public void updateFilteredEntryList(Predicate<Entry> predicate) {
        requireNonNull(predicate);
        filteredExpenditure.setPredicate(predicate);
        filteredIncome.setPredicate(predicate);
    }

    /**
     * Updates the filter of the filtered expenditure list to filter by the given {@code predicate}.
     */
    public void updateFilteredExpenditureList(Predicate<Entry> predicate) {
        requireNonNull(predicate);
        filteredExpenditure.setPredicate(predicate);
    }

    /**
     * Updates the filter of the filtered income list to filter by the given {@code predicate}.
     */
    public void updateFilteredIncomeList(Predicate<Entry> predicate) {
        requireNonNull(predicate);
        filteredIncome.setPredicate(predicate);
    }

    /**
     * Gets pie chart data of income entries
     *
     * @return ObservableList of income pie chart data
     */
    public ObservableList<PieChart.Data> getIncomePieChartData() {
        // make sure pie chart shows all unfiltered data
        updateFilteredIncomeList(PREDICATE_SHOW_ALL_ENTRIES);

        if (filteredIncome.size() == 0) {
            return FXCollections.unmodifiableObservableList(FXCollections.observableArrayList());
        }

        double[] incomePieChartArr = new double[6];

        for (Entry e : filteredIncome) {
            Tag.IncomeTag tagName = (Tag.IncomeTag) e.getTag().getEntryTag();
            switch (tagName) {
            case SALARY:
                incomePieChartArr[0] += e.getAmount().getValue();
                break;
            case ALLOWANCE:
                incomePieChartArr[1] += e.getAmount().getValue();
                break;
            case PROFIT:
                incomePieChartArr[2] += e.getAmount().getValue();
                break;
            case INVESTMENT:
                incomePieChartArr[3] += e.getAmount().getValue();
                break;
            case GIFTS:
                incomePieChartArr[4] += e.getAmount().getValue();
                break;
            case OTHERS:
                incomePieChartArr[5] += e.getAmount().getValue();
                break;
            default:
                break;
            }
        }

        ObservableList<PieChart.Data> incomePieChartData = FXCollections.observableArrayList(
            new PieChart.Data("Salary", incomePieChartArr[0]), new PieChart.Data("Allowance", incomePieChartArr[1]),
            new PieChart.Data("Profit", incomePieChartArr[2]), new PieChart.Data("Investment", incomePieChartArr[3]),
            new PieChart.Data("Gifts", incomePieChartArr[4]), new PieChart.Data("Others", incomePieChartArr[5]));

        return FXCollections.unmodifiableObservableList(incomePieChartData);
    }


    /**
     * Gets pie chart data of expense entries
     *
     * @return ObservableList of expense pie chart data
     */
    public ObservableList<PieChart.Data> getExpensePieChartData() {
        // make sure pie chart shows all unfiltered data
        updateFilteredExpenditureList(PREDICATE_SHOW_ALL_ENTRIES);

        if (filteredExpenditure.size() == 0) {
            return FXCollections.unmodifiableObservableList(FXCollections.observableArrayList());
        }
        double[] expensePieChartArr = new double[6];

        for (Entry e : filteredExpenditure) {
            Tag.ExpenditureTag tagName = (Tag.ExpenditureTag) e.getTag().getEntryTag();
            switch (tagName) {
            case FOOD:
                expensePieChartArr[0] += e.getAmount().getValue();
                break;
            case GROCERIES:
                expensePieChartArr[1] += e.getAmount().getValue();
                break;
            case ENTERTAINMENT:
                expensePieChartArr[2] += e.getAmount().getValue();
                break;
            case EDUCATION:
                expensePieChartArr[3] += e.getAmount().getValue();
                break;
            case HOUSING:
                expensePieChartArr[4] += e.getAmount().getValue();
                break;
            case OTHERS:
                expensePieChartArr[5] += e.getAmount().getValue();
                break;
            default:
                break;
            }
        }

        ObservableList<PieChart.Data> expensePieChartData = FXCollections.observableArrayList(
            new PieChart.Data("Food", expensePieChartArr[0]), new PieChart.Data("Groceries", expensePieChartArr[1]),
            new PieChart.Data("Entertainment", expensePieChartArr[2]),
            new PieChart.Data("Education", expensePieChartArr[3]), new PieChart.Data("Housing", expensePieChartArr[4]),
            new PieChart.Data("Others", expensePieChartArr[5]));

        return FXCollections.unmodifiableObservableList(expensePieChartData);
    }

    @Override
    public XYChart.Series<String, Number> getExpenseLineChartData() {
        return getLineChartData(filteredExpenditure);
    }

    @Override
    public XYChart.Series<String, Number> getIncomeLineChartData() {
        return getLineChartData(filteredIncome);
    }

    private XYChart.Series<String, Number> getLineChartData(FilteredList<Entry> filteredEntryList) {
        if (filteredEntryList.size() == 0) {
            return new XYChart.Series<>();
        }
        HashMap<String, Number> dateToExpenditureMap = new HashMap<>();

        LocalDate date = filteredEntryList.get(0).getLocalDate();
        date = date.withDayOfMonth(1);
        int daysInMonth = date.lengthOfMonth();
        for (int i = 0; i < daysInMonth; i++) {
            dateToExpenditureMap.put(date.format(ISO_LOCAL_DATE), 0.00);
            date = date.plusDays(1);
        }

        ObservableList<XYChart.Data<String, Number>> expenseSeries = FXCollections.observableArrayList();

        filteredEntryList.forEach(entry -> dateToExpenditureMap.computeIfPresent(
            entry.getFormattedDate(ISO_LOCAL_DATE), (key, val) -> val.doubleValue() + entry.getAmountValue()));

        for (HashMap.Entry<String, Number> entry : dateToExpenditureMap.entrySet()) {
            expenseSeries.add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }
        return new XYChart.Series<>(expenseSeries);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return pennyWise.equals(other.pennyWise) && userPrefs.equals(other.userPrefs) && filteredExpenditure.equals(
            other.filteredExpenditure);
    }

}
