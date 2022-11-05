package seedu.address.ui;


import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import seedu.address.logic.CalendarLogic;

/**
 * A button that displays the previous month's CalendarEvents
 * when interacted with.
 */
public class PreviousButton extends CalendarButton {
    private static final String FXML = "PreviousButton.fxml";
    private CalendarLogic calendarLogic;
    @FXML
    private Button previousButton;

    /**
     * Creates a {@code PreviousButton} with the given {@code String} and {@code CalendarLogic}.
     */
    public PreviousButton(String content, CalendarLogic calendarLogic) {
        super(FXML);
        this.calendarLogic = calendarLogic;
        previousButton.setText(content);
        previousButton.focusedProperty().addListener(this::handleFocusedEvent);
    }

    @FXML
    protected void handleFocusedEvent(Observable observable) {
        if (previousButton.isFocused()) {
            previousButton.setStyle(CALENDAR_BUTTON_STYLE + ORANGE_BORDER);
        } else {
            previousButton.setStyle(CALENDAR_BUTTON_STYLE + GREY_BORDER);
        }
    }

    @FXML
    protected void handleOnAction(ActionEvent event) {
        calendarLogic.previous();
    }
}
