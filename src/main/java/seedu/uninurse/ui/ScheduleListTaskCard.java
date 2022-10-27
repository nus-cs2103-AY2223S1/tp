package seedu.uninurse.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.task.Task;

/**
 * An UI component that displays information of a {@code Task} in a {@code ScheduleListCard}.
 */
public class ScheduleListTaskCard extends UiPart<Region> {
    private static final String FXML = "ScheduleListTaskCard.fxml";

    @FXML
    private VBox cardPane;
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

        if (task.getDateTime().isPastDate()) {
            this.cardPane.setStyle("-fx-background-color: #ffe6a1;");
        } else {
            this.cardPane.setStyle("-fx-background-color: #c5e2fc;");
        }
    }
}
