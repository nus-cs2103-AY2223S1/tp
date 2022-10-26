package seedu.foodrem.ui;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.foodrem.commons.core.GuiSettings;
import seedu.foodrem.commons.core.LogsCenter;
import seedu.foodrem.logic.Logic;
import seedu.foodrem.logic.commands.CommandResult;
import seedu.foodrem.logic.commands.exceptions.CommandException;
import seedu.foodrem.logic.commands.generalcommands.HelpCommand;
import seedu.foodrem.views.StringView;
import seedu.foodrem.views.UiView;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {
    private final Logger logger = LogsCenter.getLogger(getClass());

    private final Stage primaryStage;
    private final Logic logic;
    private final HelpWindow helpWindow;
    // Independent Ui parts residing in this Ui container
    private ItemListPanel itemListPanel;
    private ResultDisplay resultDisplay;
    private UiView uiView;

    @FXML private StackPane commandBoxPlaceholder;
    @FXML private MenuItem helpMenuItem;
    @FXML private StackPane itemListPanelPlaceholder;
    @FXML private StackPane resultDisplayPlaceholder;
    @FXML private StackPane statusbarPlaceholder;

    private final String initialMessage;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic, String message) {
        super("MainWindow.fxml", primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;
        this.initialMessage = message;

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
        itemListPanel = new ItemListPanel(logic.getCurrentList());
        place(itemListPanelPlaceholder, itemListPanel);

        resultDisplay = new ResultDisplay();
        uiView = new UiView(resultDisplay);
        resultDisplay.place(StringView.from(initialMessage));
        place(resultDisplayPlaceholder, resultDisplay);

        place(statusbarPlaceholder, new StatusBarFooter(logic.getFoodRemFilePath()));
        place(commandBoxPlaceholder, new CommandBox(this::executeCommand));
    }

    private <T extends Node> void place(Pane target, UiPart<T> item) {
        target.getChildren().add(item.getRoot());
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        primaryStage.setResizable(false);
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    public void handleHelpCommand() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     * Sets the default message to the general help message.
     */
    @FXML
    private void handleHelp() {
        helpWindow.setMessageToDisplay(HelpCommand.getGeneralHelpMessage());
        handleHelpCommand();
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

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.foodrem.logic.Logic#execute(String)
     */
    private CommandResult<?> executeCommand(String commandText) throws CommandException, IllegalArgumentException {
        try {
            CommandResult<?> commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getOutput());
            uiView.viewFrom(commandResult.getOutput());
            // We need to hide the window to ensure it resizes on changing message to display.
            helpWindow.hide();

            if (commandResult.shouldShowHelp()) {
                helpWindow.setMessageToDisplay(commandResult.getHelpText());
                handleHelpCommand();
            }
            if (commandResult.shouldExit()) {
                handleExit();
            }

            return commandResult;
        } catch (CommandException | IllegalArgumentException e) {
            logger.info("Invalid command: " + commandText);
            uiView.viewFrom(e.getMessage());
            throw e;
        }
    }
}
