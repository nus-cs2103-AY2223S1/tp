package seedu.uninurse.ui;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.uninurse.model.person.Patient;


/**
 * TruncatedPersonListCard is a UI component that displays only the name and tags of a {@code Patient}.
 */
public class TruncatedPersonListCard extends UiPart<Region> {
    private static final String FXML = "TruncatedPersonListCard.fxml";

    public final Patient patient;

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
     * Creates a {@code TruncatedPersonListCard} with the given {@code Patient} and index to display.
     */
    public TruncatedPersonListCard(Patient patient, int displayedIndex) {
        super(FXML);
        this.cardPane.setStyle("-fx-border-style: solid inside;"
                + "-fx-border-width: 1;" + "-fx-border-insets: 1;"
                + "-fx-border-radius: 2;" + "-fx-border-color: black;");

        this.patient = patient;
        this.id.setText(displayedIndex + ". ");
        this.name.setText(patient.getName().getValue());
        patient.getTags().getInternalList()
                .forEach(tag -> tags.getChildren().add(new Label(tag.getValue())));

        if (patient.getTasks().size() > 0) {
            VBox taskNumberBox = new VBox();
            taskNumberBox.setStyle("-fx-background-color: #2154ad;"
                    + "-fx-padding: 3;" + "-fx-border-radius: 2;"
                    // + "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.8), 10, 0, 0, 0);"
                    + "-fx-background-radius: 5;");
            taskNumberBox.setAlignment(Pos.CENTER);

            Label taskNumberLabel = new Label();
            if (patient.getTasks().size() == 1) {
                taskNumberLabel.setText("1 Task left");
            } else {
                taskNumberLabel.setText(patient.getTasks().size() + " Tasks left");
            }

            taskNumberLabel.setStyle("-fx-font-family: \"Open Sans Semibold\";"
                    + "-fx-font-size: 13px;"
                    + "-fx-text-fill: white;");
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
        if (!(other instanceof TruncatedPersonListCard)) {
            return false;
        }

        // state check
        TruncatedPersonListCard card = (TruncatedPersonListCard) other;
        return id.getText().equals(card.id.getText())
                && patient.equals(card.patient);
    }
}


