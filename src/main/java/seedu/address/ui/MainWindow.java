package seedu.address.ui;

import static seedu.address.ui.theme.Theme.constructTheme;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.ui.schedule.ScheduleGridPanel;
import seedu.address.ui.schedule.ScheduleListPanel;
import seedu.address.ui.theme.Theme;
import seedu.address.ui.theme.ThemeException;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final int STUDENTLIST = 1;
    private static final int MODULELIST = 2;
    private static final int MODULE = 3;
    private static final int SCHEDULE = 4;
    private static final int TIMETABLE = 0;
    private static final String FXML = "MainWindow.fxml";
    private int timetableModel = 0;
    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private PersonListPanel personListPanel;
    private TutorListPanel allTutorsPanel;
    private TutorListPanel tutorListPanel;
    private ModuleListPanel moduleListPanel;
    private ModuleInfoPanel moduleInfoPanel;
    private ModulePanel modulePanel;
    private ScheduleListPanel scheduleListPanel;
    private ScheduleGridPanel scheduleGridPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private Theme theme;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane personListPanelPlaceholder;

    @FXML
    private StackPane allTutorsPanelPlaceholder;

    @FXML
    private StackPane tutorListPanelPlaceholder;

    @FXML
    private StackPane moduleListPanelPlaceholder;

    @FXML
    private StackPane moduleInfoPanelPlaceholder;

    @FXML
    private StackPane modulePanelPlaceholder;

    @FXML
    private StackPane scheduleListPanelPlaceholder;

    @FXML
    private StackPane scheduleGridPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    @FXML
    private TabPane tabPane;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        GuiSettings guiSettings = logic.getGuiSettings();
        setWindowDefaultSize(guiSettings);
        applyTheme(constructTheme(guiSettings.getTheme()));

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
        personListPanel = new PersonListPanel(logic.getFilteredPersonList());
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());

        allTutorsPanel = new TutorListPanel(logic.getFilteredTutorList());
        allTutorsPanelPlaceholder.getChildren().add(allTutorsPanel.getRoot());

        moduleListPanel = new ModuleListPanel(logic.getFilteredModuleList(), logic.getFilteredScheduleList());

        moduleListPanelPlaceholder.getChildren().add(moduleListPanel.getRoot());

        scheduleListPanel = new ScheduleListPanel(logic.getFilteredScheduleList());
        scheduleListPanelPlaceholder.getChildren().add(scheduleListPanel.getRoot());

        scheduleGridPanel = new ScheduleGridPanel(logic.getAllScheduleList());
        scheduleGridPanel.constructHorizontalTimetable();
        scheduleGridPanelPlaceholder.getChildren().add(scheduleGridPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getProfNusFilePath());
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

    /**
     * Switch to students page.
     */
    @FXML
    public void handleShowTabStudents() throws CommandException, ParseException {
        logic.execute("list");
        personListPanel = new PersonListPanel(logic.getAllPersonList());
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());
        allTutorsPanel = new TutorListPanel(logic.getAllTutorList());
        allTutorsPanelPlaceholder.getChildren().add(allTutorsPanel.getRoot());
        tabPane.getSelectionModel().select(STUDENTLIST);
    }


    /**
     * Switch to students page with all students.
     */
    @FXML
    public void handleShowTabAllStudents() throws CommandException, ParseException {
        logic.execute("list");
        personListPanel = new PersonListPanel(logic.getAllPersonList());
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());
        allTutorsPanel = new TutorListPanel(logic.getAllTutorList());
        allTutorsPanelPlaceholder.getChildren().add(allTutorsPanel.getRoot());
        tabPane.getSelectionModel().select(STUDENTLIST);
        resultDisplay.setFeedbackToUser("Listed all persons");
    }

    /**
     * Switch to modules page.
     */
    @FXML
    public void handleShowTabModules() {
        moduleListPanel = new ModuleListPanel(logic.getAllModuleList(), logic.getAllScheduleList());
        moduleListPanelPlaceholder.getChildren().add(moduleListPanel.getRoot());
        if (!logic.getFilteredModuleList().equals(logic.getAllModuleList())) {
            moduleListPanel.getTargetModuleList().setItems(logic.getFilteredModuleList());
            moduleListPanel.getTargetModuleList().setCellFactory(listView ->
                    new ModulePanel.ModuleViewCell());
            moduleListPanel.getScheduleList().setItems(logic.getFilteredScheduleList());
            moduleListPanel.getScheduleList().setCellFactory(listView ->
                    new ScheduleListPanel.ScheduleListViewCell());
        }
        tabPane.getSelectionModel().select(MODULELIST);
        resultDisplay.setFeedbackToUser("Selected a module!");
    }

    /**
     * Switch to modules page with all modules shown.
     */
    @FXML
    public void handleShowTabAllModules() {
        moduleListPanel = new ModuleListPanel(logic.getAllModuleList(), logic.getFilteredScheduleList());
        moduleListPanelPlaceholder.getChildren().add(moduleListPanel.getRoot());
        tabPane.getSelectionModel().select(MODULELIST);
        resultDisplay.setFeedbackToUser("Listed all modules");
    }


    /**
     * Switch to module information page.
     */
    @FXML
    public void handleShowTabModule() {
        moduleInfoPanel = new ModuleInfoPanel(logic.getFilteredPersonList());
        moduleInfoPanelPlaceholder.getChildren().add(moduleInfoPanel.getRoot());
        tutorListPanel = new TutorListPanel(logic.getFilteredTutorList());
        tutorListPanelPlaceholder.getChildren().add(tutorListPanel.getRoot());
        tabPane.getSelectionModel().select(MODULE);
    }



    /**
     * Switch to Timetable page with all schedules.
     */
    @FXML
    public void handleShowTabScheduleGrid() {
        scheduleGridPanel = new ScheduleGridPanel(logic.getAllScheduleList());
        tabPane.getSelectionModel().select(TIMETABLE);
        if (timetableModel == 0) { // horizontal Timetable
            scheduleGridPanel.constructHorizontalTimetable();
            scheduleGridPanelPlaceholder.getChildren().add(scheduleGridPanel.getRoot());
            resultDisplay.setFeedbackToUser("Show the Horizontal Timetable!");
        } else if (timetableModel == 1) { // vertical Timetable
            scheduleGridPanel.constructVerticalTimetable();
            scheduleGridPanelPlaceholder.getChildren().add(scheduleGridPanel.getRoot());
            resultDisplay.setFeedbackToUser("Show the Vertical Timetable!");
        } else {
            resultDisplay.setFeedbackToUser("Something wrong....");
        }
        scheduleGridPanel.setScrollPaneStyle(theme);
    }


    /**
     * Switch to Schedules page with current filtered schedules.
     */
    @FXML
    public void handleShowTabSchedule() {
        scheduleListPanel = new ScheduleListPanel(logic.getFilteredScheduleList());
        scheduleListPanelPlaceholder.getChildren().add(scheduleListPanel.getRoot());
        tabPane.getSelectionModel().select(SCHEDULE);
    }

    /**
     * Switch to Schedules page with all schedules shown.
     */
    @FXML
    public void handleShowTabAllSchedules() {
        scheduleListPanel = new ScheduleListPanel(logic.getAllScheduleList());
        scheduleListPanelPlaceholder.getChildren().add(scheduleListPanel.getRoot());
        tabPane.getSelectionModel().select(SCHEDULE);
        resultDisplay.setFeedbackToUser("Show all schedules!");
    }

    void show() {
        primaryStage.show();
    }

    private void applyTheme(Theme newTheme) {
        ObservableList<String> uiTheme = primaryStage.getScene().getStylesheets();
        uiTheme.clear();
        try {
            String switchedTheme = newTheme.getTheme();
            uiTheme.add(switchedTheme);
            uiTheme.add(Theme.EXTENSION.getTheme());
            theme = newTheme;
            logger.info(String.format(Theme.SUCCESS_MESSAGE, theme));
        } catch (ThemeException e) {
            logger.info(e.getMessage());
        }
    }

    /** Sets theme to Light Theme. */
    @FXML
    public void applyLightTheme() {
        applyTheme(Theme.LIGHT);
        scheduleGridPanel.setScrollPaneStyle(theme);
        resultDisplay.setFeedbackToUser("Switched to light mode!");
    }

    /** Sets theme to Dark Theme. */
    @FXML
    public void applyDarkTheme() {
        applyTheme(Theme.DARK);
        scheduleGridPanel.setScrollPaneStyle(theme);
        resultDisplay.setFeedbackToUser("Switched to dark mode!");
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY(), theme.toString());
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

            if (commandResult.isShowModuleList()) {
                handleShowTabAllModules();
            }

            if (commandResult.isShowStudentList()) {
                handleShowTabStudents();
            }

            if (commandResult.isShowTargetModule()) {
                handleShowTabModules();
            }

            if (commandResult.isShowModule()) {
                handleShowTabModule();
            }

            if (commandResult.isShowScheduleList()) {
                handleShowTabSchedule();
            }
            if (commandResult.isShowHorizontalTimeTable()) {
                timetableModel = 0;
                handleShowTabScheduleGrid();
            }
            if (commandResult.isShowVerticalTimeTable()) {
                timetableModel = 1;
                handleShowTabScheduleGrid();
            }

            if (commandResult.isShowLight()) {
                applyLightTheme();
            }

            if (commandResult.isShowDark()) {
                applyDarkTheme();
            }

            return commandResult;

        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
