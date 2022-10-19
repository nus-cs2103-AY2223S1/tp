package seedu.address.ui;


import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * A button that displays the next month's CalendarEvents
 * when interacted with.
 */
public class NextButton extends Button {
    private static final String switchButtonStyle = "-fx-border-color: grey; -fx-border-radius: 5;";
    private CalendarDisplay calendarDisplay;

    /**
     * Creates a {@code NextButton} with the given {@code String} and {@code CalendarDisplay}.
     */
    public NextButton(String content, CalendarDisplay calendarDisplay) {
        super(content);
        this.calendarDisplay = calendarDisplay;
        setStyle(switchButtonStyle);
        setOnAction(e -> calendarDisplay.next());
        setOnKeyPressed(e -> nextMonth(e));
    }

    private void nextMonth(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER) || event.getCode().equals(KeyCode.N)) {
            calendarDisplay.next();
            this.requestFocus();
        } else if (event.getCode().equals(KeyCode.B)) {
            calendarDisplay.previous();
            this.requestFocus();
        }
    }
}
