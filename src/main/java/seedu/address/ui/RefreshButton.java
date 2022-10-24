package seedu.address.ui;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import seedu.address.logic.CalendarLogic;

/**
 * A button that refreshes the Calendar
 * when interacted with.
 */
public class RefreshButton extends UiPart<Region> {
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
    private void handleOnAction(ActionEvent event) {
        calendarLogic.refresh();
    }
}
