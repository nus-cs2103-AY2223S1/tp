package jeryl.fyp.ui;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import jeryl.fyp.commons.core.GuiSettings;
import jeryl.fyp.commons.core.LogsCenter;
import jeryl.fyp.logic.Logic;
import jeryl.fyp.logic.commands.CommandResult;
import jeryl.fyp.logic.commands.exceptions.CommandException;
import jeryl.fyp.logic.parser.exceptions.ParseException;

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
    private UncompletedStudentListPanel uncompletedStudentListPanel;
    private UncompletedStudentListTitle uncompletedStudentListTitle;
    private CompletedStudentListPanel completedStudentListPanel;
    private CompletedStudentListTitle completedStudentListTitle;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;

    @FXML
    private StackPane welcomeBoxPlaceholder;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane uncompletedStudentListPanelPlaceholder;

    @FXML
    private StackPane uncompletedStudentListTitlePlaceholder;

    @FXML
    private StackPane completedStudentListPanelPlaceholder;

    @FXML
    private StackPane completedStudentListTitlePlaceholder;

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

        uncompletedStudentListPanel = new UncompletedStudentListPanel(logic.getUncompletedStudentList());
        uncompletedStudentListPanelPlaceholder.getChildren().add(uncompletedStudentListPanel.getRoot());

        completedStudentListPanel = new CompletedStudentListPanel(logic.getCompletedStudentList());
        completedStudentListPanelPlaceholder.getChildren().add(completedStudentListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter();
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        WelcomeBox welcomeBox = new WelcomeBox();
        welcomeBoxPlaceholder.getChildren().add(welcomeBox.getRoot());

        UncompletedStudentListTitle uncompletedStudentListTitle = new UncompletedStudentListTitle();
        uncompletedStudentListTitlePlaceholder.getChildren().add(uncompletedStudentListTitle.getRoot());

        CompletedStudentListTitle completedStudentListTitle = new CompletedStudentListTitle();
        completedStudentListTitlePlaceholder.getChildren().add(completedStudentListTitle.getRoot());

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

    public UncompletedStudentListPanel getUncompletedStudentListPanel() {
        return uncompletedStudentListPanel;
    }

    public CompletedStudentListPanel getCompletedStudentListPanel() {
        return completedStudentListPanel;
    }

    /**
     * Outputs the sorted list of projects by project name
     */
    private void handleSortByProjectName() {
        uncompletedStudentListPanel = new UncompletedStudentListPanel(logic
                .getSortedByProjectNameUncompletedStudentList());
        uncompletedStudentListPanelPlaceholder.getChildren().add(uncompletedStudentListPanel.getRoot());

        completedStudentListPanel = new CompletedStudentListPanel(logic.getSortedCompletedStudentList());
        completedStudentListPanelPlaceholder.getChildren().add(completedStudentListPanel.getRoot());
    }

    /**
     * Outputs the sorted list of projects by project status followed by alphabetical order
     */
    private void handleSortByProjectStatus() {
        uncompletedStudentListPanel = new UncompletedStudentListPanel(logic
                .getSortedByProjectStatusUncompletedStudentList());
        uncompletedStudentListPanelPlaceholder.getChildren().add(uncompletedStudentListPanel.getRoot());

        completedStudentListPanel = new CompletedStudentListPanel(logic.getSortedCompletedStudentList());
        completedStudentListPanelPlaceholder.getChildren().add(completedStudentListPanel.getRoot());
    }

    /**
     * Reverts the UI back to the Filtered Student List
     */
    private void revertStudentListPanel() {
        uncompletedStudentListPanel = new UncompletedStudentListPanel(logic.getUncompletedStudentList());
        uncompletedStudentListPanelPlaceholder.getChildren().add(uncompletedStudentListPanel.getRoot());

        completedStudentListPanel = new CompletedStudentListPanel(logic.getCompletedStudentList());
        completedStudentListPanelPlaceholder.getChildren().add(completedStudentListPanel.getRoot());
    }

    /**
     * Executes the command and returns the result.
     *
     * @see jeryl.fyp.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException,
            RuntimeException {
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

            if (commandResult.isSortByProjectName()) {
                handleSortByProjectName();
            } else if (commandResult.isSortByProjectStatus()) {
                handleSortByProjectStatus();
            } else {
                revertStudentListPanel();
            }

            return commandResult;
        } catch (CommandException | ParseException | RuntimeException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
