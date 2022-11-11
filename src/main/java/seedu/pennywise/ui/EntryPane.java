package seedu.pennywise.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.pennywise.commons.core.LogsCenter;

/**
 * Panel containing all entries.
 */
public class EntryPane extends UiPart<Region> {
    private static final String FXML = "EntryPane.fxml";
    private final Logger logger = LogsCenter.getLogger(seedu.pennywise.ui.EntryPane.class);

    private EntryListPanel expenseEntryPanel;
    private EntryListPanel incomeEntryPanel;

    @FXML
    private Tab expenses;

    @FXML
    private Tab income;

    @FXML
    private TabPane tabPane;

    @FXML
    private StackPane expenseEntryPlaceholder;

    @FXML
    private StackPane incomeEntryPlaceholder;


    /**
     * Creates a {@code EntryPane} with default entries
     */
    public EntryPane(EntryListPanel expenseEntry, EntryListPanel incomeEntry) {
        super(FXML);
        this.expenseEntryPanel = expenseEntry;
        this.incomeEntryPanel = incomeEntry;
        expenseEntryPlaceholder.getChildren().add(expenseEntry.getRoot());
        incomeEntryPlaceholder.getChildren().add(incomeEntry.getRoot());

    }

    /**
     * Switches the UI tab pane to the {@code Expense} entry panel.
     */
    public void showExpenseEntryPanel() {
        logger.info("Showing expenses entry panel");
        expenses.setUserData(true); // indicates that this action is from PennyWise instead of user action
        tabPane.getSelectionModel().select(expenses);
    }

    /**
     * Switches the UI tab pane to the {@code Income} entry panel.
     */
    public void showIncomeEntryPanel() {
        logger.info("Showing income entry panel");
        income.setUserData(true); // indicates that this action is from PennyWise instead of user action
        tabPane.getSelectionModel().select(income);
    }

    public Tab getIncome() {
        return income;
    }

    public Tab getExpenses() {
        return expenses;
    }

    public EntryListPanel getExpenseEntryPanel() {
        return this.expenseEntryPanel;
    }

    public EntryListPanel getIncomeEntryPanel() {
        return this.incomeEntryPanel;
    }
}
