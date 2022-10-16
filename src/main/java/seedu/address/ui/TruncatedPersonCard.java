package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Patient;


// TODO: KIV this class for viewPatientDetails(TBC)
/**
 * TruncatedPersonCard is a UI component that displays only the name and tags of a {@code Patient}.
 */
public class TruncatedPersonCard extends UiPart<Region> {
    private static final String FXML = "TruncatedPersonListCard.fxml";

    public final Patient patient;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code TruncatedPersonCard} with the given {@code Patient} and index to display.
     */
    public TruncatedPersonCard(Patient patient, int displayedIndex) {
        super(FXML);
        cardPane.setSpacing(1);
        cardPane.setStyle("-fx-padding: 1;" + "-fx-border-style: solid inside;"
                + "-fx-border-width: 1;" + "-fx-border-insets: 1;"
                + "-fx-border-radius: 2;" + "-fx-border-color: black;");
        this.patient = patient;
        id.setText(displayedIndex + ". ");
        name.setText(patient.getName().fullName);
        patient.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonCard)) {
            return false;
        }

        // state check
        TruncatedPersonCard card = (TruncatedPersonCard) other;
        return id.getText().equals(card.id.getText())
                && patient.equals(card.patient);
    }



}


