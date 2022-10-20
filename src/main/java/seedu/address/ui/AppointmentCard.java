package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Appointment;

public class AppointmentCard extends UiPart<Region>{
    private static final String FXML = "AppointmentListCard.fxml";

    public final Appointment appointment;

    @FXML
    private HBox cardPane;

    @FXML
    private Label id;
    @FXML
    private Label appointmentDateTime;

    @FXML
    private Label appointmentLocation;

    public AppointmentCard(Appointment appointment, int displayedIndex) {
        super(FXML);
        this.appointment = appointment;
        id.setText(displayedIndex + ". ");
        appointmentDateTime.setText(appointment.getDateTime().toString());
        appointmentLocation.setText(appointment.getLocation().toString());
    }
}
