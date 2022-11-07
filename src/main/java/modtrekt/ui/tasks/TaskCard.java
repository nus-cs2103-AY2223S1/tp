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
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Task task;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label module;
    @FXML
    private Label description;
    @FXML
    private Label dueDate;
    @FXML
    private FlowPane badges;

    /**
     * Creates a {@code ModuleCode} with the given {@code Module} and index to display.
     */
    public TaskCard(Task t, int displayedIndex) {
        super(FXML);
        this.task = t;
        id.setText("#" + displayedIndex);

        description.setText(task.getDescription().toString());
        module.setText(t.getModule().toString());
        if (t instanceof Deadline) {
            dueDate.setText("due " + ((Deadline) t).getDueDate().toString());
            dueDate.setVisible(true);
            dueDate.setManaged(true);
        } else {
            // Setting it to invisible will still give the text vertical height, unless it is not managed.
            dueDate.setVisible(false);
            dueDate.setManaged(false);
        }
        this.badges.setVisible(false);
        this.badges.setManaged(false);
        if (task.isDone()) {
            // Add the `done` badge if the task is done.
            Label doneBadge = new Label("done");
            this.badges.getChildren().add(doneBadge);
            this.badges.setVisible(true);
            this.badges.setManaged(true);
        }
        if (task.getPriority() != Task.Priority.NONE) {
            // Add the priority badge only if it has been set.
            Label priorityBadge = new Label(task.getPriority().toString().toLowerCase() + " priority");
            this.badges.getChildren().add(priorityBadge);
            this.badges.setVisible(true);
            this.badges.setManaged(true);
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
