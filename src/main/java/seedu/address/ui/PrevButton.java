package seedu.address.ui;


import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * A button that displays the previous month's CalendarEvents
 * when interacted with.
 */
public class PrevButton extends Button {
    private static final String switchButtonStyle = "-fx-border-color: grey; -fx-border-radius: 5;";
    private CalendarDisplay calendarDisplay;

    /**
     * Creates a {@code PrevButton} with the given {@code String} and {@code CalendarDisplay}.
     */
    public PrevButton(String content, CalendarDisplay calendarDisplay) {
        super(content);
        this.calendarDisplay = calendarDisplay;
        setStyle(switchButtonStyle);
        setOnAction(e -> calendarDisplay.previous());
        setOnKeyPressed(e -> previousMonth(e));
    }

    private void previousMonth(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER) || event.getCode().equals(KeyCode.B)) {
            calendarDisplay.previous();
            this.requestFocus();
        } else if (event.getCode().equals(KeyCode.N)) {
            calendarDisplay.next();
            this.requestFocus();
        }
    }
}
