package seedu.address.ui;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import seedu.address.logic.CalendarLogic;

/**
 * A button that displays the next month's CalendarEvents
 * when interacted with.
 */
public class NextButton extends UiPart<Region> {
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
    }

    @FXML
    private void handleOnKeyPressed(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER) || event.getCode().equals(KeyCode.N)) {
            calendarLogic.next();
            nextButton.requestFocus();
        } else if (event.getCode().equals(KeyCode.B)) {
            calendarLogic.previous();
            nextButton.requestFocus();
        } else if (event.getCode().equals(KeyCode.R)) {
            calendarLogic.refresh();
            nextButton.requestFocus();
        }
    }

    @FXML
    private void handleOnAction(ActionEvent event) {
        calendarLogic.next();
    }
}
