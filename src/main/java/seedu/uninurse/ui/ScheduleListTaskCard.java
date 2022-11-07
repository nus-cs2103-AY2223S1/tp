package seedu.uninurse.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.uninurse.model.task.Task;

/**
 * An UI component that displays information of a Task in a ScheduleListCard.
 */
public class ScheduleListTaskCard extends UiPart<Region> {
    private static final String FXML = "ScheduleListTaskCard.fxml";

    @FXML
    private VBox taskPane;
    @FXML
    private Label taskTime;
    @FXML
    private Label taskName;

    /**
     * Creates a ScheduleListTaskCard with the given Task to display.
     */
    public ScheduleListTaskCard(Task task) {
        super(FXML);
        this.taskTime.setText(task.getDateTime().getTime());
        this.taskName.setText(task.getTaskDescription());

        if (task.getDateTime().isPastDate()) {
            this.taskPane.setId("schedule_list_task_box_overdue");
        } else {
            this.taskPane.setId("schedule_list_task_box");
        }
    }
}
