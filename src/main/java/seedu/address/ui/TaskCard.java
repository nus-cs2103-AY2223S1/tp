
package seedu.address.ui;

import java.util.Comparator;

import javafx.beans.value.ObservableValue;
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

        //Reused from https://stackoverflow.com/questions/56104276 with minor modifications
        status.textProperty().addListener((ObservableValue<? extends String> st, String old, String curr) -> {
            if (!isStatusComplete(curr)) {
                status.setStyle("-fx-text-fill: #D7504D;");
            } else {
                status.setStyle("-fx-text-fill: #96D294;");
            }
        });
        status.setText(task.getStatusForDisplay());
    }

    /**
     * Checks if task status is completed.
     * @param status task status.
     * @return true if task is completed, false if task is incomplete.
     */
    public boolean isStatusComplete(String status) {
        assert(status.equals("completed") || status.equals("incomplete"));
        return status.equals("completed");
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
