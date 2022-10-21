package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.task.Task;
import seedu.address.model.task.ToDo;

/**
 * An UI component that displays information of a {@code ToDo}.
 */
public class ToDoCard extends UiPart<Region> implements TaskCard {

    private static final String FXML = "TaskListCard.fxml";

    public final Task task;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label title;
    @FXML
    private Label description;

    /**
     * Creates a {@code ToDoCard} with the given {@code ToDo} and index to display.
     * @param task
     * @param displayedIndex
     */
    public ToDoCard(ToDo task, int displayedIndex) {
        super(FXML);
        this.task = task;
        id.setText(displayedIndex + ". ");
        title.setText(task.getTitle().title);
        description.setText(task.getDescription().description);

        // Set wrap
        title.setWrapText(true);
        description.setWrapText(true);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ToDoCard)) {
            return false;
        }

        // state check
        ToDoCard card = (ToDoCard) other;
        return id.getText().equals(card.id.getText())
                && task.equals(card.task);
    }
}
