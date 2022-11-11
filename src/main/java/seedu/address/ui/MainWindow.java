package seedu.address.ui;

import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
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
    /** Number of seconds delay before the application closes after running this command */
    public static final int DELAY_DURATION_SECONDS = 3;

    private static final String FXML = "MainWindow.fxml";
    private final Logger logger = LogsCenter.getLogger(getClass());
    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private TeamListPanel teamListPanel;
    private PersonListPanel personListPanel;
    private ResultDisplay resultDisplay;
    private TaskListPanel taskListPanel;
    private MemberListPanel memberListPanel;
    private HelpWindow helpWindow;

    private TeamDetailsPanel teamDetailsPanel;

    @FXML
    private StackPane teamListPanelPlaceholder;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane personListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    @FXML
    private StackPane taskListPanelPlaceholder;

    @FXML
    private StackPane teamDetailsCardPlaceHolder;

    @FXML
    private StackPane memberListPanelPlaceholder;

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
    void fillInnerParts() {
        teamListPanel = new TeamListPanel(logic.getTeamList());
        teamListPanelPlaceholder.getChildren().add(teamListPanel.getRoot());

        personListPanel = new PersonListPanel(logic.getFilteredPersonList());
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        teamDetailsPanel = new TeamDetailsPanel(logic, logic.getTeamAsProperty(), resultDisplay);
        teamDetailsCardPlaceHolder.getChildren().add(teamDetailsPanel.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getTruthTableFilePath());
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
        PauseTransition delay = new PauseTransition(Duration.seconds(DELAY_DURATION_SECONDS));
        delay.setOnFinished(event -> {
            GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                    (int) primaryStage.getX(), (int) primaryStage.getY());
            logic.setGuiSettings(guiSettings);
            helpWindow.hide();
            primaryStage.hide();
        });
        delay.play();
    }

    /**
     * Switches the application theme.
     */
    private void handleSwitch() {
        ArrayList<String> newStyleSheets = new ArrayList<>();
        ArrayList<String> oldStyleSheets = new ArrayList<>();
        for (String styleSheet : primaryStage.getScene().getStylesheets()) {
            if (Pattern.matches(".*LightTheme.*", styleSheet)) {
                oldStyleSheets.add(styleSheet);
                String newUrl = styleSheet.replaceAll("LightTheme", "DarkTheme");
                newStyleSheets.add(newUrl);
            } else if (Pattern.matches(".*DarkTheme.*", styleSheet)) {
                oldStyleSheets.add(styleSheet);
                String newUrl = styleSheet.replaceAll("DarkTheme", "LightTheme");
                newStyleSheets.add(newUrl);
            }
        }
        primaryStage.getScene().getStylesheets().addAll(newStyleSheets);
        primaryStage.getScene().getStylesheets().removeAll(oldStyleSheets);

        ArrayList<String> newHelpStyleSheets = new ArrayList<>();
        ArrayList<String> oldHelpStyleSheets = new ArrayList<>();
        for (String styleSheet : helpWindow.getRoot().getScene().getStylesheets()) {
            if (Pattern.matches(".*LightTheme.*", styleSheet)) {
                oldHelpStyleSheets.add(styleSheet);
                String newUrl = styleSheet.replaceAll("LightTheme", "DarkTheme");
                newStyleSheets.add(newUrl);
            } else if (Pattern.matches(".*DarkTheme.*", styleSheet)) {
                oldStyleSheets.add(styleSheet);
                String newUrl = styleSheet.replaceAll("DarkTheme", "LightTheme");
                newStyleSheets.add(newUrl);
            }
        }
        helpWindow.getRoot().getScene().getStylesheets().addAll(newHelpStyleSheets);
        helpWindow.getRoot().getScene().getStylesheets().removeAll(oldHelpStyleSheets);

    }

    public PersonListPanel getPersonListPanel() {
        return personListPanel;
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

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }
            if (commandResult.isSwitchTheme()) {
                handleSwitch();
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
