package seedu.address.ui;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;

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
    private WindowAnchorPane windowAnchorPane;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    @FXML
    private AnchorPane windowAnchorPaneHolder;

    private final Scene scene;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        scene = primaryStage.getScene();

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        // No more menu bar
        //setAccelerators();

        helpWindow = new HelpWindow();

        scene.setOnMouseClicked(e -> resultDisplayPlaceholder.requestFocus());
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
        windowAnchorPane = new WindowAnchorPane(logic);
        windowAnchorPane.fillInnerParts();
        windowAnchorPaneHolder.getChildren().add(windowAnchorPane.getRoot());
        windowAnchorPane.resizeElements(primaryStage.getHeight(),
                primaryStage.getWidth());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        // we removed the status bar
        // StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath());
        // statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBox.bindResultsDisplay(resultDisplayPlaceholder);

        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
        resultDisplayPlaceholder.prefWidthProperty().bind(scene.widthProperty());

        scene.addEventFilter(KeyEvent.KEY_PRESSED, t -> {
            if (t.getCode() == KeyCode.SPACE && !commandBox.getCommandTextField().isFocused()) {
                commandBox.getCommandTextField().setEditable(false);
                commandBox.getCommandTextField().requestFocus();
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        commandBox.getCommandTextField().setEditable(true);
                        commandBox.getCommandTextField().selectEnd();
                    }
                }, 10);
            }
        });

        scene.heightProperty().addListener((ob, oldVal, newVal) ->
                windowAnchorPane.resizeElements(scene.getHeight(),
                        scene.getWidth())
        );
        scene.widthProperty().addListener((ob, oldVal, newVal) ->
                windowAnchorPane.resizeElements(scene.getHeight(), scene.getWidth())
        );

        windowAnchorPane.resizeElements(scene.getHeight(), scene.getWidth());
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

    private void handleInspect(String[] inspectingName) {
        if (inspectingName == null || inspectingName.length == 0) {
            resultDisplay.setFeedbackToUser("There was nothing given to inspect");
            return;
        }

        ListView<Person> personListView = getPersonListPanel().getListView();

        try {
            int index = Integer.parseInt(inspectingName[0]) - 1;
            if (index < 0 || index >= personListView.getItems().size()) {
                resultDisplay.setFeedbackToUser(Messages.OUT_OF_BOUNDS);
                return;
            }

            personListView.getSelectionModel().clearSelection();
            personListView.getSelectionModel().select(index);
            return;
        } catch (NumberFormatException e) {
            logger.info(Messages.NOT_AN_INTEGER);
        }

        Person[] personsArray = personListView.getItems()
                .stream().filter(x -> matches(x.getName().fullName, inspectingName))
                .toArray(Person[]::new);

        if (personsArray.length == 0) {
            resultDisplay.setFeedbackToUser(String.format(Messages.MESSAGE_INVALID_NAME_INSPECT,
                    Arrays.stream(inspectingName).map(n -> "\"" + n + "\"")
                            .collect(Collectors.joining(" or "))));
            return;
        }

        if (personsArray.length > 1) {
            resultDisplay.setFeedbackToUser(Messages.AMBIGUOUS_NAME_INSPECT_FIRST);
        }

        int index = personListView.getItems().indexOf(personsArray[0]);
        personListView.getSelectionModel().clearSelection();
        personListView.getSelectionModel().select(index);
    }

    private void handleShowNotePanel(boolean isVisible) {
        windowAnchorPane.setNotesPaneVisibility(isVisible, scene.getHeight(), scene.getWidth());
    }

    private boolean matches(String currentName, String[] matching) {
        for (String word : matching) {
            if (StringUtil.containsWordIgnoreCase(currentName, word)) {
                return true;
            }
        }
        return false;
    }

    public PersonListPanel getPersonListPanel() {
        return windowAnchorPane.getPersonListPanel();
    }

    public NoteListPanel getNoteListPanel() {
        return windowAnchorPane.getNoteListPanel();
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

            switch (commandResult.getUiState()) {
            case ShowHelp:
                handleHelp();
                break;

            case Exit:
                handleExit();
                break;

            case Inspect:
                handleInspect(commandResult.getArgs());
                break;

            case HideNotes:
                handleShowNotePanel(false);
                break;

            case ShowNotes:
                handleShowNotePanel(true);
                break;

            default:
                break;
            }

            getPersonListPanel().setFilteredBoxIcon(
                    logic.getAddressBook().getPersonList().size() != logic.getFilteredPersonList().size());

            getNoteListPanel().setFilteredBoxIcon(
                    logic.getAddressBook().getNoteBook().size() != logic.getFilteredNoteList().size());

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
