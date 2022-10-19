package seedu.address.ui;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import seedu.address.MainApp;

/**
 * An UI component that displays information of an {@code Appointment}.
 */
public class AppointmentFlowPane extends FlowPane {
    private static final String INDEX_STYLE = "-fx-padding: 0 10 0 0; -fx-font-size: 14px; -fx-font-weight: bold";
    private static final String DATE_PADDING = "-fx-padding: 0 10 0 10";
    private static final String TIME_PADDING = "-fx-padding: 0 0 0 10";
    private static final String FLOWPANE_PADDING = "-fx-padding: 0 0 5 0";
    private static final Image CALENDAR_IMAGE = new Image(MainApp.class.getResourceAsStream("/images/calendar.png"));
    private static final Image CLOCK_IMAGE = new Image(MainApp.class.getResourceAsStream("/images/clock.png"));
    private static final Image LOCATION_IMAGE = new Image(MainApp.class.getResourceAsStream("/images/location.png"));

    /**
     * Creates a {@code AppointmentFlowPane} with the given {@code appointmentDate},
     * {@code appointmentTime} and index to display.
     */
    public AppointmentFlowPane(String index, String appointmentDate, String appointmentTime,
                                String appointmentLocation) {
        setPrefWrapLength(Integer.MAX_VALUE);
        Label indexLabel = new Label(index);
        indexLabel.setStyle(INDEX_STYLE);
        Label dateLabel = new Label(appointmentDate);
        dateLabel.setStyle(DATE_PADDING);
        Label timeLabel = new Label(appointmentTime);
        timeLabel.setStyle(TIME_PADDING);
        Label locationLabel = new Label(appointmentLocation + System.lineSeparator());
        timeLabel.setStyle(TIME_PADDING);
        getChildren().addAll(
                indexLabel,
                new ImageView(CALENDAR_IMAGE),
                dateLabel,
                new ImageView(CLOCK_IMAGE),
                timeLabel,
                new ImageView(LOCATION_IMAGE),
                locationLabel
        );
        setStyle(FLOWPANE_PADDING);
    }
}
