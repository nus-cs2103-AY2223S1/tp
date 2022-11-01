package seedu.uninurse.ui;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.task.Task;

/**
 * An UI component that displays information of a {@code Task} in a {@code ScheduleListCard}.
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
     * Creates a {@code ScheduleListCard} with the given {@code Patient} and {@code Task} to display.
     */
    public ScheduleListTaskCard(Patient patient, Task task) {
        super(FXML);
        this.taskTime.setText(task.getDateTime().getTime());
        this.taskName.setText(task.getTaskDescription());

        if (task.getDateTime().isPastDate()) {
            this.taskPane.setBackground(new Background(new BackgroundFill(
                    Color.web("ffe6a1"), new CornerRadii(5.0), new Insets(1.0))));
        } else {
            this.taskPane.setBackground(new Background(new BackgroundFill(
                    Color.web("c5e2fc"), new CornerRadii(5.0), new Insets(1.0))));
        }
    }
}
