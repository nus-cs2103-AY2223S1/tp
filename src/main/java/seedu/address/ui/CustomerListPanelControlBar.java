package seedu.address.ui;

import java.util.function.Consumer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import seedu.address.ui.command.window.AddCustomerWindow;
import seedu.address.ui.command.window.CustomerWindow;

/**
 * Represents the controls of the {@code CommissionListPanel}.
 */
public class CustomerListPanelControlBar extends UiPart<Region> {

    private static final String FXML = "CustomerListPanelControlBar.fxml";

    private CustomerWindow addCustomerWindow;

    @FXML
    private Button addCustomerButton;

    /**
     * Constructs a {@code CustomerListPanelControlBar}.
     */
    public CustomerListPanelControlBar(Consumer<UiPart<Stage>> addChildWindow,
                                       CommandBox.CommandExecutor commandExecutor) {
        super(FXML);
        addCustomerWindow = new AddCustomerWindow(addChildWindow, commandExecutor, new Stage());
    }

    /**
     * Opens the add customer window or focuses on it if it's already opened.
     */
    @FXML
    private void showAddCustomerWindow() {
        if (!addCustomerWindow.isShowing()) {
            addCustomerWindow.show();
        } else {
            addCustomerWindow.focus();
        }
    }
}
