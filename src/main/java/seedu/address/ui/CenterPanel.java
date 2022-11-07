package seedu.address.ui;

import java.util.logging.Logger;

import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;

/**
 * The Center Panel. The left half is used to display the PersonListPanel
 * while the right half is used to display the ReminderListPanel.
 */
public class CenterPanel extends UiPart<Region> {
    private static final String FXML = "CenterPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ReminderListPanel.class);

    @javafx.fxml.FXML
    private StackPane personListPanelPlaceholder;

    @javafx.fxml.FXML
    private StackPane reminderListPanelPlaceholder;

    /**
     * Creates a {@code ReminderListPanel} with the given {@code ObservableList}.
     */
    public CenterPanel(PersonListPanel personListPanel, ReminderListPanel reminderListPanel) {
        super(FXML);
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());
        reminderListPanelPlaceholder.getChildren().add(reminderListPanel.getRoot());
    }
}
