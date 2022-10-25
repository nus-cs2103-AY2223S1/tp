package seedu.uninurse.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.uninurse.model.task.DateTime;
import seedu.uninurse.model.task.Task;

/**
 * An UI component that displays information of a {@code Patient}.
 */
public class TaskListCard extends UiPart<Region> {

    private static final String FXML = "TaskListCard.fxml";
    public final Task task;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label taskname;
    @FXML
    private Label date;
    @FXML
    private Label time;
    /**
     * Creates a {@code TaskListCard} with the given {@code TaskList} to display.
     */
    public TaskListCard(Task task, int displayedIndex) {
        super(FXML);
        cardPane.setSpacing(1);
        cardPane.setStyle("-fx-padding: 1;" + "-fx-border-style: dashed inside;"
                + "-fx-border-width: 1;" + "-fx-border-insets: 1;"
                + "-fx-border-radius: 5;" + "-fx-border-color: black;");
        this.task = task;
        id.setText(displayedIndex + ". ");
        taskname.setText(task.getTaskDescription());
        DateTime dateTime = task.getDateTime();
        String dateString = dateTime.getDate();
        String timeString = dateTime.getTime();
        int daysFromToday = dateTime.findDaysFromToday();

        if (daysFromToday == 0) {
            date.setText(String.format("Date: %s (Today)", dateString));
        } else if (daysFromToday == 1) {
        date.setText(String.format("Date: %s (Tommorow)", dateString));
        } else if (daysFromToday < 0) {
            if (daysFromToday == -1) {
                date.setText(String.format("Date: %s (Overdue by 1 day)", dateString));
            } else {
                date.setText(String.format("Date: %s (Overdue by %s days)", dateString, Math.abs(daysFromToday)));
            }
        } else {
            date.setText(String.format("Date: %s (in %s days)", dateString, daysFromToday));
        }
        time.setText(String.format("Time: %s", timeString));
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
        return taskname.getText().equals(card.taskname.getText())
                && date.equals(card.date)
                && time.equals(card.time);
    }
}
