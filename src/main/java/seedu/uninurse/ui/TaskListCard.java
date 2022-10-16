package seedu.uninurse.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * An UI component that displays information of a {@code Patient}.
 */
public class TaskListCard extends UiPart<Region> {

    private static final String FXML = "TaskListCard.fxml";

    @FXML
    private HBox cardPane;
    @FXML
    private Label header;
    @FXML
    private Label taskList;
    /**
     * Creates a {@code TaskListCard} with the given {@code TaskList} to display.
     */
    public TaskListCard(String taskListString) {
        super(FXML);
        header.setText("Tasks:");
        taskList.setText(taskListString);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TaskListCard)) {
            return false;
        }

        // state check
        TaskListCard card = (TaskListCard) other;
        return taskList.getText().equals(card.taskList.getText());
    }
}
