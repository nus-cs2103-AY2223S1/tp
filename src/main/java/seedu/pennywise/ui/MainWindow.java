package seedu.pennywise.ui;

import java.util.function.Supplier;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.pennywise.commons.core.GuiSettings;
import seedu.pennywise.commons.core.LogsCenter;
import seedu.pennywise.logic.Logic;
import seedu.pennywise.logic.commands.CommandResult;
import seedu.pennywise.logic.commands.exceptions.CommandException;
import seedu.pennywise.logic.parser.exceptions.ParseException;
import seedu.pennywise.model.GraphConfiguration;
import seedu.pennywise.model.entry.EntryType;
import seedu.pennywise.model.entry.GraphType;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private final Stage primaryStage;
    private final Logic logic;

    // Independent Ui parts residing in this Ui container
    private EntryPane entryPane;
    private ResultDisplay resultDisplay;
    private final HelpWindow helpWindow;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane entryPanePlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusBarPlaceholder;

    @FXML
    private StackPane graphPanelPlaceholder;

    private GraphPanel currGraphPanel;
    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        setAccelerators();

        helpWindow = new HelpWindow();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
    }

    /**
     * Sets the accelerator of a MenuItem.
     *
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);

        /*
         * TODO: the code below can be removed once the bug reported here
         * https://bugs.openjdk.java.net/browse/JDK-8131666
         * is fixed in later version of SDK.
         *
         * According to the bug report, TextInputControl (TextField, TextArea) will
         * consume function-key events. Because CommandBox contains a TextField, and
         * ResultDisplay contains a TextArea, thus some accelerators (e.g F1) will
         * not work when the focus is in them because the key event is consumed by
         * the TextInputControl(s).
         *
         * For now, we add following event filter to capture such key events and open
         * help window purposely so to support accelerators even when focus is
         * in CommandBox or ResultDisplay.
         */
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                menuItem.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
    }

    /**
     * Fills up all the placeholders of this window.
     */
    public void fillInnerParts() {
        EntryListPanel expenseEntryPanel = new EntryListPanel(logic.getFilteredExpenditureList());
        EntryListPanel incomeEntryPanel = new EntryListPanel(logic.getFilteredIncomeList());

        entryPane = new EntryPane(expenseEntryPanel, incomeEntryPanel);
        entryPanePlaceholder.getChildren().add(entryPane.getRoot());

        entryPane.getExpenses().setOnSelectionChanged((EventHandler<Event>) evt -> {
            Object data = entryPane.getExpenses().getUserData();
            entryPane.getExpenses().setUserData(false);
            if (!entryPane.getExpenses().isSelected()) {
                return;
            }

            // if the change in tab selection is caused by a user command (eg. view t/e)
            if (data != null && data.equals(true)) {
                return;
            }

            // if the change in tab selection is caused by user manually toggling,
            // show pie chart with reset filters
            GraphConfiguration expenditureGraphConfig = new GraphConfiguration(
                    new EntryType(EntryType.ENTRY_TYPE_EXPENDITURE),
                    new GraphType(GraphType.GRAPH_TYPE_CATEGORY),
                    true);
            CommandResult expenditureCommandResult = new CommandResult(
                    "",
                    false,
                    false,
                    expenditureGraphConfig);
            this.updateGraph(expenditureCommandResult);
        });
        entryPane.getIncome().setOnSelectionChanged((EventHandler<Event>) evt -> {
            Object data = entryPane.getIncome().getUserData();
            entryPane.getIncome().setUserData(false);
            if (!entryPane.getIncome().isSelected()) {
                return;
            }

            // if the change in tab selection is caused by a user command (eg. view t/e)
            if (data != null && data.equals(true)) {
                return;
            }

            // if the change in tab selection is caused by user manually toggling,
            // show pie chart with reset filters
            GraphConfiguration incomeGraphConfig = new GraphConfiguration(
                    new EntryType(EntryType.ENTRY_TYPE_INCOME),
                    new GraphType(GraphType.GRAPH_TYPE_CATEGORY),
                    true);
            CommandResult incomeCommandResult = new CommandResult(
                    "",
                    false,
                    false,
                    incomeGraphConfig);
            this.updateGraph(incomeCommandResult);
        });
        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getPennyWiseFilePath());
        statusBarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());

        this.currGraphPanel = new GraphPanel(new EntryType(EntryType.ENTRY_TYPE_EXPENDITURE),
                logic.getExpensePieChartData());

        graphPanelPlaceholder.getChildren().add(this.currGraphPanel.getRoot());
    }


    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }

    void show() {
        primaryStage.show();
    }

    /**
     * Updates the graph shown in the graph panel depending on the graph configuration associated with
     * the provided command result.
     *
     * @param commandResult The provided command result.
     */
    private void updateGraph(CommandResult commandResult) {
        graphPanelPlaceholder.getChildren().clear();

        GraphConfiguration graphConfiguration = commandResult.getGraphConfiguration();
        if (!graphConfiguration.getShouldUpdateGraph()) {
            return;
        }
        GraphType graphType = graphConfiguration.getGraphType();
        EntryType entryType = graphConfiguration.getEntryType();
        if (entryType == null) {
            entryType = this.currGraphPanel.getEntryType();
        }
        if (graphType == null) {
            graphType = this.currGraphPanel.getGraphType();
        }
        assert graphType != null;
        assert entryType != null;

        EntryType finalEntryType = entryType;
        Supplier<ObservableList<PieChart.Data>> pieChartDataSupplier = () -> {
            switch (finalEntryType.getEntryType()) {
            case EXPENDITURE:
                return logic.getExpensePieChartData();
            case INCOME:
                return logic.getIncomePieChartData();
            default:
                // Should never reach here
                return null;
            }
        };

        Supplier<XYChart.Series<String, Number>> lineChartDataSupplier = () -> {
            switch (finalEntryType.getEntryType()) {
            case EXPENDITURE:
                return logic.getExpenseLineChartData();
            case INCOME:
                return logic.getIncomeLineChartData();
            default:
                // Should never reach here
                return null;
            }
        };

        GraphPanel graphPanel = null;
        switch (graphType.getGraphType()) {
        case CATEGORY:
            graphPanel = new GraphPanel(entryType, pieChartDataSupplier.get());
            break;
        case MONTH:
            graphPanel = new GraphPanel(entryType, lineChartDataSupplier.get());
            break;
        default:
            break;
        }

        assert graphPanel != null;
        this.currGraphPanel = graphPanel;
        graphPanelPlaceholder.getChildren().add(graphPanel.getRoot());
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
    }

    public EntryListPanel getEntryListPanel() {
        return entryPane.getExpenseEntryPanel();
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.pennywise.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            GraphConfiguration graphConfiguration = commandResult.getGraphConfiguration();
            if (graphConfiguration.getShouldUpdateGraph()) {
                updateGraph(commandResult);
            }
            EntryType entryType = this.currGraphPanel.getEntryType();
            switch (entryType.getEntryType()) {
            case EXPENDITURE:
                entryPane.showExpenseEntryPanel();
                break;
            case INCOME:
                entryPane.showIncomeEntryPanel();
                break;
            default:
                break;
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
