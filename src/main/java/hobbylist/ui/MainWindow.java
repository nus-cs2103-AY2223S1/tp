package hobbylist.ui;

import java.util.logging.Logger;

import hobbylist.commons.core.GuiSettings;
import hobbylist.commons.core.LogsCenter;
import hobbylist.commons.core.ThemeSettings;
import hobbylist.logic.Logic;
import hobbylist.logic.commands.CommandResult;
import hobbylist.logic.commands.exceptions.CommandException;
import hobbylist.logic.parser.exceptions.ParseException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private ThemeSettings.Theme theme = ThemeSettings.Theme.DARK;

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private ActivityListPanel activityListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private EditAliasesWindow editAliasesWindow;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private MenuItem editAliasesItem;

    @FXML
    private StackPane activityListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;
    @FXML
    private ImageView img;

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
        ThemeSettings.Theme theme = logic.getThemeSettings().getTheme();
        switch (theme) {
        case SKY:
            handleSky();
            break;
        case STAR:
            handleStar();
            break;
        case LIGHT:
            handleLight();
            break;
        case TREE:
            handleTree();
            break;
        default:
            handleDark();
        }

        setAccelerators();

        helpWindow = new HelpWindow();

        editAliasesWindow = new EditAliasesWindow(logic);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
        setAccelerator(editAliasesItem, KeyCombination.valueOf("F2"));
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

        activityListPanel = new ActivityListPanel(logic.getFilteredActivityList(), logic.getSelectedActivity());
        activityListPanelPlaceholder.getChildren().add(activityListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getHobbyListFilePath());
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
        primaryStage.setResizable(false);
        primaryStage.getScene().getStylesheets().add("view/StarTheme.css");
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
     * Change primaryStage to star theme.
     */
    @FXML
    public void handleStar() {
        primaryStage.getScene().getStylesheets()
                .removeAll("view/SkyTheme.css", "view/DarkTheme.css",
                        "view/LightTheme.css", "view/StarTheme.css", "view/TreeTheme.css");
        primaryStage.getScene().getStylesheets().add("view/StarTheme.css");
        theme = ThemeSettings.Theme.STAR;
    }
    /**
     * Change primaryStage to dark theme.
     */
    @FXML
    public void handleDark() {
        primaryStage.getScene().getStylesheets()
                .removeAll("view/StarTheme.css", "view/SkyTheme.css",
                        "view/LightTheme.css", "view/DarkTheme.css", "view/TreeTheme.css");
        primaryStage.getScene().getStylesheets().add("view/DarkTheme.css");
        theme = ThemeSettings.Theme.DARK;
    }
    /**
     * Change primaryStage to sky theme.
     */
    @FXML
    public void handleSky() {
        primaryStage.getScene().getStylesheets()
                .removeAll("view/StarTheme.css", "view/DarkTheme.css",
                        "view/LightTheme.css", "view/SkyTheme.css", "view/TreeTheme.css");
        primaryStage.getScene().getStylesheets().add("view/SkyTheme.css");
        theme = ThemeSettings.Theme.SKY;
    }
    /**
     * Change primaryStage to light theme.
     */
    @FXML
    public void handleLight() {
        primaryStage.getScene().getStylesheets()
                .removeAll("view/StarTheme.css", "view/DarkTheme.css",
                        "view/SkyTheme.css", "view/LightTheme.css", "view/TreeTheme.css");
        primaryStage.getScene().getStylesheets().add("view/LightTheme.css");
        theme = ThemeSettings.Theme.LIGHT;
    }
    /**
     * Change primaryStage to tree theme.
     */
    @FXML
    public void handleTree() {
        primaryStage.getScene().getStylesheets()
                .removeAll("view/StarTheme.css", "view/DarkTheme.css",
                        "view/SkyTheme.css", "view/LightTheme.css", "view/TreeTheme.css");
        primaryStage.getScene().getStylesheets().add("view/TreeTheme.css");
        theme = ThemeSettings.Theme.TREE;
    }

    /**
     * Opens the edit aliases window or focuses on it if it's already opened.
     */
    @FXML
    public void handleEditAliases() {
        if (!editAliasesWindow.isShowing()) {
            editAliasesWindow.show();
        } else {
            editAliasesWindow.focus();
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
        ThemeSettings themeSettings = new ThemeSettings(theme);
        logic.setThemeSettings(themeSettings);
        helpWindow.hide();
        editAliasesWindow.hide();
        primaryStage.hide();
    }

    public ActivityListPanel getActivityListPanel() {
        return activityListPanel;
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
