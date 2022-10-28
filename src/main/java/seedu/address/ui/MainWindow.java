package seedu.address.ui;

import java.util.Collections;
import java.util.Set;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.LockCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;
import seedu.address.model.portfolio.Note;
import seedu.address.model.portfolio.Plan;
import seedu.address.model.portfolio.Portfolio;

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
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private PortfolioWindow portfolioWindow;
    private LockWindow lockWindow;
    private int index = -1;
    private Set<Plan> emptyPlan = Collections.emptySet();
    private Set<Note> emptyNote = Collections.emptySet();

    @FXML
    private Scene parent;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private MenuBar menuBar;

    @FXML
    private HBox hbox;

    @FXML
    private StackPane personListPanelPlaceholder;

    @FXML
    private StackPane portfolioListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    @FXML
    private Label totalClient;

    @FXML
    private Button btnChangeTheme;

    @FXML
    private Button btnHide;

    @FXML
    private ImageView imageTheme;

    @FXML
    private ImageView imageHide;

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
        getTotalClient();
        helpWindow = new HelpWindow();
        lockWindow = new LockWindow(new Stage(), this);

        // Set the layout of menuBar
        HBox.setHgrow(menuBar, Priority.ALWAYS);
        HBox.setHgrow(btnChangeTheme, Priority.NEVER);

        Preferences pref = Preferences.userRoot().node(this.getClass().getName());
        initializeTheme(pref);
        // Toggle the theme with each mouse click on button
        btnChangeTheme.setOnMouseClicked(event -> {
            int mode = pref.getInt("mode", 0);
            if (mode == 0) { //dark change to light
                setLightTheme(pref);
            } else { //light change to dark
                setDarkTheme(pref);
            }
        });
        btnHide.setOnMouseClicked(event -> {
            int hidden = pref.getInt("hidden", 0);
            if (hidden == 0) { //hide sensitive data
                hide(pref);
            } else { //show sensitive data
                show(pref);
            }
        });
    }

    /**
     * Sets FinBook to light mode if user set his/her preference as light mode (mode == 1).
     * FinBook's default theme is dark mode. (mode == 0)
     *
     * @param pref Stored preference of application theme.
     */
    void initializeTheme(Preferences pref) {
        int mode = pref.getInt("mode", 0);
        if (mode == 1) {
            setLightTheme(pref);
        }
        int hidden = pref.getInt("hidden", 0);
        if (hidden == 1) {
            show(pref);
        }
    }

    /**
     * Sets FinBook UI to light mode by changing MainWindow, HelpWindow and LockWindow stylesheet to their
     * respective light stylesheet and sets the button to sun icon.
     *
     * @param pref Stored preference of application theme.
     */
    void setLightTheme(Preferences pref) {
        pref.putInt("mode", 1);
        parent.getStylesheets().add("view/styles/MainWindowLight.css");
        parent.getStylesheets().remove("view/styles/MainWindowDark.css");
        imageTheme.setImage(new Image("images/sun.png"));
        helpWindow.setLightTheme();
        lockWindow.setLightTheme();
    }

    /**
     * Sets FinBook UI to dark mode by changing MainWindow, HelpWindow and LockWindow stylesheet to their
     * respective dark stylesheet and sets the button to moon icon.
     *
     * @param pref Stored preference of application theme.
     */
    void setDarkTheme(Preferences pref) {
        pref.putInt("mode", 0);
        parent.getStylesheets().add("view/styles/MainWindowDark.css");
        parent.getStylesheets().remove("view/styles/MainWindowLight.css");
        imageTheme.setImage(new Image("images/moon.png"));
        helpWindow.setDarkTheme();
        lockWindow.setDarkTheme();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public Logic getLogic() {
        return logic;
    }

    public void getTotalClient() {
        totalClient.setText(String.valueOf(logic.getFilteredPersonList().size()));
    }

    /**
     * Updates the portfolio page after each view command
     */
    public void getPortfolio() {
        Person person;
        Portfolio portfolio;
        if (index == -1) {
            portfolio = null;
        } else {
            person = logic.getFilteredPersonList().get(index);
            portfolio = person.getPortfolio();
        }
        portfolioWindow = new PortfolioWindow(portfolio);
        portfolioListPanelPlaceholder.getChildren().clear();
        portfolioListPanelPlaceholder.getChildren().add(portfolioWindow.getRoot());
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
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());

        portfolioWindow = new PortfolioWindow(null);
        portfolioListPanelPlaceholder.getChildren().add(portfolioWindow.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath());
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
        primaryStage.setResizable(true);
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
        if (logic.isPasswordSet()) {
            lockWindow.show();
        } else {
            primaryStage.show();
        }
    }

    /**
     * Shows FinBook data by removing blurring of portfolioWindow and PersonListPanel data.
     * @param pref Stored preference of hidden attribute.
     */
    void show(Preferences pref) {
        pref.putInt("hidden", 0);
        imageHide.setImage(new Image("images/open_eye.png"));
        personListPanel = new PersonListPanel(logic.getFilteredPersonList(), false);
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());
    }

    /**
     * Hides FinBook data by censoring portfolioWindow and PersonListPanel data.
     * @param pref Stored preference of hidden attribute.
     */
    void hide(Preferences pref) {
        pref.putInt("hidden", 1);
        imageHide.setImage(new Image("images/close_eye.png"));
        personListPanel = new PersonListPanel(logic.getFilteredPersonList(), true);
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());
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
     * Locks the application.
     */
    @FXML
    public void handleLock() {
        helpWindow.hide();
        primaryStage.hide();
        resultDisplay.setFeedbackToUser(LockCommand.SHOWING_UNLOCK_MESSAGE);
        lockWindow.show();
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

            if (commandResult.isLock()) {
                handleLock();
            }
            index = commandResult.getIndex();
            getPortfolio();
            getTotalClient();
            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }

    }
}
