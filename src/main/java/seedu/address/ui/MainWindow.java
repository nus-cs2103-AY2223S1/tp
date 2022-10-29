package seedu.address.ui;

import java.util.Stack;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private final Stage primaryStage;
    private final Logic logic;
    private final HelpWindow helpWindow;
    private final HelpPanel helpPanel;
    private final DetailHelpPanel detailHelpPanel;
    private final Stack<MainPanelName> mainPanelHistory = new Stack<>();
    // Independent Ui parts residing in this Ui container
    private CommandBox commandBox;
    private PersonListPanel personListPanel;
    private DetailPanel detailPanel;
    private ResultDisplay resultDisplay;
    private MainPanelName currentMainPanel;
    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane mainPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

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

        helpWindow = new HelpWindow();

        helpPanel = new HelpPanel();
        detailHelpPanel = new DetailHelpPanel();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        personListPanel = new PersonListPanel(logic.getSortedFilteredPersonList(), this::selectPerson);

        currentMainPanel = MainPanelName.List;
        mainPanelPlaceholder.getChildren().add(personListPanel.getRoot());

        detailPanel = new DetailPanel(logic.getSelectedPerson());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());

        setupUserInteraction();
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
     * Switch between different main panels
     *
     * @param mainPanelName to be switched
     * @param recordHistory flag to indicate whether this action need
     *                      to be stored
     */
    private void switchMainPanel(MainPanelName mainPanelName, boolean recordHistory) {
        if (currentMainPanel.equals(mainPanelName)) {
            return;
        }

        if (recordHistory) {
            // Record the current panel before proceed to next panel;
            mainPanelHistory.push(currentMainPanel);
        }
        currentMainPanel = mainPanelName;

        MainPanel panelToSwitch = null;
        switch (mainPanelName) {
        case List:
            panelToSwitch = personListPanel;
            break;
        case Help:
            panelToSwitch = helpPanel;
            break;
        case Detail:
            panelToSwitch = detailPanel;
            break;
        case DetailHelp:
            panelToSwitch = detailHelpPanel;
            break;
        default:
            panelToSwitch = personListPanel;
            break;
        }

        assert panelToSwitch != null;

        mainPanelPlaceholder.getChildren().clear();
        mainPanelPlaceholder.getChildren().add(panelToSwitch.getRoot());
    }

    private void setupUserInteraction() {
        this.getRoot().addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                handleBack();
            } else if (event.getCode().equals(KeyCode.F1)) {
                handleHelp();
            }
        });

        personListPanel.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            Person selectedPerson = personListPanel.getSelectedPerson();
            boolean isFirstPersonSelected = logic.getSortedFilteredPersonList().size() == 0
                    || selectedPerson.equals(logic.getSortedFilteredPersonList().get(0));

            if (isFirstPersonSelected && event.getCode().equals(KeyCode.UP)) {
                personListPanel.setFocus(false);
                personListPanel.clearSelectedPerson();
                commandBox.focus();
            }

            if (event.getCode().equals(KeyCode.ENTER)) {
                selectPerson(selectedPerson);
            }
        });

        personListPanel.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                if (event.getClickCount() == 2) {
                    selectPerson(personListPanel.getSelectedPerson());
                }
            }
        });

        personListPanel.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            // Ignore tab navigation when the list view is empty
            if (event.getCode().equals(KeyCode.TAB)) {
                event.consume();
            }
        });

        commandBox.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            boolean isEmpty = logic.getSortedFilteredPersonList().size() == 0;
            if (!isEmpty && event.getCode().equals(KeyCode.DOWN)) {
                personListPanel.setFocus(true);
                personListPanel.setSelectedPerson(logic.getSortedFilteredPersonList().get(0));
            }
        });

        commandBox.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            // Ignore tab navigation when the list view is empty
            if (event.getCode().equals(KeyCode.TAB)) {
                event.consume();
            }
        });
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (currentMainPanel.equals(MainPanelName.List)) {
            switchMainPanel(MainPanelName.Help, true);
        } else if (currentMainPanel.equals(MainPanelName.Detail)) {
            switchMainPanel(MainPanelName.DetailHelp, true);
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

    private void handleBack() {
        if (!mainPanelHistory.empty()) {
            MainPanelName to = mainPanelHistory.pop();
            boolean backToListPanel = to.equals(MainPanelName.List) && currentMainPanel.equals(MainPanelName.Detail);

            switchMainPanel(to, false);

            if (backToListPanel) {
                personListPanel.setFocus(true);
            }
        }
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.address.logic.Logic#execute(String, MainPanelName)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText, this.currentMainPanel);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            if (commandResult.isBack()) {
                handleBack();
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }

    private void selectPerson(Person person) {
        logic.setSelectedPerson(person);
        switchMainPanel(MainPanelName.Detail, true);
    }
}
