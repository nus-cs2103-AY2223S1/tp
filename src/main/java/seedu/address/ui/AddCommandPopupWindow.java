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

public class AddCommandPopupWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "AddCommandPopupWindow.fxml";
    private static final String ORDER_COMPONENT = "order";
    private static final String PET_COMPONENT = "pet";
    private Stage stage;
    private Logic logic;
    private PopupPanelForOrder popupPanelForOrder;
    private PopupPanelForBuyer popupPanelForBuyer;
    private PopupPanelForPet popupPanelForPet;

    @FXML
    private StackPane popupContentPlaceholder;

    @FXML
    private Button saveButton;
    @FXML
    private Label typeToBeAdded;

    public AddCommandPopupWindow(Stage root, Logic logic, String typeToBeAdded) {
        super(FXML, root);
        this.stage = root;
        this.typeToBeAdded.setText(typeToBeAdded);
        fillContentPlaceholder(typeToBeAdded);
        setCloseWindowKey();
        // Alternative solution: setCloseWindowKey(new KeyCodeCombination(KeyCode.ESCAPE));
        setSaveButtonShortcut(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
    }

    public AddCommandPopupWindow(Logic logic, String typeToBeAdded) {
        this(new Stage(), logic, typeToBeAdded);
    }

    public void fillContentPlaceholder(String typeToBeAdded) {
        typeToBeAdded = typeToBeAdded.trim().toUpperCase();
        switch (typeToBeAdded) {
            // TODO: implement this
        }
    }

    public void setCloseWindowKey() {
        // Solution adapted from https://stackoverflow.com/questions/14357515/javafx-close-window-on-pressing-esc
        stage.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                stage.close();
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
    void handleInputCommand(ActionEvent event) {

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

    public boolean isShowing() {
        return getRoot().isShowing();
    }

}
