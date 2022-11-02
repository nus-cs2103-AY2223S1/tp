package seedu.address.ui.popupwindow;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.addcommands.AddCommandWithPopup;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.ui.ResultDisplay;
import seedu.address.ui.UiPart;

/**
 * The pop-up window for adding a buyer with/without orders,
 * or adding a supplier with/without pets.
 */
public class AddCommandPopupWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(AddCommandPopupWindow.class);
    private static final String FXML = "AddCommandPopupWindow.fxml";
    private static final String ICON_APPLICATION = "/images/petCode.png";
    private final Stage stage;
    private final Logic logic;
    private final ResultDisplay resultDisplay;
    private final Image image = new Image(MainApp.class.getResourceAsStream(ICON_APPLICATION));
    private PopUpPanel popUpPanel;

    @FXML
    private StackPane popupContentPlaceholder;

    @FXML
    private Button saveButton;

    @FXML
    private Label typeToBeAdded;

    /**
     * Constructs a {@code AddCommandPopupWindow}.
     *
     * @param root Stage of the window.
     * @param logic Logic for execution of the generated add command.
     * @param typeToBeAdded Type of person to add.
     * @param resultDisplay Result display window of the main window.
     */
    public AddCommandPopupWindow(Stage root, Logic logic, String typeToBeAdded, ResultDisplay resultDisplay) {
        super(FXML, root);
        this.stage = root;
        this.logic = logic;
        this.typeToBeAdded.setText(typeToBeAdded);
        this.resultDisplay = resultDisplay;
        this.stage.getIcons().add(image);
        setCloseWindowKey(KeyCode.ESCAPE);
        setSaveButtonShortcut(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
    }

    public AddCommandPopupWindow(Logic logic, String typeToBeAdded, ResultDisplay resultDisplay) {
        this(new Stage(), logic, typeToBeAdded, resultDisplay);
    }



    /**
     * Fills the placeholder in the pop-up window with content based on the typo of person to be added.
     *
     * @param typeToBeAdded Type of person to be added.
     */
    public void fillContentPlaceholder(String typeToBeAdded) {
        typeToBeAdded = typeToBeAdded.trim().toUpperCase();
        popupContentPlaceholder.getChildren().clear();
        switch (typeToBeAdded) {
        case AddCommandWithPopup.ADD_BUYER:
            PopupPanelForBuyer popupPanelForBuyer = new PopupPanelForBuyer();
            popUpPanel = popupPanelForBuyer;
            popupContentPlaceholder.getChildren().add(popupPanelForBuyer.getRoot());
            break;
        case AddCommandWithPopup.ADD_SUPPLIER:
            PopupPanelForSupplier popupPanelForSupplier = new PopupPanelForSupplier(stage);
            popUpPanel = popupPanelForSupplier;
            popupContentPlaceholder.getChildren().add(popupPanelForSupplier.getRoot());
            break;
        default:
            // Do nothing
        }
    }

    /**
     * Converts the user input to a {@code Command} when the {@code saveButton} is pressed.
     *
     * @param event Action event.
     * @throws CommandException If the execution of the generated command causes exception.
     * @throws ParseException If the parsing of the user input into a command causes exception.
     */
    @FXML
    void exitWithSave(ActionEvent event) throws CommandException, ParseException {
        try {
            if (!popUpPanel.checkAllPartsFilled()) {
                return;
            }
            Command command = popUpPanel.generateCommand();
            CommandResult commandResult = logic.executeGivenCommand(command);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());
            close();
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command!");
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }

    /**
     * Sets the keyboard shortcut for closing the pop-up window.
     *
     * @param keyCode A keyboard key.
     */
    public void setCloseWindowKey(KeyCode keyCode) {
        // Solution adapted from https://stackoverflow.com/questions/14357515/javafx-close-window-on-pressing-esc
        stage.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
            if (event.getCode() == keyCode) {
                close();
            }
        });
    }

    /**
     * Sets the keyboard shortcut for the save button.
     *
     * @param keyCombination A combination of keyboard keys.
     */
    public void setSaveButtonShortcut(KeyCombination keyCombination) {
        getRoot().addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (keyCombination.match(event)) {
                saveButton.fire();
                event.consume();
            }
        });
    }

    /**
     * Shows the pop-up window.
     */
    public void show() {
        logger.fine("Showing add command pop-up window.");
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        getRoot().initStyle(StageStyle.DECORATED);
        getRoot().setWidth(screenBounds.getWidth() * 0.6);
        getRoot().setHeight(screenBounds.getHeight() * 0.75);
        getRoot().setX(screenBounds.getMinX());
        getRoot().setY(screenBounds.getMinY());
        getRoot().setMaxWidth(screenBounds.getWidth() * 2);
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Closes the pop-up window.
     */
    public void close() {
        logger.fine("Add command pop-up window closed.");
        stage.close();
    }

}
