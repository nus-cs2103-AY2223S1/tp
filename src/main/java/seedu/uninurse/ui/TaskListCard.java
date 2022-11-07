package seedu.uninurse.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.uninurse.model.task.DateTime;
import seedu.uninurse.model.task.Task;

/**
 * An UI component that displays information of a Patient.
 */
public class TaskListCard extends UiPart<Region> {

    private static final String FXML = "TaskListCard.fxml";
    public final Task task;

    @FXML
    private HBox taskPane;
    @FXML
    private Label id;
    @FXML
    private Label taskname;
    @FXML
    private Label date;
    @FXML
    private Label time;
    @FXML
    private Label recurrence;

    /**
     * Creates a TaskListCard with the given TaskList to display.
     */
    public TaskListCard(Task task, int displayedIndex) {
        super(FXML);
        this.task = task;

        this.taskPane.setSpacing(1);

        if (task.getDateTime().isPastDate()) {
            this.taskPane.setId("task_list_card_overdue");
        } else {
            this.taskPane.setId("task_list_card");
        }
        this.id.setText(displayedIndex + ". ");
        this.taskname.setText(task.getTaskDescription());
        this.date.setText(getDateString(task.getDateTime()));
        this.time.setText(String.format("%s", task.getDateTime().getTime()));
        this.recurrence.setText(task.getRecurrenceString());
    }

    /**
     * Returns the date and the numbers of days from today based on dateTime.
     */
    private String getDateString(DateTime dateTime) {
        String dateString = dateTime.getDate();
        int daysFromToday = dateTime.getDaysFromToday();
        if (daysFromToday == 0) {
            return String.format("%s (Today)", dateString);
        }
        if (daysFromToday == 1) {
            return String.format("%s (Tomorrow)", dateString);
        }
        if (daysFromToday < 0) {
            if (daysFromToday == -1) {
                return String.format("%s (Overdue by 1 day)", dateString);
            } else {
                return String.format("%s (Overdue by %s days)", dateString, Math.abs(daysFromToday));
            }
        }
        return String.format("%s (in %s days)", dateString, daysFromToday);
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
        TaskListCard o = (TaskListCard) other;
        return taskname.getText().equals(o.taskname.getText())
                && date.equals(o.date)
                && time.equals(o.time);
    }
}
