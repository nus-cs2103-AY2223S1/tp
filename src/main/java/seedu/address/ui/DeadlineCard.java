package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Task;

/**
 * An UI component that displays information of a {@code Deadline}.
 */
public class DeadlineCard extends UiPart<Region> implements TaskCard {
    private static final String FXML = "TaskListCard.fxml";

    public final Task task;

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label title;
    @FXML
    private Label description;
    @FXML
    private Label date;

    /**
     * Creates a {@code DeadlineCard} with the given {@code Deadline} and index to display.
     *
     * @param task
     * @param displayedIndex
     */
    public DeadlineCard(Deadline task, int displayedIndex) {
        super(FXML);
        this.task = task;
        id.setText(displayedIndex + ". ");
        title.setText(task.getTitle().title);
        description.setText(task.getDescription().description);
        String dateString = task.getDate().toString();
        date.setText("Due on: " + dateString);

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
        if (!(other instanceof DeadlineCard)) {
            return false;
        }

        // state check
        DeadlineCard card = (DeadlineCard) other;
        return id.getText().equals(card.id.getText())
                && task.equals(card.task);
    }
}
