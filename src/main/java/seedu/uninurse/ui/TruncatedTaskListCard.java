package seedu.uninurse.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.task.TaskList;

/**
 * An UI component that displays information of a Patient with a truncated TaskList.
 */
public class TruncatedTaskListCard extends UiPart<Region> {
    private static final String FXML = "TruncatedTaskListCard.fxml";

    private static final int TRUNCATION_LIMIT = 3;

    @FXML
    private VBox cardPane;
    @FXML
    private Label name;
    @FXML
    private FlowPane tags;
    @FXML
    private VBox taskPane;

    /**
     * Creates a TruncatedTaskListCard with the given Patient to display.
     */
    public TruncatedTaskListCard(Patient patient) {
        super(FXML);
        this.cardPane.setSpacing(1);
        this.cardPane.setId("truncated_task_list_card");

        this.name.setText(patient.getName().getValue());
        patient.getTags().getInternalList()
                .forEach(tag -> tags.getChildren().add(new Label(tag.getValue())));

        TaskList taskList = patient.getTasks();
        for (int i = 0; (i < taskList.size() && i < TRUNCATION_LIMIT); i++) {
            Label taskLabel = new Label(taskList.get(i).getTaskDescription());
            this.taskPane.getChildren().add(taskLabel);
        }

        if (taskList.size() > TRUNCATION_LIMIT) {
            VBox additionalTaskBox = new VBox();
            additionalTaskBox.setId("task_number_border_round");
            additionalTaskBox.setMaxWidth(150.0);

            Label additionalTaskNumberLabel = new Label();
            if ((taskList.size() - TRUNCATION_LIMIT) == 1) {
                additionalTaskNumberLabel.setText("+ 1 more Task");
            } else {
                additionalTaskNumberLabel.setText("+ " + (taskList.size() - TRUNCATION_LIMIT) + " more Tasks");
            }
            additionalTaskNumberLabel.setId("task_number_label");

            additionalTaskBox.getChildren().add(additionalTaskNumberLabel);
            this.taskPane.getChildren().add(additionalTaskBox);
        }
    }
}
