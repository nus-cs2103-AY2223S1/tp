package seedu.uninurse.ui;

import java.util.List;

import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.task.Task;

/**
 * An UI component that displays information of a Patient and a list of Tasks.
 */
public class ScheduleListCard extends UiPart<Region> {
    private static final String FXML = "ScheduleListCard.fxml";

    @FXML
    private VBox cardPane;
    @FXML
    private VBox taskPane;
    @FXML
    private Label name;
    @FXML
    private FlowPane tags;

    /**
     * Creates a ScheduleListCard with the given Patient and a list of Tasks to display.
     */
    public ScheduleListCard(Patient patient, List<Task> taskList) {
        super(FXML);
        this.cardPane.setSpacing(1);
        this.cardPane.setId("schedule_list_card");

        for (int i = 0; i < taskList.size(); i++) {
            if (i != 0) {
                this.taskPane.getChildren().add(new Separator(Orientation.HORIZONTAL));
            }
            this.taskPane.getChildren().add(new ScheduleListTaskCard(taskList.get(i)).getRoot());
        }
        this.name.setText(patient.getName().getValue());
        patient.getTags().getInternalList().forEach(tag -> tags.getChildren().add(new Label(tag.getValue())));
    }
}
