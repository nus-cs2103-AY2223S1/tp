package seedu.address.ui;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import seedu.address.MainApp;

/**
 * An UI component that displays information of an {@code Appointment}.
 */
public class AppointmentFlowPane extends FlowPane {
    private static final String INDEX_PADDING = "-fx-padding: 0 10 0 0";
    private static final String DATE_PADDING = "-fx-padding: 0 10 0 10";
    private static final String TIME_PADDING = "-fx-padding: 0 0 0 10";
    private static final String FLOWPANE_PADDING = "-fx-padding: 0 0 5 0";
    private static final Image CALENDAR_IMAGE = new Image(MainApp.class.getResourceAsStream("/images/calendar.png"));
    private static final Image CLOCK_IMAGE = new Image(MainApp.class.getResourceAsStream("/images/clock.png"));

    /**
     * Creates a {@code AppointmentFlowPane} with the given {@code appointmentDate},
     * {@code appointmentTime} and index to display.
     */
    public AppointmentFlowPane(String index, String appointmentDate, String appointmentTime) {
        Label indexLabel = new Label(index);
        indexLabel.setStyle(INDEX_PADDING);
        Label dateLabel = new Label(appointmentDate);
        dateLabel.setStyle(DATE_PADDING);
        Label timeLabel = new Label(appointmentTime);
        timeLabel.setStyle(TIME_PADDING);
        getChildren().addAll(
                indexLabel,
                new ImageView(CALENDAR_IMAGE),
                dateLabel,
                new ImageView(CLOCK_IMAGE),
                timeLabel
        );
        setStyle(FLOWPANE_PADDING);
    }
}
