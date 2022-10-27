package taskbook.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import taskbook.model.task.Deadline;
import taskbook.model.task.Task;
import taskbook.model.task.Todo;

/**
 * An UI component that displays information of a {@code TaskList}.
 */
public class TaskListCard extends UiPart<Region> {

    private static final String FXML = "TaskListCard.fxml";
    private static final String FXMLDEADLINE = "TaskListDeadlineCard.fxml";
    private static final String FXMLEVENT = "TaskListEventCard.fxml";
    private static final String FXMLTODO = "TaskListTodoCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/taskbook-level4/issues/336">The issue on TaskBook level 4</a>
     */

    public final Task task;

    @FXML
    private HBox cardPane;
    @FXML
    private Label relatedParty;
    @FXML
    private Label assignment;
    @FXML
    private Label description;
    @FXML
    private Label isDone;
    @FXML
    private Label id;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code TaskListCode} with the given {@code TaskList} and index to display.
     */
    public TaskListCard(Task task, int displayedIndex) {
        super(task instanceof Todo ? FXMLTODO : task instanceof Deadline ? FXMLDEADLINE : FXMLEVENT);
        this.task = task;
        this.relatedParty.setText(task.getName().fullName);
        this.description.setText(task.toUiString());
        this.assignment.setText(task.getAssignment().toString());
        this.isDone.setText(task.getStatus());
        this.id.setText(displayedIndex + ". ");
        task.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
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
