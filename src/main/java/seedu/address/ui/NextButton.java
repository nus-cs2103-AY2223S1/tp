package seedu.address.ui;


import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import seedu.address.logic.CalendarLogic;

/**
 * A button that displays the next month's CalendarEvents
 * when interacted with.
 */
public class NextButton extends CalendarButton {
    private static final String FXML = "NextButton.fxml";
    private CalendarLogic calendarLogic;
    @FXML
    private Button nextButton;

    /**
     * Creates a {@code NextButton} with the given {@code String} and {@code CalendarLogic}.
     */
    public NextButton(String content, CalendarLogic calendarLogic) {
        super(FXML);
        this.calendarLogic = calendarLogic;
        nextButton.setText(content);
        nextButton.focusedProperty().addListener(this::handleFocusedEvent);
    }

    @FXML
    private void handleOnKeyPressed(KeyEvent event) {
            nextButton.requestFocus();
    }

    @FXML
    protected void handleFocusedEvent(Observable observable) {
        if (nextButton.isFocused()) {
            nextButton.setStyle(CALENDAR_BUTTON_STYLE + ORANGE_BORDER);
        } else {
            nextButton.setStyle(CALENDAR_BUTTON_STYLE + GREY_BORDER);
        }
    }

    @FXML
    protected void handleOnAction(ActionEvent event) {
        calendarLogic.next();
    }
}
