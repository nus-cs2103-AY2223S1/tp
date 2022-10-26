package seedu.address.ui;


import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import seedu.address.logic.CalendarLogic;

/**
 * A button that refreshes the Calendar
 * when interacted with.
 */
public class RefreshButton extends CalendarButton {
    private static final String FXML = "RefreshButton.fxml";
    private CalendarLogic calendarLogic;
    @FXML
    private Button refreshButton;

    /**
     * Creates a {@code PreviousButton} with the given {@code String} and {@code CalendarLogic}.
     */
    public RefreshButton(String content, CalendarLogic calendarLogic) {
        super(FXML);
        this.calendarLogic = calendarLogic;
        refreshButton.setText(content);
        refreshButton.focusedProperty().addListener(this::handleFocusedEvent);
    }

    @FXML
    private void handleOnKeyPressed(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER) || event.getCode().equals(KeyCode.R)) {
            calendarLogic.refresh();
            refreshButton.requestFocus();
        } else if (event.getCode().equals(KeyCode.N)) {
            calendarLogic.next();
            refreshButton.requestFocus();
        } else if (event.getCode().equals(KeyCode.B)) {
            calendarLogic.previous();
            refreshButton.requestFocus();
        }
    }

    @FXML
    protected void handleFocusedEvent(Observable observable) {
        if (refreshButton.isFocused()) {
            refreshButton.setStyle(CALENDAR_BUTTON_STYLE + ORANGE_BORDER);
        } else {
            refreshButton.setStyle(CALENDAR_BUTTON_STYLE + GREY_BORDER);
        }
    }

    @FXML
    protected void handleOnAction(ActionEvent event) {
        calendarLogic.refresh();
    }
}
