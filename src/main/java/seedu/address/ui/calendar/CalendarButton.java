package seedu.address.ui.calendar;

import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.scene.layout.Region;
import seedu.address.ui.UiPart;

/**
 * Represents a Button used in the Calendar with hidden internal logic.
 */
public abstract class CalendarButton extends UiPart<Region> {
    public static final String CALENDAR_BUTTON_STYLE = "-fx-border-radius: 5; -fx-border-width: 2;"
             + "-fx-background-color: #1d1d1d; -fx-text-fill: white; -fx-font-size: 13;";
    public static final String GREY_BORDER = "-fx-border-color: grey;";
    public static final String ORANGE_BORDER = "-fx-border-color: orange;";
    protected CalendarButton(String fxml) {
        super(fxml);
    }

    protected abstract void handleOnAction(ActionEvent event);
    protected abstract void handleFocusedEvent(Observable observable);
}
