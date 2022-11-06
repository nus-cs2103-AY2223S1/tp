package seedu.uninurse.ui;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.uninurse.commons.core.GuiSettings;
import seedu.uninurse.commons.core.LogsCenter;
import seedu.uninurse.logic.Logic;
import seedu.uninurse.logic.commands.CommandResult;
import seedu.uninurse.logic.commands.exceptions.CommandException;
import seedu.uninurse.logic.parser.exceptions.ParseException;
import seedu.uninurse.model.exceptions.PatientOfInterestNotFoundException;

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
    private OutputPanel outputPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private Label patientHeader;

    @FXML
    private Label outputHeader;

    @FXML
    private StackPane personListPanelContainer;

    @FXML
    private StackPane outputPanelContainer;

    @FXML
    private StackPane resultDisplayContainer;

    @FXML
    private StackPane commandBoxContainer;

    @FXML
    private StackPane statusbarContainer;

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
     * Fills up all the Containers of this window.
     */
    void fillInnerParts() {
        personListPanel = new PersonListPanel(logic.getFilteredPersonList());
        personListPanelContainer.getChildren().add(personListPanel.getRoot());
        personListPanelContainer.prefHeightProperty().bind(this.getRoot().heightProperty());

        outputPanel = new OutputPanel();
        outputPanel.getRoot().prefWidthProperty().bind(outputPanelContainer.widthProperty());
        outputPanel.getRoot().prefHeightProperty().bind(outputPanelContainer.heightProperty());

        outputPanelContainer.getChildren().add(outputPanel.getRoot());
        outputPanelContainer.prefHeightProperty().bind(this.getRoot().heightProperty());

        resultDisplay = new ResultDisplay();
        resultDisplayContainer.getChildren().add(resultDisplay.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxContainer.getChildren().add(commandBox.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getUninurseBookFilePath());
        statusbarContainer.getChildren().add(statusBarFooter.getRoot());

        patientHeader.setText("Patients");
        outputHeader.setText("Output");
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
     * Executes the command and returns the result.
     *
     * @see seedu.uninurse.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            outputPanel.clear();
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            if (commandResult.isViewPatient()) {
                outputPanel.handleViewPatient(logic.getPatientOfInterest());
            }

            if (commandResult.isListTask()) {
                outputPanel.handleListTask(logic.getPatientList());
            }

            if (commandResult.isTaskRelated()) {
                outputPanel.handleTask(logic.getPatientOfInterest());
            }

            if (commandResult.isSchedule()) {
                outputPanel.handleSchedule(logic.getSchedule());
            }

            if (commandResult.isAddPatient()) {
                outputPanel.handleAddPatient(logic.getPatientOfInterest());
            }

            if (commandResult.isEditPatient()) {
                outputPanel.handleEditPatient(logic.getPatientOfInterest());
            }

            if (commandResult.isDeletePatient()) {
                outputPanel.handleDeletePatient(logic.getPatientOfInterest());
            }

            if (commandResult.isUndo()) {
                outputPanel.handleUndo(logic.getPersonListTracker());
            }

            if (commandResult.isRedo()) {
                outputPanel.handleRedo(logic.getPersonListTracker());
            }

            if (commandResult.isFind()) {
                outputPanel.handleFind(logic.getPatientList());
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        } catch (PatientOfInterestNotFoundException e) {
            logger.info("Patient of interest not found");
            throw e;
        }
    }
}
