
package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.task.Task;

/**
 * An UI component that displays information of a {@code Task}.
 */
public class TaskCard extends UiPart<Region> {

    private static final String FXML = "TaskListCard.fxml";

    private static final String COLOR_TASK_COMPLETED = "#96D294";

    private static final String COLOR_TASK_NOT_COMPLETED = "#D7504D";

    private static final String COLOR_TASK_ARCHIVED = "#FFD580";

    private static final String MESSAGE_TASK_ARCHIVED = "Archived";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Task task;

    @FXML
    private HBox cardPane;
    @FXML
    private Label description;
    @FXML
    private Label id;
    @FXML
    private Label deadline;
    @FXML
    private Label status;
    @FXML
    private Label archivalStatus;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code TaskCard} with the given {@code Task} and index to display.
     */
    public TaskCard(Task task, int displayedIndex) {
        super(FXML);
        this.task = task;
        id.setText(displayedIndex + ". ");
        description.setText(task.getDescription().toString());
        deadline.setText(task.getDeadline().toString());
        task.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        status.setText(task.getCompletionStatus().toString());
        if (task.getIsCompleted()) {
            status.setStyle(String.format("-fx-text-fill: %s;", COLOR_TASK_COMPLETED));
        } else {
            status.setStyle(String.format("-fx-text-fill: %s;", COLOR_TASK_NOT_COMPLETED));
        }
        if (task.getIsArchived()) {
            archivalStatus.setText(MESSAGE_TASK_ARCHIVED);
            archivalStatus.setStyle(String.format("-fx-text-fill: %s;", COLOR_TASK_ARCHIVED));
        } else {
            archivalStatus.setVisible(false);
            archivalStatus.setManaged(false);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TaskCard)) {
            return false;
        }

        // state check
        TaskCard card = (TaskCard) other;
        return id.getText().equals(card.id.getText())
                && task.equals(card.task);
    }
}
