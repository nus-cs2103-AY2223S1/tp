package seedu.address.ui;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import seedu.address.logic.CalendarLogic;

/**
 * A button that displays the previous month's CalendarEvents
 * when interacted with.
 */
public class PreviousButton extends UiPart<Region> {
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
    }

    @FXML
    private void handleOnKeyPressed(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER) || event.getCode().equals(KeyCode.B)) {
            calendarLogic.previous();
            previousButton.requestFocus();
        } else if (event.getCode().equals(KeyCode.N)) {
            calendarLogic.next();
            previousButton.requestFocus();
        } else if (event.getCode().equals(KeyCode.R)) {
            calendarLogic.refresh();
            previousButton.requestFocus();
        }
    }

    @FXML
    private void handleOnAction(ActionEvent event) {
        calendarLogic.previous();
    }
}
