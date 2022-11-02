package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Appointment;

/**
 * An UI component that displays information of an {@code Appointment}.
 */
public class AppointmentHBox extends UiPart<Region> {
    private static final String FXML = "AppointmentHBox.fxml";
    @FXML
    private HBox appointmentHBox;
    @FXML
    private Label indexLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Label timeLabel;
    @FXML
    private Label locationLabel;

    /**
     * Creates a {@code AppointmentFlowPane} with the given {@code appointmentDate},
     * {@code appointmentTime} and index to display.
     */
    public AppointmentHBox(int index, Appointment appointment, Node owner) {
        super(FXML);
        indexLabel.setText(String.valueOf(index));
        dateLabel.setText(appointment.getDate().toString());
        timeLabel.setText(appointment.getTimeFormat());
        locationLabel.setText(appointment.getLocation().toString());
        appointmentHBox.setOnMousePressed((x) -> {
            owner.requestFocus();
            System.out.println("He");
        });
    }


}
