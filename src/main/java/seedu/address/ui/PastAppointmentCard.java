package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.address.model.appointment.PastAppointment;

public class PastAppointmentCard extends UiPart<Region> {
    private final static String FXML = "PastAppointmentCard.fxml";
    private final static String DATE_FORMAT = "Date: %s";
    private final static String DIAGNOSIS_FORMAT = "Diagnosis: %s";
    private final static String MEDICATION_FORMAT = "Medication: %s";

    public final PastAppointment pastAppointment;

    @FXML
    private Label id;
    @FXML
    private Label date;
    @FXML
    private Label diagnosis;
    @FXML
    private FlowPane medications;

    public PastAppointmentCard(PastAppointment pastAppointment, int displayedIndex) {
        super(FXML);
        this.pastAppointment = pastAppointment;
        id.setText(displayedIndex + ". ");
        date.setText(String.format(DATE_FORMAT, pastAppointment.getDate()));
        diagnosis.setText(String.format(DIAGNOSIS_FORMAT, pastAppointment.getDiagnosis()));
        if (pastAppointment.getMedication().size() > 0) {
            medications.getChildren().add(new Label(pastAppointment.getMedicationString()));
        }
    }

    @Override
    public boolean equals(Object other) {
        return (other == this)
                || ((other instanceof PastAppointmentCard)
                && pastAppointment.equals(((PastAppointmentCard) other).pastAppointment));
    }
}
