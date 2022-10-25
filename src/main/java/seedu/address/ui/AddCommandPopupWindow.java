package seedu.address.ui;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.AddCommandWithPopup;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

public class AddCommandPopupWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(AddCommandPopupWindow.class);
    private static final String FXML = "AddCommandPopupWindow.fxml";
    private Stage stage;
    private Logic logic;
    private PopUpPanel popUpPanel;
    private ResultDisplay resultDisplay;

    @FXML
    private StackPane popupContentPlaceholder;

    @FXML
    private Button saveButton;
    @FXML
    private Label typeToBeAdded;

    public AddCommandPopupWindow(Stage root, Logic logic, String typeToBeAdded, ResultDisplay resultDisplay) {
        super(FXML, root);
        this.stage = root;
        this.logic = logic;
        this.typeToBeAdded.setText(typeToBeAdded);
        this.resultDisplay = resultDisplay;
        fillContentPlaceholder(typeToBeAdded);
        setCloseWindowKey();
        // Alternative solution: setCloseWindowKey(new KeyCodeCombination(KeyCode.ESCAPE));
        setSaveButtonShortcut(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
    }

    public AddCommandPopupWindow(Logic logic, String typeToBeAdded, ResultDisplay resultDisplay) {
        this(new Stage(), logic, typeToBeAdded, resultDisplay);
    }

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
            PopupPanelForSupplier popupPanelForSupplier = new PopupPanelForSupplier();
            popUpPanel = popupPanelForSupplier;
            popupContentPlaceholder.getChildren().add(popupPanelForSupplier.getRoot());
            break;
        default:
            // Do nothing
        }
    }

    public void setCloseWindowKey() {
        // Solution adapted from https://stackoverflow.com/questions/14357515/javafx-close-window-on-pressing-esc
        stage.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                close();
            }
        });
    }

    public void setCloseWindowKey(KeyCombination keyCombination) {
        // Solution adapted from https://stackoverflow.com/questions/25397742/javafx-keyboard-event-shortcut-key
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                stage.close();
                event.consume();
            }
        });
    }

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

    public void setSaveButtonShortcut(KeyCombination keyCombination) {
        getRoot().addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (keyCombination.match(event)) {
                saveButton.fire();
                event.consume();
            }
        });
    }

    public void show() {
        logger.fine("Showing add command pop-up window.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    public void close() {
        logger.fine("Add command pop-up window closed.");
        stage.close();
    }

}
