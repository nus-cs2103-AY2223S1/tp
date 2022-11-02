package seedu.travelr.ui;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.travelr.commons.core.GuiSettings;
import seedu.travelr.commons.core.LogsCenter;
import seedu.travelr.logic.Logic;
import seedu.travelr.logic.commands.CommandResult;
import seedu.travelr.logic.commands.exceptions.CommandException;
import seedu.travelr.logic.parser.exceptions.ParseException;

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
    private TripListPanel tripListPanel;
    private EventListPanel eventListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private SummaryWindow summaryWindow;

    private Image completed = new Image("/images/completed.png");
    private Image tripsIcon = new Image("/images/trips.png");
    private Image eventsIcon = new Image("/images/events.png");

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private MenuItem summaryMenuItem;

    @FXML
    private StackPane tripsTextField;

    @FXML
    private StackPane eventsTextField;

    // TODO: Refactor
    @FXML
    private StackPane tripListPanelPlaceholder;

    @FXML
    private StackPane eventListPanelPlaceholder;

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
        summaryWindow = new SummaryWindow();
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
        tripListPanel = new TripListPanel(logic.getFilteredTripList(), logic.getSelectedTrip(), completed);
        eventListPanel = new EventListPanel(logic.getFilteredEventList());
        tripListPanelPlaceholder.getChildren().add(tripListPanel.getRoot());
        eventListPanelPlaceholder.getChildren().add(eventListPanel.getRoot());
        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        resultDisplay.setFeedbackToUser("Currently displaying all trips, as well as events in the bucketlist.");

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getTravelrFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        TripsLabeler tripsLabel = new TripsLabeler(tripsIcon);
        tripsTextField.getChildren().add(tripsLabel.getRoot());

        EventsLabeler eventsLabel = new EventsLabeler(eventsIcon);
        eventsTextField.getChildren().add(eventsLabel.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());

        summaryWindow.init(logic.getFilteredTripList(), logic.getSummaryVariables(), completed);
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
     * Opens the summary window or focuses on it if it's already opened.
     */
    @FXML
    public void handleSummary() {
        if (!summaryWindow.isShowing()) {
            summaryWindow.show();
        } else {
            summaryWindow.focus();
        }
    }

    /**
     * Opens and refreshes the summary window or focuses on it if it's already opened.
     */
    @FXML
    public void handleAndRefreshSummary() {
        logic.refreshSummaryVariables();
        handleSummary();
    }

    public void exitSummary() {
        summaryWindow.hide();
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
        summaryWindow.hide();
        helpWindow.hide();
        primaryStage.hide();
    }

    /**
     * Executes the command and returns the result.
     *
     * @see Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isShowHelp()) {
                handleHelp();
            } else {
                exitSummary();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            if (commandResult.isShowSummary()) {
                handleSummary();
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
