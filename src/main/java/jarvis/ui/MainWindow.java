package jarvis.ui;

import java.nio.file.Paths;
import java.util.logging.Logger;

import jarvis.commons.core.GuiSettings;
import jarvis.commons.core.LogsCenter;
import jarvis.logic.Logic;
import jarvis.logic.commands.CommandResult;
import jarvis.logic.commands.exceptions.CommandException;
import jarvis.logic.parser.exceptions.ParseException;
import jarvis.model.Lesson;
import jarvis.model.Student;
import jarvis.model.Task;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
    private StudentListPanel studentListPanel;
    private TaskListPanel taskListPanel;
    private LessonListPanel lessonListPanel;
    private ExpandedStudentListPanel expStudentListPanel;
    private ExpandedTaskListPanel expTaskListPanel;
    private ExpandedLessonListPanel expLessonListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane studentListPanelPlaceholder;

    @FXML
    private StackPane taskListPanelPlaceholder;

    @FXML
    private StackPane lessonListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private SplitPane defaultList;

    @FXML
    private VBox expandedStudentList;

    @FXML
    private VBox expandedTaskList;

    @FXML
    private VBox expandedLessonList;

    @FXML
    private StackPane expandedStudentListPanelPlaceholder;

    @FXML
    private StackPane expandedTaskListPanelPlaceholder;

    @FXML
    private StackPane expandedLessonListPanelPlaceholder;

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
        expandedStudentList.setVisible(false);
        expandedTaskList.setVisible(false);
        expandedLessonList.setVisible(false);

        ObservableList<Student> filteredStudentList = logic.getFilteredStudentList();
        ObservableList<Task> filteredTaskList = logic.getFilteredTaskList();
        ObservableList<Lesson> filteredLessonList = logic.getFilteredLessonList();

        studentListPanel = new StudentListPanel(filteredStudentList);
        taskListPanel = new TaskListPanel(filteredTaskList);
        lessonListPanel = new LessonListPanel(filteredLessonList);
        expStudentListPanel = new ExpandedStudentListPanel(filteredStudentList);
        expTaskListPanel = new ExpandedTaskListPanel(filteredTaskList);
        expLessonListPanel = new ExpandedLessonListPanel(filteredLessonList);

        studentListPanelPlaceholder.getChildren().add(studentListPanel.getRoot());
        taskListPanelPlaceholder.getChildren().add(taskListPanel.getRoot());
        lessonListPanelPlaceholder.getChildren().add(lessonListPanel.getRoot());
        expandedStudentListPanelPlaceholder.getChildren().add(expStudentListPanel.getRoot());
        expandedTaskListPanelPlaceholder.getChildren().add(expTaskListPanel.getRoot());
        expandedLessonListPanelPlaceholder.getChildren().add(expLessonListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        String studentBookPath = Paths.get(".").resolve(logic.getStudentBookFilePath()).toString();
        String taskBookPath = Paths.get(".").resolve(logic.getTaskBookFilePath()).toString();
        String lessonBookPath = Paths.get(".").resolve(logic.getLessonBookFilePath()).toString();
        StatusBarFooter statusBarFooter = new StatusBarFooter(studentBookPath + " and " + taskBookPath
                + " and " + lessonBookPath);
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
     * Displays the default list or an expanded student/task/lesson list, depending on the command result.
     */
    @FXML
    public void handleList(CommandResult commandResult) {
        hideAllList();
        DisplayedList displayedList = commandResult.getDisplayedList();
        if (displayedList == DisplayedList.DEFAULT_LIST) {
            defaultList.setVisible(true);
        } else if (displayedList == DisplayedList.EXP_STUDENT_LIST) {
            expandedStudentList.setVisible(true);
        } else if (displayedList == DisplayedList.EXP_TASK_LIST) {
            expandedTaskList.setVisible(true);
        } else if (displayedList == DisplayedList.EXP_LESSON_LIST) {
            expandedLessonList.setVisible(true);
        }
    }

    /**
     * Helper function to hide all lists.
     */
    public void hideAllList() {
        defaultList.setVisible(false);
        expandedStudentList.setVisible(false);
        expandedTaskList.setVisible(false);
        expandedLessonList.setVisible(false);
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
     * Executes the command and returns the result.
     *
     * @see jarvis.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());


            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isList()) {
                handleList(commandResult);
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
