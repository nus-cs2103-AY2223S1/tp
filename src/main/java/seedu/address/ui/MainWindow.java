package seedu.address.ui;

import static seedu.address.commons.core.GuiSettings.DARK_THEME_STRING;
import static seedu.address.commons.core.GuiSettings.GREEN_THEME_STRING;
import static seedu.address.commons.core.GuiSettings.LIGHT_THEME_STRING;
import static seedu.address.commons.core.GuiSettings.PINK_THEME_STRING;
import static seedu.address.logic.commands.ListStudentCommand.COMMAND_LIST_STUDENT_STRING;
import static seedu.address.logic.commands.ListTuitionClassCommand.COMMAND_LIST_CLASS_STRING;
import static seedu.address.logic.commands.ListTutorCommand.COMMAND_LIST_TUTOR_STRING;

import java.util.logging.Logger;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.image.ImageView;
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
import seedu.address.model.Model.ListType;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.tutor.Tutor;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {
    private static final String SELECTED_LABEL_STYLE_CLASS = "active-label";

    private static final String UNSELECTED_LABEL_STYLE_CLASS = "inactive-label";

    private static final Label NO_PERSON_DISPLAYED_LABEL = new Label("No Person Displayed");

    private static final String WELCOME_MESSAGE = "Welcome to myStudent!\n" + "Key in command to start";

    private static final String FXML = "MainWindow.fxml";

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
    private ListType descriptionEntityType;
    private String theme;

    @FXML
    private StackPane animationPanel;
    @FXML
    private ImageView logo;
    @FXML
    private ImageView welcomeMessage;
    @FXML
    private ImageView exitMessage;
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
    private Pane studentLabelPanel;

    @FXML
    private Pane tutorLabelPanel;

    @FXML
    private Pane tuitionClassLabelPanel;

    @FXML
    private Scene mainWindowScene;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        setWindow(logic.getGuiSettings());

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

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());

        entityDescriptionPlaceholder.getChildren().add(NO_PERSON_DISPLAYED_LABEL);

        resultDisplay.setFeedbackToUser(WELCOME_MESSAGE);
    }

    /**
     * Sets up the clickable student and tutor's card such that
     * the selected student or tutor will be displayed in the
     * description panel.
     */
    public void setUpClickableCards() {
        studentListPanel.getStudentListView().getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<Student>() {
                    @Override
                    public void changed(ObservableValue<? extends Student> observable,
                                        Student oldValue, Student newValue) {
                        descriptionEntityType = ListType.STUDENT_LIST;
                        entityDescriptionPlaceholder.getChildren().clear();
                        if (newValue != null) {
                            studentDescription = new StudentDescription(newValue);
                            entityDescriptionPlaceholder.getChildren().add(studentDescription.getRoot());
                        } else {
                            entityDescriptionPlaceholder.getChildren().add(NO_PERSON_DISPLAYED_LABEL);
                        }
                    }
                });

        tutorListPanel.getTutorListView().getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<Tutor>() {
                    @Override
                    public void changed(ObservableValue<? extends Tutor> observable, Tutor oldValue, Tutor newValue) {
                        descriptionEntityType = ListType.TUTOR_LIST;
                        entityDescriptionPlaceholder.getChildren().clear();
                        if (newValue != null) {
                            tutorDescription = new TutorDescription(newValue);
                            entityDescriptionPlaceholder.getChildren().add(tutorDescription.getRoot());
                        } else {
                            entityDescriptionPlaceholder.getChildren().add(NO_PERSON_DISPLAYED_LABEL);
                        }
                    }
                });
    }

    /**
     * Sets the default size and theme based on {@code guiSettings}.
     */
    private void setWindow(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
        mainWindowScene.getStylesheets().clear();
        mainWindowScene.getStylesheets().add(guiSettings.getTheme());
        this.theme = guiSettings.getTheme();
    }

    /**
     * Sets the current theme to the light theme.
     * Accessed by the "Light Theme" tab in the Menu of Ui.
     */
    @FXML
    private void updateToLightTheme() {
        mainWindowScene.getStylesheets().clear();
        mainWindowScene.getStylesheets().add(LIGHT_THEME_STRING);
        this.theme = LIGHT_THEME_STRING;
    }

    /**
     * Sets the current theme to the dark theme.
     * Accessed by the "Dark Theme" tab in the Menu of Ui.
     */
    @FXML
    private void updateToDarkTheme() {
        mainWindowScene.getStylesheets().clear();
        mainWindowScene.getStylesheets().add((DARK_THEME_STRING));
        this.theme = DARK_THEME_STRING;
    }

    /**
     * Sets the current theme to the green theme.
     * Accessed by the "Green Theme" tab in the Menu of Ui.
     */
    @FXML
    private void updateToGreenTheme() {
        mainWindowScene.getStylesheets().clear();
        mainWindowScene.getStylesheets().add(GREEN_THEME_STRING);
        this.theme = GREEN_THEME_STRING;
    }

    /**
     * Sets the current theme to the pink theme.
     * Accessed by the "Pink Theme" tab in the Menu of Ui.
     */
    @FXML
    private void updateToPinkTheme() {
        mainWindowScene.getStylesheets().clear();
        mainWindowScene.getStylesheets().add(PINK_THEME_STRING);
        this.theme = PINK_THEME_STRING;
    }

    /**
     * Opens the help window depending on query commandType or focuses on it if it's already opened.
     */
    public void handleHelp(CommandResult.CommandType commandType) {
        if (commandType == null) {
            helpWindow.set();
        } else {
            helpWindow.set(commandType);
        }
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        helpWindow.set();
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }

    /**
     * Plays an opening animation when the application starts.
     */
    void show() {
        primaryStage.show();
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        FadeTransition transition = new FadeTransition(Duration.seconds(2));
        transition.setNode(animationPanel);
        transition.setFromValue(1.0f);
        transition.setToValue(0.0f);
        pause.setOnFinished(event -> {
            transition.play();
        });
        pause.play();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY(), this.theme);
        logic.setGuiSettings(guiSettings);
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        FadeTransition transition = new FadeTransition(Duration.seconds(1));
        transition.setNode(animationPanel);
        transition.setFromValue(0.0f);
        transition.setToValue(1.0f);
        welcomeMessage.setVisible(false);
        exitMessage.setVisible(true);
        transition.setOnFinished(event -> {
            pause.play();
        });
        transition.play();
        pause.setOnFinished(event -> {
            helpWindow.hide();
            primaryStage.hide();
        });
    }

    /**
     * Exports the address books to csv.
     */
    @FXML
    private void handleExport() {
        logic.export();
    }

    /**
     * Updates the description of the specified entity
     */
    private void updateDescription(ListType type, int index) {
        assert(type != ListType.TUITIONCLASS_LIST);
        switch(type) {
        case STUDENT_LIST:
            descriptionEntityType = type;
            studentListPanel.getStudentListView().getSelectionModel().clearSelection();
            studentListPanel.getStudentListView().getSelectionModel().select(index);
            descriptionEntityType = ListType.STUDENT_LIST;
            break;
        case TUTOR_LIST:
            descriptionEntityType = type;
            tutorListPanel.getTutorListView().getSelectionModel().clearSelection();
            tutorListPanel.getTutorListView().getSelectionModel().select(index);
            descriptionEntityType = ListType.TUTOR_LIST;
            break;
        default:
            break;
        }
    }

    /**
     * Updates the displayed list.
     */
    private void handleList() {
        ListType type = logic.getCurrentListType();
        int index;
        switch (type) {
        case STUDENT_LIST:
            index = studentListPanel.getStudentListView().getSelectionModel().getSelectedIndex();
            studentListPanel = new StudentListPanel(logic.getFilteredStudentList());
            setUpClickableCards();
            tutorListPanel.getTutorListView().getSelectionModel().clearSelection();
            entityListPanelPlaceholder.getChildren().clear();
            entityListPanelPlaceholder.getChildren().add(studentListPanel.getRoot());
            studentListPanel.getStudentListView().getSelectionModel().select(index);
            break;
        case TUTOR_LIST:
            index = tutorListPanel.getTutorListView().getSelectionModel().getSelectedIndex();
            tutorListPanel = new TutorListPanel(logic.getFilteredTutorList());
            setUpClickableCards();
            studentListPanel.getStudentListView().getSelectionModel().clearSelection();
            entityListPanelPlaceholder.getChildren().clear();
            entityListPanelPlaceholder.getChildren().add(tutorListPanel.getRoot());
            tutorListPanel.getTutorListView().getSelectionModel().select(index);
            break;
        case TUITIONCLASS_LIST:
            tuitionClassListPanel = new TuitionClassListPanel(logic.getFilteredTuitionClassList());
            entityListPanelPlaceholder.getChildren().clear();
            entityListPanelPlaceholder.getChildren().add(tuitionClassListPanel.getRoot());
            break;
        default:
            break;
        }
        setLabelStyle();
    }

    /**
     * Displays the added student in Description Panel if the
     * current list is the student list.
     */
    private void handleAddStudent() {
        ListType type = logic.getCurrentListType();
        int listSize;
        if (type == ListType.STUDENT_LIST) {
            entityDescriptionPlaceholder.getChildren().clear();
            listSize = logic.getFilteredStudentList().size();
            studentListPanel.getStudentListView().getSelectionModel().clearSelection();
            studentListPanel.getStudentListView().getSelectionModel().select(listSize - 1);
            descriptionEntityType = ListType.STUDENT_LIST;
        }
    }

    /**
     * Displays the added tutor in Description Panel if the
     * current list is the tutor list.
     */
    private void handleAddTutor() {
        ListType type = logic.getCurrentListType();
        int listSize;
        if (type == ListType.TUTOR_LIST) {
            entityDescriptionPlaceholder.getChildren().clear();
            listSize = logic.getFilteredTutorList().size();
            tutorListPanel.getTutorListView().getSelectionModel().clearSelection();
            tutorListPanel.getTutorListView().getSelectionModel().select(listSize - 1);
            descriptionEntityType = ListType.TUTOR_LIST;
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
            entityDescriptionPlaceholder.getChildren().add(NO_PERSON_DISPLAYED_LABEL);
        }
    }

    /**
     * Clears the current Description Panel if the displayed is the deleted entity
     */
    private void handleDelete(CommandResult commandResult) {
        ListType type = logic.getCurrentListType();
        switch (type) {
        case STUDENT_LIST:
            if (commandResult.getDeletedStudent().equals(studentDescription.getDisplayedStudent())) {
                entityDescriptionPlaceholder.getChildren().clear();
                entityDescriptionPlaceholder.getChildren().add(NO_PERSON_DISPLAYED_LABEL);
            }
            break;
        case TUTOR_LIST:
            if (commandResult.getDeletedTutor().equals(tutorDescription.getDisplayedTutor())) {
                entityDescriptionPlaceholder.getChildren().clear();
                entityDescriptionPlaceholder.getChildren().add(NO_PERSON_DISPLAYED_LABEL);
            }
            break;
        case TUITIONCLASS_LIST:
            if (descriptionEntityType == ListType.STUDENT_LIST) {
                int index = studentListPanel.getStudentListView().getSelectionModel().getSelectedIndex();
                updateDescription(ListType.STUDENT_LIST, index);
            } else {
                assert(descriptionEntityType == ListType.TUTOR_LIST);
                int index = tutorListPanel.getTutorListView().getSelectionModel().getSelectedIndex();
                updateDescription(ListType.TUTOR_LIST, index);
            }
            break;
        default:
            break;
        }
    }

    /**
     * Updates the description if the edited type is Student or Tutor.
     *  Updates the list view and the set the description to empty if
     *  the edited type is Class.
     */
    private void handleEdit(int index) {
        ListType type = logic.getCurrentListType();
        switch (type) {
        case STUDENT_LIST:
            updateDescription(ListType.STUDENT_LIST, index);
            break;
        case TUTOR_LIST:
            updateDescription(ListType.TUTOR_LIST, index);
            break;
        case TUITIONCLASS_LIST:

            if (descriptionEntityType == ListType.STUDENT_LIST) {
                index = studentListPanel.getStudentListView().getSelectionModel().getSelectedIndex();
                updateDescription(ListType.STUDENT_LIST, index);
            } else {
                assert(descriptionEntityType == ListType.TUTOR_LIST);
                index = tutorListPanel.getTutorListView().getSelectionModel().getSelectedIndex();
                updateDescription(ListType.TUTOR_LIST, index);
            }
            break;
        default:
            break;
        }
    }

    /**
     * Switches to the student list when the student tab is clicked
     */
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

    /**
     * Switches to the tutor list when the tutor tab is clicked
     */
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

    /**
     * Switches to the class list when the class tab is clicked
     */
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


    /**
     * Updates the Ui style of the tabs
     */
    private void setLabelStyle() {
        studentLabelPanel.getStyleClass().clear();
        tutorLabelPanel.getStyleClass().clear();
        tuitionClassLabelPanel.getStyleClass().clear();
        Model.ListType type = logic.getCurrentListType();
        switch (type) {
        case STUDENT_LIST:
            tutorLabelPanel.getStyleClass().add(UNSELECTED_LABEL_STYLE_CLASS);
            tuitionClassLabelPanel.getStyleClass().add(UNSELECTED_LABEL_STYLE_CLASS);
            studentLabelPanel.getStyleClass().add(SELECTED_LABEL_STYLE_CLASS);
            break;
        case TUTOR_LIST:
            studentLabelPanel.getStyleClass().add(UNSELECTED_LABEL_STYLE_CLASS);
            tuitionClassLabelPanel.getStyleClass().add(UNSELECTED_LABEL_STYLE_CLASS);
            tutorLabelPanel.getStyleClass().add(SELECTED_LABEL_STYLE_CLASS);
            break;
        case TUITIONCLASS_LIST:
            studentLabelPanel.getStyleClass().add(UNSELECTED_LABEL_STYLE_CLASS);
            tutorLabelPanel.getStyleClass().add(UNSELECTED_LABEL_STYLE_CLASS);
            tuitionClassLabelPanel.getStyleClass().add(SELECTED_LABEL_STYLE_CLASS);
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
    private void updateListView(ListType type) {
        switch(type) {
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
                handleHelp(commandResult.getQueryType());
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            if (commandResult.isList()) {
                handleList();
            }

            if (commandResult.isUpdateListView()) {
                updateListView(logic.getCurrentListType());
            }

            if (commandResult.isUpdateDescription()) {
                updateDescription(logic.getCurrentListType(), commandResult.getIndex());
            }

            if (commandResult.isClear()) {
                handleClear();
            }

            if (commandResult.isAddStudent()) {
                handleAddStudent();
            }

            if (commandResult.isAddTutor()) {
                handleAddTutor();
            }

            if (commandResult.isDelete()) {
                handleDelete(commandResult);
            }

            if (commandResult.isEdit()) {
                handleEdit(commandResult.getIndex());
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
