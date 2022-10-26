package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.address.model.appointment.PastAppointment;

/**
 * Creates a {@code PastAppointmentCard} with the given {@code PastAppointment} and index to display.
 */
public class PastAppointmentCard extends UiPart<Region> {
    private static final String FXML = "PastAppointmentCard.fxml";
    private static final String DATE_FORMAT = "Date: %s";
    private static final String DIAGNOSIS_FORMAT = "Diagnosis: %s";
    private static final String MEDICATION_FORMAT = "Medication: %s";

    public final PastAppointment pastAppointment;

    @FXML
    private Label id;
    @FXML
    private Label date;
    @FXML
    private Label diagnosis;
    @FXML
    private FlowPane medications;

    /**
     * Generates a past appointment card.
     * @param pastAppointment Past appointment to display.
     * @param displayedIndex Index of the past appointment.
     */
    public PastAppointmentCard(PastAppointment pastAppointment, int displayedIndex) {
        super(FXML);
        this.pastAppointment = pastAppointment;
        id.setText(displayedIndex + ". ");
        date.setText(String.format(DATE_FORMAT, pastAppointment.getDate()));
        diagnosis.setText(String.format(DIAGNOSIS_FORMAT, pastAppointment.getDiagnosis()));
        if (pastAppointment.getMedication().size() > 0) {
            Label meds = new Label(pastAppointment.getMedicationString());
            meds.applyCss();
            medications.getChildren().add(meds);
        }
    }

    @Override
    public boolean equals(Object other) {
        return (other == this)
                || ((other instanceof PastAppointmentCard)
                && pastAppointment.equals(((PastAppointmentCard) other).pastAppointment));
    }
}
