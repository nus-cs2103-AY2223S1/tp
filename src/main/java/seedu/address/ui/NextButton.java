package seedu.address.ui;


import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import seedu.address.logic.CalendarLogic;

/**
 * A button that displays the next month's CalendarEvents
 * when interacted with.
 */
public class NextButton extends Button {
    private static final String switchButtonStyle = "-fx-border-color: grey; -fx-border-radius: 5;";
    private CalendarLogic calendarLogic;

    /**
     * Creates a {@code NextButton} with the given {@code String} and {@code CalendarLogic}.
     */
    public NextButton(String content, CalendarLogic calendarLogic) {
        super(content);
        this.calendarLogic = calendarLogic;
        setStyle(switchButtonStyle);
        setOnAction(e -> calendarLogic.next());
        setOnKeyPressed(e -> nextMonth(e));
    }

    private void nextMonth(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER) || event.getCode().equals(KeyCode.N)) {
            calendarLogic.next();
            this.requestFocus();
        } else if (event.getCode().equals(KeyCode.B)) {
            calendarLogic.previous();
            this.requestFocus();
        }
    }
}
