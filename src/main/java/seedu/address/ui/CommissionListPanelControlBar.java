package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import seedu.address.ui.CommandWindows.AddCommissionWindow;

/**
 * Represents the controls of the {@code CommissionListPanel}.
 */
public class CommissionListPanelControlBar extends UiPart<Region> {

    private static final String FXML = "CommissionListPanelControlBar.fxml";

    private AddCommissionWindow addCommissionWindow;

    @FXML
    private Button addCommissionButton;

    /**
     * Constructs a {@code CommissionListPanelControlBar}.
     */
    public CommissionListPanelControlBar(MainWindow mainStage, CommandBox.CommandExecutor commandExecutor) {
        super(FXML);
        addCommissionWindow = new AddCommissionWindow(mainStage, commandExecutor, new Stage());
    }

    /**
     * Opens the add commission window or focuses on it if it's already opened.
     */
    @FXML
    private void showAddCommissionWindow() {
        if (!addCommissionWindow.isShowing()) {
            addCommissionWindow.show();
        } else {
            addCommissionWindow.focus();
        }
    }
}
