package modtrekt.ui.tasks;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import modtrekt.model.task.Deadline;
import modtrekt.model.task.Task;
import modtrekt.ui.UiPart;

/**
 * An UI component that displays information of a {@code Module}.
 */
public class TaskCard extends UiPart<Region> {

    private static final String FXML = "tasks/TaskListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on ModuleList level 4</a>
     */

    public final Task task;

    @FXML
    private HBox cardPane;
    @FXML
    private Label description;
    @FXML
    private Label id;
    @FXML
    private Label dueDate;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code ModuleCode} with the given {@code Module} and index to display.
     */
    public TaskCard(Task t, int displayedIndex) {
        super(FXML);
        this.task = t;
        id.setText(displayedIndex + ". ");

        description.setText(task.getDescription().toString());
        Label moduleBadge = new Label(t.getModule().toString());
        this.tags.getChildren().add(moduleBadge);
        dueDate.setText("");
        if (t instanceof Deadline) {
            dueDate.setText("Due by: " + ((Deadline) t).getDueDate().toString());
        }
        if (task.isArchived()) {
            // Add the `archived` badge if the task is archived.
            Label archivedBadge = new Label("ARCHIVED");
            this.tags.getChildren().add(archivedBadge);
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
