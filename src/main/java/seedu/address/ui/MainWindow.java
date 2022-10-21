package seedu.address.ui;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";
    private static final String HELP_HOTKEY = "F1";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private PersonListPanel personListPanel;
    private TargetPersonPanel targetPersonPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private WelcomePanel welcomePanel;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private Button helpMenuItem;

    @FXML
    private StackPane personListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    @FXML
    private GridPane mainPane;

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
        setHelpShortcut();
        welcomePanel = new WelcomePanel();
        mainPane.addColumn(1, welcomePanel.getRoot());

        helpWindow = new HelpWindow();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Sets F1 to open the help window.
     */
    private void setHelpShortcut() {
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (KeyCombination.valueOf(HELP_HOTKEY).match(event)) {
                handleHelp();
                event.consume(); // To stop event from propagating to other handlers
            }
        });
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        personListPanel = new PersonListPanel(logic.getFilteredPersonList());
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());

        targetPersonPanel = new TargetPersonPanel(logic.getTargetPersonList());

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

    public PersonListPanel getPersonListPanel() {
        return personListPanel;
    }

    /**
     * Displays a message to the user via the UI.
     * @param message Message to be displayed.
     */
    public void displayMessage(String message) {
        resultDisplay.setFeedbackToUser(message);
    }

    /**
     * Sets the welcome message within welcomePanel.
     * @param message
     */
    public void setWelcomeMessage(String message) {
        requireAllNonNull(welcomePanel, message);
        welcomePanel.setWelcomeMessage(message);
    }

    /**
     * Sets the secondary pane to specified pane.
     * @param secondaryPaneState
     */
    public void setSecondaryPaneState(SecondaryPaneState secondaryPaneState) {
        requireNonNull(mainPane);
        resetSecondaryPane();
        switch(secondaryPaneState) {
        case WELCOME:
            mainPane.addColumn(1, welcomePanel.getRoot());
            break;
        case HELP:
        case TARGET_PERSON:
            mainPane.addColumn(1, targetPersonPanel.getRoot());
            break;
        case MESSAGE_TEMPLATES:
        default:
            break;
        }
    }

    /**
     * Removes the secondary pane from UI.
     */
    private void resetSecondaryPane() {
        requireNonNull(mainPane);
        ObservableList<Node> childrens = mainPane.getChildren();
        // gosh it took me more than an hour to find out how to do this
        for (Node node : childrens) {
            if (node.getStyleClass().contains("secondary-pane")) {
                logger.info("Removing secondary pane");
                mainPane.getChildren().remove(node);
                break;
            }
        }
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
            displayMessage(commandResult.getFeedbackToUser());

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.getSecondaryPaneState() != null) {
                setSecondaryPaneState(commandResult.getSecondaryPaneState());
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            displayMessage(e.getMessage());
            throw e;
        }
    }
}
