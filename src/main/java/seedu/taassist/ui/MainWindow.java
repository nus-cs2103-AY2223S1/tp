package seedu.taassist.ui;

import static seedu.taassist.commons.core.Messages.MESSAGE_INVALID_UI_ACTION;

import java.awt.Desktop;
import java.net.URL;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.taassist.commons.core.GuiSettings;
import seedu.taassist.commons.core.LogsCenter;
import seedu.taassist.logic.Logic;
import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.logic.commands.result.CommandResult;
import seedu.taassist.logic.parser.exceptions.ParseException;

/**
 * The Main Window. Provides the basic application layout containing
 * a button bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static HelpWindow helpWindow;

    private static final String FXML = "MainWindow.fxml";

    private static final String USERGUIDE_URL = "https://ay2223s1-cs2103t-t12-1.github.io/tp/UserGuide.html";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private ModuleClassListPanel moduleClassListPanel;
    private SessionListPanel sessionListPanel;
    private StudentListPanel studentListPanel;
    private ResultDisplay resultDisplay;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private Button helpButton;

    @FXML
    private Label classTitle;

    @FXML
    private Button unfocusButton;

    @FXML
    private HBox buttonBar;

    @FXML
    private Label classLabel;

    @FXML
    private GridPane classLabelContainer;

    @FXML
    private StackPane studentListPanelPlaceholder;

    @FXML
    private StackPane moduleClassListPanelPlaceholder;

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
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        studentListPanel = new StudentListPanel(logic.getStudentViewList());
        studentListPanelPlaceholder.getChildren().add(studentListPanel.getRoot());

        moduleClassListPanel = new ModuleClassListPanel(logic.getModuleClassList());
        moduleClassListPanelPlaceholder.getChildren().add(moduleClassListPanel.getRoot());

        sessionListPanel = new SessionListPanel(logic.getSessionList());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getTaAssistFilePath());
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
     * Opens a particular webpage, if unable to do so, help window will be shown for them to copy the URL.
     */
    public static void openWebpage(String urlString) {
        if (!Desktop.isDesktopSupported() || !Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            helpWindow.show();
            return;
        }
        try {
            Desktop.getDesktop().browse(new URL(urlString).toURI());
        } catch (Exception e) {
            helpWindow.show();
        }
    }

    /**
     * Opens the weblink to user guide using user's default browser.
     */
    @FXML
    public void handleHelp() {
        openWebpage(USERGUIDE_URL);
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

    @FXML
    private void handleFocusMode() {
        classLabel.setText("Sessions");
        unfocusButton.setVisible(true);
        classTitle.setVisible(true);
        classTitle.textProperty().bind(logic.getFocusLabelProperty());
        helpButton.setId("helpFocusButton");
        classLabelContainer.setStyle("-fx-background-color:#f5d58b");
        buttonBar.setStyle("-fx-background-color: derive(#a5dff0, 20%);");
        moduleClassListPanelPlaceholder.getChildren().remove(moduleClassListPanel.getRoot());
        moduleClassListPanelPlaceholder.getChildren().remove(sessionListPanel.getRoot());
        moduleClassListPanelPlaceholder.getChildren().add(sessionListPanel.getRoot());
    }

    private void handleUnfocusMode() {
        classLabel.setText("Classes");
        unfocusButton.setVisible(false);
        classTitle.setVisible(false);
        classLabelContainer.setStyle("-fx-background-color:#a5dff0;");
        helpButton.setId("helpButton");
        buttonBar.setStyle("-fx-background-color: derive(#EDA7A7, 20%);");
        moduleClassListPanelPlaceholder.getChildren().remove(sessionListPanel.getRoot());
        moduleClassListPanelPlaceholder.getChildren().add(moduleClassListPanel.getRoot());
    }

    @FXML
    private void buttonHandleUnfocus() {
        try {
            executeCommand("unfocus");
        } catch (Exception e) { //never encountered because button only visible in focus mode
            assert false;
        }
    }

    public StudentListPanel getStudentListPanel() {
        return studentListPanel;
    }

    public SessionListPanel getSessionListPanel() {
        return sessionListPanel;
    }

    public ModuleClassListPanel getModuleClassListPanel() {
        return moduleClassListPanel;
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.taassist.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.hasUiAction()) {
                switch (commandResult.getUiAction()) {
                case UI_HELP:
                    handleHelp();
                    break;
                case UI_EXIT:
                    handleExit();
                    break;
                case UI_FOCUS:
                    handleFocusMode();
                    break;
                case UI_UNFOCUS:
                    handleUnfocusMode();
                    break;
                case UI_NO_ACTION:
                    // fallthrough
                default:
                    logger.info(MESSAGE_INVALID_UI_ACTION);
                    throw new CommandException(MESSAGE_INVALID_UI_ACTION);
                }
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
