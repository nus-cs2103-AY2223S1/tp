package seedu.address.ui;


import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import seedu.address.logic.CalendarLogic;

/**
 * A button that refreshes the Calendar
 * when interacted with.
 */
public class RefreshButton extends Button {
    private static final String switchButtonStyle = "-fx-border-color: grey; -fx-border-radius: 5;";
    private CalendarLogic calendarLogic;

    /**
     * Creates a {@code RefreshButton} with the given {@code String} and {@code CalendarLogic}.
     */
    public RefreshButton(String content, CalendarLogic calendarLogic) {
        super(content);
        this.calendarLogic = calendarLogic;
        setStyle(switchButtonStyle);
        setOnAction(e -> calendarLogic.refresh());
        setOnKeyPressed(e -> refresh(e));
    }

    private void refresh(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER) || event.getCode().equals(KeyCode.R)) {
            calendarLogic.refresh();
            this.requestFocus();
        }
    }
}
