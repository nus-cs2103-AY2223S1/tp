package seedu.address.ui;

import static seedu.address.logic.commands.ThemeCommand.CHANGE_THEME_ERROR;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Themes.Theme;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";
    private static final String COMPACT_MENUITEM_TEXT = "Compacted Mode";
    private static final String EXPAND_MENUITEM_TEXT = "Expanded Mode";
    private static final String LIGHT_THEME_MENUITEM_TEXT = "Light Theme";
    private static final String DARK_THEME_MENUITEM_TEXT = "Dark Theme";
    private static final String ADD_COMMAND_SHORTCUT_TEXT = "add n/ p/ e/ a/ g/ b/ ra/ re/ s/ t/";
    private static final String EDIT_COMMAND_SHORTCUT_TEXT = "edit ";
    private static final String DELETE_COMMAND_SHORTCUT_TEXT = "delete ";
    private static final String CLONE_COMMAND_SHORTCUT_TEXT = "clone ";
    private static final String VIEW_COMMAND_SHORTCUT_TEXT = "view ";
    private static final String UNDO_COMMAND_SHORTCUT_TEXT = "undo ";
    private static final String MARK_COMMAND_SHORTCUT_TEXT = "mark ";
    private static final String UNMARK_COMMAND_SHORTCUT_TEXT = "unmark ";
    private static final String EMPTY_COMMAND_SHORTCUT_TEXT = "";



    private final String lightTheme = getClass().getResource("/view/LightTheme.css").toExternalForm();
    private final String darkTheme = getClass().getResource("/view/DarkTheme.css").toExternalForm();
    private final String extensionsLight = getClass().getResource("/view/ExtensionsLight.css").toExternalForm();
    private final String extensionsDark = getClass().getResource("/view/ExtensionsDark.css").toExternalForm();
    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private PersonListPanel personListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private HelpWindowForCommand helpWindowForCommand;

    private boolean isExpanded;
    private Theme currentTheme;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem lightDarkThemeItem;

    @FXML
    private MenuItem compactExpandItem;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane personListPanelPlaceholder;

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
        helpWindowForCommand = new HelpWindowForCommand();

        isExpanded = false;
        compactExpandItem.setText(EXPAND_MENUITEM_TEXT);
        currentTheme = Theme.LIGHT;
        lightDarkThemeItem.setText(DARK_THEME_MENUITEM_TEXT);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
        setCommandBoxText(ADD_COMMAND_SHORTCUT_TEXT, KeyCombination.valueOf("Shortcut+N"));
        setCommandBoxText(EDIT_COMMAND_SHORTCUT_TEXT, KeyCombination.valueOf("Shortcut+E"));
        setCommandBoxText(DELETE_COMMAND_SHORTCUT_TEXT, KeyCombination.valueOf("Shortcut+D"));
        setCommandBoxText(CLONE_COMMAND_SHORTCUT_TEXT, KeyCombination.valueOf("Shortcut+L"));
        setCommandBoxText(VIEW_COMMAND_SHORTCUT_TEXT, KeyCombination.valueOf("Shortcut+I"));
        setCommandBoxText(UNDO_COMMAND_SHORTCUT_TEXT, KeyCombination.valueOf("Shortcut+Z"));
        setCommandBoxText(MARK_COMMAND_SHORTCUT_TEXT, KeyCombination.valueOf("Shortcut+M"));
        setCommandBoxText(UNMARK_COMMAND_SHORTCUT_TEXT, KeyCombination.valueOf("Shortcut+U"));
        setCommandBoxText(EMPTY_COMMAND_SHORTCUT_TEXT, KeyCombination.valueOf("Shortcut+R"));
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
     * Sets the text of command box.
     * @param text The text to be added to the command box.
     * @param keyCombination the KeyCombination value of the accelerator.
     */
    private void setCommandBoxText(String text, KeyCombination keyCombination) {
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (keyCombination.match(event)) {
                CommandBox commandBox = new CommandBox(this::executeCommand, text);
                commandBoxPlaceholder.getChildren().clear();
                commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
                event.consume();
            }
        });
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        personListPanel = new PersonListPanel(logic.getFilteredPersonList(), isExpanded);
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getSurvinFilePath());
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
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelpForCommand(String helpMessageForCommand) {
        helpWindowForCommand.setTextString(helpMessageForCommand);
        //update the dimensions of the help window
        helpWindowForCommand.hide();
        helpWindowForCommand.show();
        helpWindowForCommand.focus();
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
        helpWindowForCommand.hide();
        primaryStage.hide();
    }

    /**
     * Changes theme to light theme or dark theme depending on current state.
     */
    @FXML
    private void handleLightDarkTheme() {
        if (currentTheme.equals(Theme.LIGHT)) {
            logger.info("Switching to dark theme...");
            currentTheme = Theme.DARK;
            lightDarkThemeItem.setText(LIGHT_THEME_MENUITEM_TEXT);
            primaryStage.getScene().getStylesheets().clear();
            primaryStage.getScene().getStylesheets().add(darkTheme);
            primaryStage.getScene().getStylesheets().add(extensionsDark);
            helpWindow.setDarkTheme();
            helpWindowForCommand.setDarkTheme();
        } else if (currentTheme.equals(Theme.DARK)) {
            logger.info("Switching to light theme...");
            currentTheme = Theme.LIGHT;
            lightDarkThemeItem.setText(DARK_THEME_MENUITEM_TEXT);
            primaryStage.getScene().getStylesheets().clear();
            primaryStage.getScene().getStylesheets().add(lightTheme);
            primaryStage.getScene().getStylesheets().add(extensionsLight);
            helpWindow.setLightTheme();
            helpWindowForCommand.setLightTheme();
        }
    }

    /**
     * Handles theme change from the ThemeCommand.
     * Takes in {@code Theme} that will specify the new theme to change to.
     * Throws a {@code CommandException} if theme to change to is same as current theme.
     * @param theme specifies the theme to change to
     * @throws CommandException if theme to change to is same as current theme
     */
    private void handleThemeCommand(Theme theme) throws CommandException {
        if (theme.equals(currentTheme)) {
            throw new CommandException(CHANGE_THEME_ERROR);
        }
        handleLightDarkTheme();
    }

    /**
     * Compacts or expands all PersonCard depending on current state.
     * Any new PersonCard will be created compacted or expanded depending on the new state.
     */
    @FXML
    private void handleCompactExpand() {
        if (isExpanded) {
            logger.info("Switching to compacted mode...");
            isExpanded = false;
            compactExpandItem.setText(EXPAND_MENUITEM_TEXT);
        } else {
            logger.info("Switching to expanded mode...");
            isExpanded = true;
            compactExpandItem.setText(COMPACT_MENUITEM_TEXT);
        }
        personListPanel = new PersonListPanel(logic.getFilteredPersonList(), isExpanded);
        personListPanelPlaceholder.getChildren().clear();
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());
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

            if (commandResult.isThemeChange()) {
                assert !commandResult.isToggleListMode()
                        && !commandResult.isShowHelp()
                        && !commandResult.isExit();
                handleThemeCommand(commandResult.getTheme());
            }

            if (commandResult.isToggleListMode()) {
                assert !commandResult.isThemeChange()
                        && !commandResult.isShowHelp()
                        && !commandResult.isExit();
                handleCompactExpand();
            }

            if (commandResult.isShowHelp()) {
                assert !commandResult.isThemeChange()
                        && !commandResult.isToggleListMode()
                        && !commandResult.isExit();
                if (commandResult.getShowHelpFor().equals("")) {
                    handleHelp();
                } else {
                    handleHelpForCommand(commandResult.getShowHelpFor());
                }
            }

            if (commandResult.isExit()) {
                assert !commandResult.isThemeChange()
                        && !commandResult.isToggleListMode()
                        && !commandResult.isShowHelp();
                handleExit();
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }

    public void setHelpMenuItem(MenuItem helpMenuItem) {
        this.helpMenuItem = helpMenuItem;
    }
}
