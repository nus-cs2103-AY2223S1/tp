package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

public class AddCommandPopupWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "AddCommandPopupWindow.fxml";
    private Stage stage;

    @FXML
    private StackPane popupContentPlaceholder;

    @FXML
    private Label typeToBeAdded;

    public AddCommandPopupWindow(Stage root, String typeToBeAdded) {
        super(FXML, root);
        this.stage = root;
        this.typeToBeAdded.setText(typeToBeAdded);
    }

    public AddCommandPopupWindow(String typeToBeAdded) {
        this(new Stage(), typeToBeAdded);
    }

    public void fillInnerParts() {

    }

    public void setCloseWindowKey() {
        
    }

    public void show() {
        logger.fine("Showing add command pop-up window.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    public void closeWithoutSaving() {

    }

}
