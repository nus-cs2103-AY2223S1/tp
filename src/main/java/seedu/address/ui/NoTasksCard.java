package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

/**
 * A UI component that displays a message to indicate that there are no
 * {@code Task}s in the module.
 */
public class NoTasksCard extends UiPart<Text> {
    private static final String FXML = "NoTasksCard.fxml";
    private static final String NO_TASKS = "You have not added any tasks for "
            + "this module.";

    @FXML
    private Label noTaskMessage;

    /**
     * Creates a {@code NoTasksCard} to display.
     */
    public NoTasksCard() {
        super(FXML);
        noTaskMessage.setText(NO_TASKS);
    }
}
