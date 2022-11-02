package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.calendar.CalendarEvent;

/**
 * The content that is displayed within the {@code CalendarPopup}
 */
public class CalendarPopupContent extends UiPart<Region> {
    private static final String FXML = "CalendarPopupContent.fxml";
    @FXML
    private ScrollPane popupContent;
    @FXML
    private Label clientLabel;
    @FXML
    private FlowPane datePane;
    @FXML
    private FlowPane timePane;
    @FXML
    private FlowPane locationPane;
    @FXML
    private Label dateLabel;
    @FXML
    private Label timeLabel;
    @FXML
    private Label locationLabel;
    @FXML
    private VBox popupVBox;

    /**
     * Creates a {@code CalendarPopupContent} with the given CalendarEvent details.
     */
    public CalendarPopupContent(CalendarEvent calendarEvent) {
        super(FXML);
        clientLabel.setText(calendarEvent.getName().toString());
        dateLabel.setText(calendarEvent.getDate());
        timeLabel.setText(calendarEvent.getTimeFormat());
        locationLabel.setText(calendarEvent.getLocation().toString());
    }
}
