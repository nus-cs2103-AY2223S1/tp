package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.address.model.person.Appointment;

/**
 * An UI component that displays information of an {@code Appointment}.
 */
public class AppointmentFlowPane extends UiPart<Region> {
    private static final String FXML = "AppointmentFlowPane.fxml";
    private static final String INDEX_STYLE = "-fx-padding: 0 10 0 0; -fx-font-size: 20px; "
            + "-fx-font-weight: bold; -fx-text-fill: #FFC600;";
    private static final String INDEX_FIRST_STYLE = "-fx-padding: 0 13 0 0; -fx-font-size: 20px; "
            + "-fx-font-weight: bold; -fx-text-fill: #FFC600;";
    @FXML
    private FlowPane appointmentFlowPane;
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
    public AppointmentFlowPane(int index, Appointment appointment) {
        super(FXML);
        indexLabel.setText(String.valueOf(index));
        if (index == 1) {
            // Line of code is here because first appointment always misaligned with the rest of the appointments
            indexLabel.setStyle(INDEX_FIRST_STYLE);
        }
        dateLabel.setText(appointment.getDate().toString());
        timeLabel.setText(appointment.getTimeFormat());
        locationLabel.setText(appointment.getLocation().toString());
    }
}
