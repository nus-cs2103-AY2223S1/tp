package seedu.uninurse.ui;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.uninurse.model.person.Patient;


/**
 * TruncatedPatientListCard is a UI component that displays only the name and tags
 * and the number of remaining tasks of a Patient.
 */
public class TruncatedPatientListCard extends UiPart<Region> {
    private static final String FXML = "TruncatedPatientListCard.fxml";

    private final Patient patient;

    @FXML
    private VBox cardPane;
    @FXML
    private VBox taskNumberPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private FlowPane tags;

    /**
     * Creates a TruncatedPatientListCard with the given Patient and index to display.
     */
    public TruncatedPatientListCard(Patient patient, int displayedIndex) {
        super(FXML);        
        this.patient = patient;

        this.cardPane.setId("person_list_card");
        this.id.setText(displayedIndex + ". ");
        this.name.setText(patient.getName().getValue());
        patient.getTags().getInternalList()
                .forEach(tag -> tags.getChildren().add(new Label(tag.getValue())));

        if (patient.getTasks().size() > 0) {
            VBox taskNumberBox = new VBox();
            taskNumberBox.setId("task_number_border");
            taskNumberBox.setAlignment(Pos.CENTER);

            Label taskNumberLabel = new Label();
            taskNumberLabel.setId("task_number_label");
            if (patient.getTasks().size() == 1) {
                taskNumberLabel.setText("1 Task left");
            } else {
                taskNumberLabel.setText(patient.getTasks().size() + " Tasks left");
            }
            taskNumberBox.getChildren().add(taskNumberLabel);
            this.taskNumberPane.getChildren().add(taskNumberBox);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TruncatedPatientListCard)) {
            return false;
        }

        // state check
        TruncatedPatientListCard o = (TruncatedPatientListCard) other;
        return id.getText().equals(o.id.getText())
                && patient.equals(o.patient);
    }
}


