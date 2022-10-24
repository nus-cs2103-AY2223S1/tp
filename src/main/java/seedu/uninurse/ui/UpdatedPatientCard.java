package seedu.uninurse.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.uninurse.model.person.Patient;

/**
 * An UI component that displays information of a {@code Patient} without index.
 * UpdatedPersonCard to be used for output panel when adding, editing, or deleting a patient.
 */
public class UpdatedPatientCard extends UiPart<Region> {

    private static final String FXML = "UpdatedPatientCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Patient patient;

    @FXML
    private VBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    @FXML
    private Label header;
    @FXML
    private Label conditions;
    @FXML
    private Label medications;
    @FXML
    private Label taskheader;
    @FXML
    private Label tasklist;

    /**
     * Creates a {@code UpdatedPatientCard} with the given {@code Patient}.
     */
    public UpdatedPatientCard(Patient patient, String headerString) {
        super(FXML);
        cardPane.setSpacing(2);
        cardPane.setStyle("-fx-padding: 2;" + "-fx-border-style: solid inside;"
                + "-fx-border-width: 2;" + "-fx-border-insets: 2;"
                + "-fx-border-radius: 2;" + "-fx-border-color: black;");
        this.patient = patient;

        name.setText(patient.getName().getValue());
        phone.setText(patient.getPhone().getValue());
        address.setText(patient.getAddress().getValue());
        email.setText(patient.getEmail().getValue());
        patient.getTags().getInternalList()
                .forEach(tag -> tags.getChildren().add(new Label(tag.getValue())));
        header.setText(headerString);
        conditions.setText("Conditions:" + "\n" + patient.getConditions().toString());
        medications.setText("Medications:" + "\n" + "1. enlarger with also a really really long name"); //dummy values
        taskheader.setText("Tasks:");
        tasklist.setText(patient.getTasks().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UpdatedPatientCard)) {
            return false;
        }

        // state check
        UpdatedPatientCard card = (UpdatedPatientCard) other;
        return patient.equals(card.patient);
    }
}
