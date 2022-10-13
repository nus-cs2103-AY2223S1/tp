package seedu.address.ui;

import java.util.logging.Logger;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

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
    private StudentListPanel studentListPanel;
    private TutorListPanel tutorListPanel;
    private TuitionClassListPanel tuitionClassListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane entityListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    @FXML
    private Pane studentLabelPanel;

    @FXML
    private Pane tutorLabelPanel;

    @FXML
    private Pane tuitionClassLabelPanel;

    private String selectedLabelStyle;

    private String unselectedLabelStyle;

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
        personListPanel = new PersonListPanel(logic.getFilteredPersonList());
        studentListPanel = new StudentListPanel(logic.getFilteredStudentList());
        tutorListPanel = new TutorListPanel(logic.getFilteredTutorList());
        tuitionClassListPanel = new TuitionClassListPanel(logic.getFilteredTuitionClassList());

        entityListPanelPlaceholder.getChildren().add(studentListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        // might one to change this to show all the different file paths? or just remove it entirely.
        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getTutorAddressBookFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    /**
     * Gets the selectedlabelStyle and unselectedLabelStyle
     * from studentLabelPanel and tutorLabelPanel accordingly,
     * assuming that the .fxml file sets them to selected and
     * unselected style correctly.
     */
    public void initializeStyleClass() {
        // Get the value of the style of a selected label
        // from studentLabelPanel since it is set to the
        // selected color by default.
        // Refer to studentLabelPanel and tutorLabelPanel
        // for more details about the difference of their
        // style.
        selectedLabelStyle = studentLabelPanel.getStyleClass().get(0);
        unselectedLabelStyle = tutorLabelPanel.getStyleClass().get(0);
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
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> {
            helpWindow.hide();
            primaryStage.hide();
        });
        pause.play();
    }

    /**
     * Updates the displayed list.
     */
    @FXML
    private void handleList() {
        Model.ListType type = logic.getCurrentListType();
        entityListPanelPlaceholder.getChildren().clear();
        switch (type) {
        case STUDENT_LIST:
            entityListPanelPlaceholder.getChildren().add(studentListPanel.getRoot());
            break;
        case TUTOR_LIST:
            entityListPanelPlaceholder.getChildren().add(tutorListPanel.getRoot());
            break;
        case TUITIONCLASS_LIST:
            entityListPanelPlaceholder.getChildren().add(tuitionClassListPanel.getRoot());
            break;
        case PERSON_LIST:
            entityListPanelPlaceholder.getChildren().add(personListPanel.getRoot());
            break;
        default:
            break;
        }

        setLabelStyle(type);
    }

    private void setLabelStyle(Model.ListType type) {
        studentLabelPanel.getStyleClass().clear();
        tutorLabelPanel.getStyleClass().clear();
        tuitionClassLabelPanel.getStyleClass().clear();
        switch (type) {
        case STUDENT_LIST:
            tutorLabelPanel.getStyleClass().add(unselectedLabelStyle);
            tuitionClassLabelPanel.getStyleClass().add(unselectedLabelStyle);
            studentLabelPanel.getStyleClass().add(selectedLabelStyle);
            break;
        case TUTOR_LIST:
            studentLabelPanel.getStyleClass().add(unselectedLabelStyle);
            tuitionClassLabelPanel.getStyleClass().add(unselectedLabelStyle);
            tutorLabelPanel.getStyleClass().add(selectedLabelStyle);
            break;
        case TUITIONCLASS_LIST:
            studentLabelPanel.getStyleClass().add(unselectedLabelStyle);
            tutorLabelPanel.getStyleClass().add(unselectedLabelStyle);
            tuitionClassLabelPanel.getStyleClass().add(selectedLabelStyle);
            break;
        default:
            break;
        }
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

            if (commandResult.isList()) {
                handleList();
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
