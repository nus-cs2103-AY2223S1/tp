package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.task.Task;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class TaskListCard extends UiPart<Region> {

    private static final String FXML = "TaskListCard.fxml";

    public final Task task;

    @FXML
    private VBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label description;
    @FXML
    private Label deadline;
    @FXML
    private Label completion;

    /**
     * Creates a {@code TaskListCard} with the given {@code Task} and index to display.
     */
    public TaskListCard(Task task, int displayedIndex) {
        super(FXML);
        this.task = task;
        id.setText(displayedIndex + ".");
        name.setText(String.valueOf(task.getTaskName()));
        description.setText(String.valueOf(task.getTaskDescription()));
        deadline.setText(String.format("Due by %s", task.getTaskDeadline()));
        completion.setText("10% completed (3/30 students)"); // Dummy text.
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
        return id.getText().equals(card.id.getText())
                && task.equals(card.task);
    }
}
