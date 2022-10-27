package seedu.uninurse.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.task.Task;

/**
 * An UI component that displays information of a {@code Task} in a {@code ScheduleListCard}.
 */
public class ScheduleListTaskCard extends UiPart<Region> {
    private static final String FXML = "ScheduleListTaskCard.fxml";

    @FXML
    private Label taskTime;
    @FXML
    private Label taskName;

    /**
     * Creates a {@code ScheduleListCard} with the given {@code Patient} and {@code Task} to display.
     */
    public ScheduleListTaskCard(Patient patient, Task task) {
        super(FXML);
        this.taskTime.setText(task.getDateTime().getTime());
        this.taskName.setText(task.getTaskDescription());
    }
}
