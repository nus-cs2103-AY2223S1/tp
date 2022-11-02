package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import seedu.address.model.calendar.CalendarEvent;

/**
 * The Popup that is displayed when a {@code CalendarButton} is
 * interacted with.
 */
public class CalendarPopup extends UiPart<Popup> {
    private static final String FXML = "CalendarPopup.fxml";
    private final Node owner;
    @FXML
    private Popup popup;

    /**
     * Creates a {@code CalendarPopup} with the given CalendarEvent details.
     */
    public CalendarPopup(CalendarEvent calendarEvent, Node owner) {
        super(FXML);
        this.owner = owner;
        initialiseCalendarPopup(calendarEvent);
        popup.sizeToScene();
    }

    private void initialiseCalendarPopup(CalendarEvent calendarEvent) {
        popup.getContent().add(new CalendarPopupContent(calendarEvent).getRoot());
        popup.setAnchorLocation(PopupWindow.AnchorLocation.WINDOW_BOTTOM_LEFT);
    }

    public void displayToolTip() {

    }
}
