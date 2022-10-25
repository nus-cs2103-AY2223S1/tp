package seedu.address.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import seedu.address.MainApp;
import seedu.address.model.person.Appointment;

/**
 * An UI component that displays information of an {@code Appointment}.
 */
public class AppointmentHBox extends HBox {
    private static final String INDEX_STYLE = "-fx-padding: 0 10 0 0; -fx-font-size: 20px; "
            + "-fx-font-weight: bold; -fx-text-fill: #FFC600;";

    private static final String DATE_PADDING = "-fx-padding: 0 10 0 10";
    private static final String TIME_PADDING = "-fx-padding: 0 10 0 10";
    private static final String LOCATION_PADDING = "-fx-padding: 0 10 0 10";
    private static final String HBOX_PADDING = "-fx-padding: 0 0 5 0";
    private static final Image CALENDAR_IMAGE = new Image(MainApp.class.getResourceAsStream("/images/calendar.png"));
    private static final Image CLOCK_IMAGE = new Image(MainApp.class.getResourceAsStream("/images/clock.png"));
    private static final Image LOCATION_IMAGE = new Image(MainApp.class.getResourceAsStream("/images/location.png"));

    /**
     * Creates a {@code AppointmentFlowPane} with the given {@code appointmentDate},
     * {@code appointmentTime} and index to display.
     */
    public AppointmentHBox(int index, Appointment appointment) {
        Label indexLabel = new Label(String.valueOf(index));
        indexLabel.setStyle(INDEX_STYLE);
        indexLabel.setMinWidth(26);

        Label dateLabel = new Label(appointment.getDate().toString());
        dateLabel.setMinWidth(110);
        dateLabel.setStyle(DATE_PADDING);
        Label timeLabel = new Label(appointment.getTime().toString());
        timeLabel.setMinWidth(82);
        timeLabel.setStyle(TIME_PADDING);
        Label locationLabel = new Label(appointment.getLocation().toString());
        locationLabel.setStyle(LOCATION_PADDING);
        getChildren().addAll(
                indexLabel,
                new ImageView(CALENDAR_IMAGE),
                dateLabel,
                new ImageView(CLOCK_IMAGE),
                timeLabel,
                new ImageView(LOCATION_IMAGE),
                locationLabel
        );
        setAlignment(Pos.CENTER_LEFT);
        setStyle(HBOX_PADDING);
    }
}
