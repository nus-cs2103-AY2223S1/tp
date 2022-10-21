package seedu.address.ui;

import static seedu.address.logic.commands.ListStudentCommand.COMMAND_LIST_STUDENT_STRING;
import static seedu.address.logic.commands.ListTuitionClassCommand.COMMAND_LIST_CLASS_STRING;
import static seedu.address.logic.commands.ListTutorCommand.COMMAND_LIST_TUTOR_STRING;

import java.util.logging.Logger;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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
    private static final String SELECTED_STUDENT_LABEL_STYLE_CLASS = "active-student-label";

    private static final String SELECTED_TUTOR_LABEL_STYLE_CLASS = "active-tutor-label";

    private static final String SELECTED_CLASS_LABEL_STYLE_CLASS = "active-class-label";

    private static final String UNSELECTED_LABEL_STYLE_CLASS = "inactive-label";

    private static final String FXML = "MainWindow.fxml";

    private static final Label NO_ENTITY_DISPLAYED_LABEL = new Label("No Entity Displayed");


    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private StudentListPanel studentListPanel;
    private TutorListPanel tutorListPanel;
    private TuitionClassListPanel tuitionClassListPanel;
    private StudentDescription studentDescription;
    private TutorDescription tutorDescription;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;

    private Model.ListType descriptionEntityType;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane entityListPanelPlaceholder;

    @FXML
    private Pane entityDescriptionPlaceholder;

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

        entityDescriptionPlaceholder.getChildren().add(NO_ENTITY_DISPLAYED_LABEL);
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

    /** Shows the specified entity **/
    private void handleShow(int index) {
        Model.ListType type = logic.getCurrentListType();
        entityDescriptionPlaceholder.getChildren().clear();
        descriptionEntityType = type;
        switch(type) {
        case STUDENT_LIST:
            studentDescription = new StudentDescription(
                    logic.getFilteredStudentList().get(index), index + 1);
            entityDescriptionPlaceholder.getChildren().add(studentDescription.getRoot());
            break;
        case TUTOR_LIST:
            tutorDescription = new TutorDescription(
                    logic.getFilteredTutorList().get(index), index + 1);
            entityDescriptionPlaceholder.getChildren().add(tutorDescription.getRoot());
            break;
        default:
            break;
        }
    }

    /**
     * Updates the displayed list.
     */
    private void handleList() {
        Model.ListType type = logic.getCurrentListType();
        switch (type) {
        case STUDENT_LIST:
            entityListPanelPlaceholder.getChildren().clear();
            entityListPanelPlaceholder.getChildren().add(studentListPanel.getRoot());
            break;
        case TUTOR_LIST:
            entityListPanelPlaceholder.getChildren().clear();
            entityListPanelPlaceholder.getChildren().add(tutorListPanel.getRoot());
            break;
        case TUITIONCLASS_LIST:
            entityListPanelPlaceholder.getChildren().clear();
            entityListPanelPlaceholder.getChildren().add(tuitionClassListPanel.getRoot());
            break;
        default:
            break;
        }
        setLabelStyle();
    }

    /** Displays the added entity in Description Panel. **/
    private void handleAdd() {
        Model.ListType type = logic.getCurrentListType();
        entityDescriptionPlaceholder.getChildren().clear();
        int listSize;
        switch(type) {
        case STUDENT_LIST:
            listSize = logic.getFilteredStudentList().size();
            studentDescription = new StudentDescription(
                    logic.getFilteredStudentList().get(listSize - 1), listSize - 1);
            entityDescriptionPlaceholder.getChildren().add(studentDescription.getRoot());
            break;
        case TUTOR_LIST:
            listSize = logic.getFilteredTutorList().size();
            tutorDescription = new TutorDescription(
                    logic.getFilteredTutorList().get(listSize - 1), listSize - 1);
            entityDescriptionPlaceholder.getChildren().add(tutorDescription.getRoot());
            break;
        default:
            break;
        }
    }

    /**
     * Clears the current Description Panel if the displayed entity
     * is in the cleared list.
     */
    private void handleClear() {
        if (descriptionEntityType == null) {
            return;
        }

        if (descriptionEntityType == logic.getCurrentListType()) {
            entityDescriptionPlaceholder.getChildren().clear();
            entityDescriptionPlaceholder.getChildren().add(NO_ENTITY_DISPLAYED_LABEL);
        }
    }

    /** Clears the current Description Panel if it is the deleted entity **/
    private void handleDelete(CommandResult commandResult) {
        Model.ListType type = logic.getCurrentListType();
        switch (type) {
        case STUDENT_LIST:
            if (commandResult.getDeletedStudent().equals(studentDescription.getDisplayedStudent())) {
                entityDescriptionPlaceholder.getChildren().clear();
                entityDescriptionPlaceholder.getChildren().add(NO_ENTITY_DISPLAYED_LABEL);
            }
            break;
        case TUTOR_LIST:
            if (commandResult.getDeletedTutor().equals(tutorDescription.getDisplayedTutor())) {
                entityDescriptionPlaceholder.getChildren().clear();
                entityDescriptionPlaceholder.getChildren().add(NO_ENTITY_DISPLAYED_LABEL);
            }
            break;
        default:
            break;
        }
    }

    /** Switches to the student list when the student tab is clicked **/
    @FXML
    private void switchToStudentList() throws CommandException, ParseException {
        try {
            executeCommand(COMMAND_LIST_STUDENT_STRING);

        } catch (CommandException e) {
            throw e;
        } catch (ParseException e) {
            throw e;
        }
    }

    /** Switches to the tutor list when the tutor tab is clicked **/
    @FXML
    private void switchToTutorList() throws CommandException, ParseException {
        try {
            executeCommand(COMMAND_LIST_TUTOR_STRING);

        } catch (CommandException e) {
            throw e;
        } catch (ParseException e) {
            throw e;
        }
    }

    /** Switches to the class list when the class tab is clicked **/
    @FXML
    private void switchToTuitionClassList() throws CommandException, ParseException {
        try {
            executeCommand(COMMAND_LIST_CLASS_STRING);

        } catch (CommandException e) {
            throw e;
        } catch (ParseException e) {
            throw e;
        }
    }


    /** Updates the ui of the tabs **/
    private void setLabelStyle() {
        studentLabelPanel.getStyleClass().clear();
        tutorLabelPanel.getStyleClass().clear();
        tuitionClassLabelPanel.getStyleClass().clear();
        Model.ListType type = logic.getCurrentListType();
        switch (type) {
        case STUDENT_LIST:
            tutorLabelPanel.getStyleClass().add(UNSELECTED_LABEL_STYLE_CLASS);
            tuitionClassLabelPanel.getStyleClass().add(UNSELECTED_LABEL_STYLE_CLASS);
            studentLabelPanel.getStyleClass().add(SELECTED_STUDENT_LABEL_STYLE_CLASS);
            break;
        case TUTOR_LIST:
            studentLabelPanel.getStyleClass().add(UNSELECTED_LABEL_STYLE_CLASS);
            tuitionClassLabelPanel.getStyleClass().add(UNSELECTED_LABEL_STYLE_CLASS);
            tutorLabelPanel.getStyleClass().add(SELECTED_TUTOR_LABEL_STYLE_CLASS);
            break;
        case TUITIONCLASS_LIST:
            studentLabelPanel.getStyleClass().add(UNSELECTED_LABEL_STYLE_CLASS);
            tutorLabelPanel.getStyleClass().add(UNSELECTED_LABEL_STYLE_CLASS);
            tuitionClassLabelPanel.getStyleClass().add(SELECTED_CLASS_LABEL_STYLE_CLASS);
            break;
        default:
            break;
        }
    }

    /**
     * Updates the list when specifications of an entity is changed.
     * This is needed as ObservableList does not keep track of the
     * changes in the elements of the list but only changes in the list itself.
     */
    private void updateListView() {
        Model.ListType type = logic.getCurrentListType();
        switch(type) {
        case STUDENT_LIST:
            studentListPanel = new StudentListPanel(logic.getFilteredStudentList());
            entityListPanelPlaceholder.getChildren().clear();
            entityListPanelPlaceholder.getChildren().add(studentListPanel.getRoot());
            break;
        case TUTOR_LIST:
            tutorListPanel = new TutorListPanel(logic.getFilteredTutorList());
            entityListPanelPlaceholder.getChildren().clear();
            entityListPanelPlaceholder.getChildren().add(tutorListPanel.getRoot());
            break;
        case TUITIONCLASS_LIST:
            tuitionClassListPanel = new TuitionClassListPanel(logic.getFilteredTuitionClassList());
            entityListPanelPlaceholder.getChildren().clear();
            entityListPanelPlaceholder.getChildren().add(tuitionClassListPanel.getRoot());
            break;
        default:
            break;
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

            if (commandResult.isUpdateListView()) {
                updateListView();
            }

            if (commandResult.isUpdateDescription()) {
                handleShow(commandResult.getIndex());
            }

            if (commandResult.isClear()) {
                handleClear();
            }

            if (commandResult.isAdd()) {
                handleAdd();
            }

            if (commandResult.isDelete()) {
                handleDelete(commandResult);
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
