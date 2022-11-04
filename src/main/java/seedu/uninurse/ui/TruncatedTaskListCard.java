package seedu.uninurse.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.task.TaskList;

/**
 * An UI component that displays information of a {@code Patient} with a truncated {@code TaskList}.
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
     * Creates a {@code TruncatedTaskListCard} with the given {@code Patient} to display.
     */
    public TruncatedTaskListCard(Patient patient) {
        super(FXML);
        cardPane.setSpacing(1);
        cardPane.setStyle("-fx-padding: 1;" + "-fx-border-style: dashed inside;"
                + "-fx-border-width: 1;" + "-fx-border-insets: 1;"
                + "-fx-border-radius: 5;" + "-fx-border-color: black;");

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
            additionalTaskBox.setStyle("-fx-background-color: #2154ad;"
                    + "-fx-padding: 3;" + "-fx-border-radius: 2;"
                    + "-fx-background-radius: 10;");
            additionalTaskBox.setMaxWidth(150.0);

            Label additionalTaskNumberLabel = new Label();
            if ((taskList.size() - TRUNCATION_LIMIT) == 1) {
                additionalTaskNumberLabel.setText("+ 1 more Task");
            } else {
                additionalTaskNumberLabel.setText("+ " + (taskList.size() - TRUNCATION_LIMIT) + " more Tasks");
            }
            additionalTaskNumberLabel.setStyle("-fx-font-family: \"Open Sans Semibold\";"
                    + "-fx-font-size: 13px;"
                    + "-fx-text-fill: white;");

            additionalTaskBox.getChildren().add(additionalTaskNumberLabel);
            this.taskPane.getChildren().add(additionalTaskBox);
        }
    }
}
