package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;

public class CommissionListPanelControlBar extends UiPart<Region> {
    private static final String FXML = "CommissionListPanelControlBar.fxml";

    @FXML
    private Button addCommissionButton;

    public CommissionListPanelControlBar() {
        super(FXML);
    }
}
