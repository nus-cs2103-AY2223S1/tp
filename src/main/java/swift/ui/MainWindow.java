package swift.ui;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import swift.commons.core.GuiSettings;
import swift.commons.core.LogsCenter;
import swift.logic.Logic;
import swift.logic.commands.CommandResult;
import swift.logic.commands.CommandType;
import swift.logic.commands.exceptions.CommandException;
import swift.logic.parser.exceptions.ParseException;

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
    private PersonListPanel personListPanel;
    private TaskListPanel taskListPanel;
    private ResultDisplay resultDisplay;
    private PersonTaskListPanel personTaskListPanel;
    private TaskPersonListPanel taskPersonListPanel;
    private HelpWindow helpWindow;

    private boolean isContactTabShown;

    @FXML
    private StackPane listPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane personTaskListPanelPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private MenuItem openTaskTabItem;

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

        isContactTabShown = true;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
    }

    /**
     * Sets the accelerator of a MenuItem.
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
    void fillInnerParts() {
        personListPanel = new PersonListPanel(logic.getFilteredPersonList());
        listPanelPlaceholder.getChildren().add(personListPanel.getRoot());

        personTaskListPanel = new PersonTaskListPanel(logic.getFilteredTaskList(),
                logic.getUnfilteredBridgeList(), logic.getFilteredPersonList());
        personTaskListPanelPlaceholder.getChildren().add(personTaskListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
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
     * Switch to tasks tab.
     */
    @FXML
    private void showTaskTab() {
        taskListPanel = new TaskListPanel(logic.getFilteredTaskList(), logic.getUnfilteredBridgeList(),
                logic.getFilteredPersonList());

        listPanelPlaceholder.getChildren().clear();
        listPanelPlaceholder.getChildren().add(taskListPanel.getRoot());

        showContactsInTaskTab();
    }

    /**
     * Switch to contacts tab.
     */
    @FXML
    private void showContactTab() {
        personListPanel = new PersonListPanel(logic.getFilteredPersonList());
        listPanelPlaceholder.getChildren().clear();
        listPanelPlaceholder.getChildren().add(personListPanel.getRoot());

        showTasksInContactTab();
    }

    /**
     * Show the associated tasks list panel only if there is one contact selected in contact list.
     */
    @FXML
    private void showTasksInContactTab() {
        removeSidePanel();
        personTaskListPanel = new PersonTaskListPanel(logic.getFilteredTaskList(),
                logic.getUnfilteredBridgeList(), logic.getUnfilteredPersonList());
        personTaskListPanel.switchToAllTasks();
        personTaskListPanelPlaceholder.getChildren().add(personTaskListPanel.getRoot());
    }

    /**
     * Show the associated persons list panel only if there is one task selected in task list.
     */
    @FXML
    private void showContactsInTaskTab() {
        removeSidePanel();
        taskPersonListPanel = new TaskPersonListPanel(logic.getFilteredPersonList());
        taskPersonListPanel.switchToAllContacts();
        personTaskListPanelPlaceholder.getChildren().add(taskPersonListPanel.getRoot());
    }

    @FXML
    private void showAssignedContacts() {
        taskPersonListPanel.switchToAssignedContacts();
    }

    @FXML
    private void showAssignedTasks() {
        personTaskListPanel.switchToAssignedTasks();
    }

    /**
     * Remove list in side panel
     * @return
     */
    @FXML
    private void removeSidePanel() {
        personTaskListPanelPlaceholder.getChildren().clear();
    }

    public PersonListPanel getPersonListPanel() {
        return personListPanel;
    }

    /**
     * Refreshes the current view.
     */
    public void refreshTab() {
        if (isContactTabShown) {
            showContactTab();
        } else {
            showTaskTab();
        }
    }

    /**
     * Executes the command and returns the result.
     *
     * @see swift.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            CommandType commandType = commandResult.getCommandType();
            switch (commandType) {
            case HELP:
                handleHelp();
                break;
            case EXIT:
                handleExit();
                break;
            case CONTACTS:
                showContactTab();
                isContactTabShown = true;
                break;
            case TASKS:
                showTaskTab();
                isContactTabShown = false;
                break;
            case ASSIGN:
            case UNASSIGN:
                refreshTab();
                break;
            case SELECT_CONTACT:
                showContactTab();
                showAssignedTasks();
                break;
            case SELECT_TASK:
                showTaskTab();
                showAssignedContacts();
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
