package seedu.address.ui;

import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.scene.layout.Region;

/**
 * Represents a Button used in the Calendar with hidden internal logic.
 */
public abstract class CalendarButton extends UiPart<Region> {
    public static final String CALENDAR_BUTTON_STYLE = "-fx-border-radius: 5;";
    public static final String GREY_BORDER = "-fx-border-color: grey;";
    public static final String ORANGE_BORDER = "-fx-border-color: orange;";
    protected CalendarButton(String fxml) {
        super(fxml);
    }

    protected abstract void handleOnAction(ActionEvent event);
    protected abstract void handleFocusedEvent(Observable observable);
}
