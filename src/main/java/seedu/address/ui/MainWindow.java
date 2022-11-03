package seedu.address.ui;

import java.util.Iterator;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.client.Client;
import seedu.address.model.remark.Remark;
import seedu.address.model.transaction.Transaction;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private ClientListPanel clientListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private RemarkListPanel remarkListPanel;
    private TransactionListPanel transactionListPanel;
    private NetTransactionBox netTransactionBox;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane clientListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    @FXML
    private StackPane remarkListPanelPlaceholder;

    @FXML
    private StackPane transactionListPanelPlaceholder;

    @FXML
    private StackPane menuPlaceholder;

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
     * @param keyCombination the KeyCombination value of the accelerator.
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
    void fillInnerParts() {
        clientListPanel = new ClientListPanel(logic.getFilteredClientList());
        clientListPanelPlaceholder.getChildren().add(clientListPanel.getRoot());

        transactionListPanel = new TransactionListPanel();
        transactionListPanelPlaceholder.getChildren().add(transactionListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        remarkListPanel = new RemarkListPanel();
        remarkListPanelPlaceholder.getChildren().add(remarkListPanel.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getJeeqTrackerFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());

        netTransactionBox = new NetTransactionBox(logic.calculateTotalTransaction(logic.getFilteredClientList()));
        menuPlaceholder.getChildren().add(netTransactionBox.getRoot());
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

    /**
     * Handles changes to the UI whenever the Remark or Transaction information is updated in a client.
     * @param commandResult the result of command executed.
     */
    private void handleClientDetailsUpdate(CommandResult commandResult) {
        ObservableList<Client> clientList = logic.getFilteredClientList();
        double updatedNetTransaction = logic.calculateTotalTransaction(clientList);
        if (clientList.size() != 1) {
            // Empty remark list panel.
            remarkListPanel.setRemarkList(FXCollections.observableArrayList());
            transactionListPanel.setTransactionList(FXCollections.observableArrayList());
            netTransactionBox.setNetTransaction(updatedNetTransaction);
            return;
        }
        Client client = clientList.get(0);
        ObservableList<Remark> remarks = client.getRemarks().asUnmodifiableObservableList();
        ObservableList<Transaction> transactions = client.getTransactions().asUnmodifiableObservableList();
        remarkListPanel.setRemarkList(remarks);
        transactionListPanel.setTransactionList(transactions);
        netTransactionBox.setNetTransaction(updatedNetTransaction);
    }

    /**
     * Handles changes to the UI whenever the filtered command is executed.
     * @param result the result of command executed.
     */
    private void handleFilterTransaction(CommandResult result) {
        ObservableList<Client> clientList = logic.getFilteredClientList();
        remarkListPanel.setRemarkList(FXCollections.observableArrayList());
        ObservableList<Transaction> transactions = FXCollections.observableArrayList();
        double updatedNetTransaction = logic.calculateTotalTransaction(clientList);
        Iterator<Client> itr = clientList.listIterator();
        while (itr.hasNext()) {
            Client client = itr.next();
            if (isBuyFilter(result)) {
                transactions.addAll(client.getBuyTransactionList());
            } else {
                transactions.addAll(client.getSellTransactionList());
            }
        }
        transactionListPanel.setTransactionList(transactions);
        netTransactionBox.setNetTransaction(updatedNetTransaction);
    }

    /**
     * Handles changes to the UI whenever the sort command is executed.
     * @param result the result of command executed.
     */

    private void handleSortTransaction(CommandResult result) {
        ObservableList<Client> clientList = logic.getFilteredClientList();
        remarkListPanel.setRemarkList(FXCollections.observableArrayList());
        double updatedNetTransaction = logic.calculateTotalTransaction(clientList);
        ObservableList<Transaction> transactions = FXCollections.observableArrayList();
        Client client = clientList.get(0);
        if (isOldestSort(result)) {
            transactions.addAll(client.getSortOldestTransaction());
        } else {
            transactions.addAll(client.getSortLatestTransaction());
        }
        ObservableList<Remark> remarks = client.getRemarks().asUnmodifiableObservableList();
        remarkListPanel.setRemarkList(remarks);
        transactionListPanel.setTransactionList(transactions);
        netTransactionBox.setNetTransaction(updatedNetTransaction);
    }

    private boolean isBuyFilter(CommandResult result) {
        String output = result.toString();
        return (output.contains("buy"));
    }

    private boolean isOldestSort(CommandResult result) {
        String output = result.toString();
        return (output.contains("oldest"));
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.address.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isShowUserGuide()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            if (commandResult.isFilteredTransactions()) {
                handleFilterTransaction(commandResult);
            } else if (commandResult.isSortedTransactions()) {
                handleSortTransaction(commandResult);
            } else if (!commandResult.hasNoUiChange()) {
                handleClientDetailsUpdate(commandResult);
            }


            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }

}
