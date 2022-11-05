package seedu.address.ui;


import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import seedu.address.logic.CalendarLogic;

/**
 * A textField that reads user input for the new month
 * when interacted with.
 */
public class JumpText extends UiPart<Region> {
    private static final String FXML = "JumpText.fxml";
    private static final String JUMP_TEXT_STYLE = "-fx-border-radius: 5; -fx-border-width: 2; "
            + "-fx-background-color: #1d1d1d; -fx-text-fill: white; -fx-font-size: 13;";
    private static final String GREY_BORDER = "-fx-border-color: grey;";
    private static final String ORANGE_BORDER = "-fx-border-color: orange;";

    private CalendarLogic calendarLogic;

    @FXML
    private TextField jumpText;

    /**
     * Creates a {@code JumpText} with the given {@code CalendarLogic}.
     */
    public JumpText(CalendarLogic calendarLogic) {
        super(FXML);
        this.calendarLogic = calendarLogic;
        jumpText.setStyle(JUMP_TEXT_STYLE + GREY_BORDER);
        jumpText.focusedProperty().addListener(this::handleFocusedEvent);
    }

    @FXML
    protected void handleFocusedEvent(Observable observable) {
        if (jumpText.isFocused()) {
            jumpText.setStyle(JUMP_TEXT_STYLE + ORANGE_BORDER);
        } else {
            jumpText.setStyle(JUMP_TEXT_STYLE + GREY_BORDER);
        }
    }

    @FXML
    protected void handleOnAction(ActionEvent event) {
        jumpText.requestFocus();
        calendarLogic.jump();
    }

    public String getText() {
        return jumpText.getText();
    }

    public void clear() {
        jumpText.clear();
    }

    public boolean isJumpTextFocused() {
        return jumpText.isFocused();
    }
}
