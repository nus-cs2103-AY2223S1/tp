package seedu.address.ui;


import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import seedu.address.logic.CalendarLogic;

/**
 * A button that displays the previous month's CalendarEvents
 * when interacted with.
 */
public class PrevButton extends Button {
    private static final String switchButtonStyle = "-fx-border-color: grey; -fx-border-radius: 5;";
    private CalendarLogic calendarLogic;

    /**
     * Creates a {@code PrevButton} with the given {@code String} and {@code CalendarLogic}.
     */
    public PrevButton(String content, CalendarLogic calendarLogic) {
        super(content);
        this.calendarLogic = calendarLogic;
        setStyle(switchButtonStyle);
        setOnAction(e -> calendarLogic.previous());
        setOnKeyPressed(e -> previousMonth(e));
    }

    private void previousMonth(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER) || event.getCode().equals(KeyCode.B)) {
            calendarLogic.previous();
            this.requestFocus();
        } else if (event.getCode().equals(KeyCode.N)) {
            calendarLogic.next();
            this.requestFocus();
        }
    }
}
