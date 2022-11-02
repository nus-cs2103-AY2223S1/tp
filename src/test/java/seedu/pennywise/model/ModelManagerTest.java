package seedu.pennywise.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.pennywise.testutil.Assert.assertThrows;
import static seedu.pennywise.testutil.TypicalEntry.ALLOWANCE;
import static seedu.pennywise.testutil.TypicalEntry.DINNER;
import static seedu.pennywise.testutil.TypicalEntry.LUNCH;
import static seedu.pennywise.testutil.TypicalEntry.getExpenditureFilteredByMonthPennyWise;
import static seedu.pennywise.testutil.TypicalEntry.getTypicalPennyWise;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import seedu.pennywise.commons.core.GuiSettings;
import seedu.pennywise.testutil.PennyWiseBuilder;
import seedu.pennywise.testutil.TypicalLineChartData;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new PennyWise(), new PennyWise(modelManager.getPennyWise()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setPennyWiseFilePath(Paths.get("pennywise/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setPennyWiseFilePath(Paths.get("new/pennywise/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setPennyWiseFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setPennyWiseFilePath(null));
    }

    @Test
    public void setPennyWiseFilePath_validPath_setsPennyWiseFilePath() {
        Path path = Paths.get("pennywise/book/file/path");
        modelManager.setPennyWiseFilePath(path);
        assertEquals(path, modelManager.getPennyWiseFilePath());
    }

    @Test
    public void hasExpenditure_nullExpenditure_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasExpenditure(null));
    }

    @Test
    public void hasIncome_nullIncome_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasIncome(null));
    }

    @Test
    public void hasExpenditure_incomeNotInPennyWise_returnsFalse() {
        assertFalse(modelManager.hasExpenditure(LUNCH));
    }

    @Test
    public void hasExpenditure_expenditureInPennyWise_returnsTrue() {
        modelManager.addExpenditure(LUNCH);
        assertTrue(modelManager.hasExpenditure(LUNCH));
    }

    @Test
    public void hasIncome_incomeNotInPennyWise_returnsFalse() {
        assertFalse(modelManager.hasIncome(ALLOWANCE));
    }

    @Test
    public void hasIncome_incomeInPennyWise_returnsTrue() {
        modelManager.addIncome(ALLOWANCE);
        assertTrue(modelManager.hasIncome(ALLOWANCE));
    }

    @Test
    public void getFilteredExpenditureList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredExpenditureList().remove(0));
    }

    @Test
    public void getExpensePieChart_emptyExpensesArray_success() {
        ObservableList<PieChart.Data> expectedExpensePieChartData = FXCollections.observableArrayList();
        ObservableList<PieChart.Data> actualExpensePieChartData = modelManager.getExpensePieChartData();
        assertEquals(expectedExpensePieChartData, actualExpensePieChartData);
    }

    @Test
    public void getExpensePieChart_validExpensesArray_success() {
        ObservableList<PieChart.Data> expectedExpensePieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Food", 10.00), new PieChart.Data("Groceries", 0.00),
                new PieChart.Data("Entertainment", 8.00), new PieChart.Data("Education", 0.00),
                new PieChart.Data("Housing", 0.00), new PieChart.Data("Others", 0.00));

        PennyWise typicalPennyWise = getTypicalPennyWise();
        modelManager.setPennyWise(typicalPennyWise);

        ObservableList<PieChart.Data> actualExpensePieChartData = modelManager.getExpensePieChartData();

        for (int i = 0; i < 6; i++) {
            PieChart.Data expectedCategory = expectedExpensePieChartData.get(i);
            PieChart.Data actualCategory = actualExpensePieChartData.get(i);

            assertEquals(expectedCategory.getName(), actualCategory.getName());
            assertEquals(expectedCategory.getPieValue(), actualCategory.getPieValue());
        }
    }

    @Test
    public void getIncomePieChart_emptyIncomeArray_success() {
        ObservableList<PieChart.Data> expectedIncomePieChartData = FXCollections.observableArrayList();
        ObservableList<PieChart.Data> actualIncomePieChartData = modelManager.getIncomePieChartData();
        assertEquals(expectedIncomePieChartData, actualIncomePieChartData);
    }

    @Test
    public void getIncomePieChart_validIncomeArray_success() {
        ObservableList<PieChart.Data> expectedIncomePieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Salary", 0.0), new PieChart.Data("Allowance", 300.0),
                new PieChart.Data("Profit", 0.0), new PieChart.Data("Investment", 40.80),
                new PieChart.Data("Gifts", 0.0), new PieChart.Data("Others", 0.0));

        PennyWise typicalPennyWise = getTypicalPennyWise();
        modelManager.setPennyWise(typicalPennyWise);

        ObservableList<PieChart.Data> actualIncomePieChartData = modelManager.getIncomePieChartData();

        for (int i = 0; i < 6; i++) {
            PieChart.Data expectedCategory = expectedIncomePieChartData.get(i);
            PieChart.Data actualCategory = actualIncomePieChartData.get(i);

            assertEquals(expectedCategory.getName(), actualCategory.getName());
            assertEquals(expectedCategory.getPieValue(), actualCategory.getPieValue());
        }
    }

    @Test
    public void getExpenseLineChart_emptyExpensesArray_success() {
        XYChart.Series<String, Number> expectedExpenseLineChartData = new XYChart.Series<>();
        XYChart.Series<String, Number> actualExpenseLineChartData = modelManager.getExpenseLineChartData();
        assertEquals(expectedExpenseLineChartData.getData(), actualExpenseLineChartData.getData());
    }

    @Test
    public void getIncomeLineChart_emptyIncomeArray_success() {
        XYChart.Series<String, Number> expectedIncomeLineChartData = new XYChart.Series<>();
        XYChart.Series<String, Number> actualIncomeLineChartData = modelManager.getIncomeLineChartData();
        assertEquals(expectedIncomeLineChartData.getData(), actualIncomeLineChartData.getData());
    }

    @Test
    public void getExpenseLineChartData_validExpenseArray_success() {
        PennyWise filteredPennyWise = getExpenditureFilteredByMonthPennyWise();
        modelManager.setPennyWise(filteredPennyWise);
        ObservableList<XYChart.Data<String, Number>> expectedExpenseLineChartData =
                new TypicalLineChartData().getExpenditureLineChartData();

        ObservableList<XYChart.Data<String, Number>> actualExpenseLineChartData =
                modelManager.getExpenseLineChartData().getData();
        actualExpenseLineChartData.sort(Comparator.comparing(XYChart.Data::getXValue));

        modelManager.setPennyWise(new PennyWise());
        for (int i = 0; i < 31; i++) {
            assertEquals(expectedExpenseLineChartData.get(i).getXValue(),
                    actualExpenseLineChartData.get(i).getXValue());
            assertEquals(expectedExpenseLineChartData.get(i).getYValue(),
                    actualExpenseLineChartData.get(i).getYValue());
        }
    }

    @Test
    public void equals() {
        PennyWise pennyWise = new PennyWiseBuilder().withExpenditure(LUNCH).withExpenditure(DINNER).build();
        PennyWise differentPennyWise = new PennyWise();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(pennyWise, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(pennyWise, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different pennywise -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentPennyWise, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = LUNCH.getDescription().fullDescription.split("\\s+");
        modelManager.updateFilteredExpenditureList(new seedu.pennywise.model.entry.DescriptionContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(pennyWise, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredExpenditureList(Model.PREDICATE_SHOW_ALL_ENTRIES);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setPennyWiseFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(pennyWise, differentUserPrefs)));
    }

}
