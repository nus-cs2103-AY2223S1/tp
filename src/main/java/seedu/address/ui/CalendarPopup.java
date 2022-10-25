package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;
import seedu.address.model.calendar.CalendarEvent;

/**
 * The Popup that is displayed when a {@code CalendarButton} is
 * interacted with.
 */
public class CalendarPopup extends UiPart<Tooltip> {
    private static final String FXML = "CalendarPopup.fxml";
    private static final int POPUP_DELAY = 10;
    private static final int POPUP_DURATION = 1000;
    private static final String LABEL_STYLE = "-fx-text-fill: black;";
    private static final String LEFT_SQUARE_BUBBLE =
            "-fx-shape: \" M24 1h-24v16.981h4v5.019l7-5.019h13z \";";
    private static final String RIGHT_SQUARE_BUBBLE =
            "M24 1h-24v16.981h13l7+5.019v-5.019h4z";
    private static final String POPUP_STYLE = "-fx-font-size: 16px; -fx-background-color: "
            + "white;";
    private final Node owner;
    @FXML
    private Tooltip popup;

    /**
     * Creates a {@code CalendarPopup} with the given CalendarEvent details.
     */
    public CalendarPopup(CalendarEvent calendarEvent, Node owner) {
        super(FXML);
        this.owner = owner;
        initialiseCalendarPopup(calendarEvent);
    }

    private void initialiseCalendarPopup(CalendarEvent calendarEvent) {
        popup.setStyle(POPUP_STYLE + LEFT_SQUARE_BUBBLE);
        popup.setShowDelay(new Duration(POPUP_DELAY));
        popup.setShowDuration(new Duration(POPUP_DURATION));
        popup.setGraphic(new CalendarPopupContent(calendarEvent).getRoot());
    }
}
