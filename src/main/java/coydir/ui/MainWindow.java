package coydir.ui;

import java.util.logging.Logger;

import coydir.commons.core.GuiSettings;
import coydir.commons.core.LogsCenter;
import coydir.logic.Logic;
import coydir.logic.commands.CommandResult;
import coydir.logic.commands.exceptions.CommandException;
import coydir.logic.parser.exceptions.ParseException;
import coydir.model.person.EmployeeId;
import coydir.model.person.Person;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
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
    private HomePanel homePanel;
    private PersonListPanel personListPanel;
    private ResultDisplay resultDisplay;

    private DepartmentInfo departmentInfo;
    private HelpWindow helpWindow;
    private PersonInfo personInfo;

    private int currentIndex;
    private EmployeeId currentEmployee;

    private String currentDepartment;

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
    private StackPane sidePanelPlaceholder;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.primaryStage.setWidth(1100);
        this.primaryStage.setHeight(850);
        this.primaryStage.setMinWidth(1100);
        this.primaryStage.setMinHeight(850);
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
        personListPanel = new PersonListPanel(logic.getFilteredPersonList());
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());

        // init personInfo component but do not display
        personInfo = new PersonInfo();

        // init homePanel component
        homePanel = new HomePanel();

        // set side panel to home panel
        sidePanelPlaceholder.getChildren().add(homePanel.getRoot());

        departmentInfo = new DepartmentInfo(logic.getUnfilteredPersonList());
        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getDatabaseFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
        commandBox.focus();

        personListPanel.getPersonListView().setOnMouseClicked((new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                handleViewPerson(personListPanel.getPersonListView().getSelectionModel().getSelectedIndex());
            }
        }));
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
     * Switches to Dark Theme if the window is currently on Light Theme.
     * Remains on Dark Theme otherwise.
     */
    @FXML
    public void handleDarkTheme() {
        getPrimaryStage().getScene().getStylesheets().clear();
        getPrimaryStage().getScene().getStylesheets().addAll(
                getClass().getClassLoader().getResource("view/DarkTheme.css").toExternalForm(),
                getClass().getClassLoader().getResource("view/Extensions.css").toExternalForm());
    }

    /**
     * Switches to Light Theme if the window is currently on Dark Theme.
     * Remains on Light Theme otherwise.
     */
    @FXML
    public void handleLightTheme() {
        getPrimaryStage().getScene().getStylesheets().clear();
        getPrimaryStage().getScene().getStylesheets().addAll(
                getClass().getClassLoader().getResource("view/LightTheme.css").toExternalForm(),
                getClass().getClassLoader().getResource("view/Extensions.css").toExternalForm());
    }

    void show() {
        primaryStage.show();
    }

    private void setSidePanel(UiPart<Region> panel) {
        sidePanelPlaceholder.getChildren().clear();
        sidePanelPlaceholder.getChildren().add(panel.getRoot());
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

    private void handlePersonInfoUpdate(int index) {
        ObservableList<Person> personList = logic.getFilteredPersonList();
        if (index >= personList.size() || index < 0) {
            setSidePanel(homePanel);
        } else {
            personInfo.update(logic.getFilteredPersonList().get(index));
        }
        currentIndex = index;
    }

    private void handleViewPerson(int index) {
        setSidePanel(personInfo);
        handlePersonInfoUpdate(index);
    }

    private void handleViewDepartmentUpdate(String department) {
        departmentInfo.update(logic.getUnfilteredPersonList(), department);
    }

    private void handleViewDepartment(String department) {
        departmentInfo.update(logic.getUnfilteredPersonList(), department);
        setSidePanel(departmentInfo);
    }

    public PersonListPanel getPersonListPanel() {
        return personListPanel;
    }

    /**
     * Executes the command and returns the result.
     *
     * @see coydir.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());
            handlePersonInfoUpdate(currentIndex);
            handleViewDepartmentUpdate(currentDepartment);

            if (commandResult.isShowHelp()) {
                handleHelp();
            } else if (commandResult.isExit()) {
                handleExit();
            } else if (commandResult.isViewPerson()) {
                int viewIndex = commandResult.getViewIndex();
                handleViewPerson(viewIndex);
            } else if (commandResult.isViewDepartment()) {
                currentDepartment = commandResult.getDepartment();
                handleViewDepartment(commandResult.getDepartment());
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
