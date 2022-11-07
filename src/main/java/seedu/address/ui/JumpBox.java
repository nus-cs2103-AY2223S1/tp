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
public class JumpBox extends UiPart<Region> {
    private static final String FXML = "JumpBox.fxml";
    private static final String JUMP_TEXT_STYLE = "-fx-border-radius: 5; -fx-border-width: 2; "
            + "-fx-background-color: #1d1d1d; -fx-text-fill: white; -fx-font-size: 13;";
    private static final String GREY_BORDER = "-fx-border-color: grey;";
    private static final String ORANGE_BORDER = "-fx-border-color: orange;";

    private CalendarLogic calendarLogic;

    @FXML
    private TextField jumpBox;

    /**
     * Creates a {@code JumpBox} with the given {@code CalendarLogic}.
     */
    public JumpBox(CalendarLogic calendarLogic) {
        super(FXML);
        this.calendarLogic = calendarLogic;
        jumpBox.setStyle(JUMP_TEXT_STYLE + GREY_BORDER);
        jumpBox.focusedProperty().addListener(this::handleFocusedEvent);
    }

    @FXML
    protected void handleFocusedEvent(Observable observable) {
        if (jumpBox.isFocused()) {
            jumpBox.setStyle(JUMP_TEXT_STYLE + ORANGE_BORDER);
        } else {
            jumpBox.setStyle(JUMP_TEXT_STYLE + GREY_BORDER);
        }
    }

    @FXML
    protected void handleOnAction(ActionEvent event) {
        jumpBox.requestFocus();
        calendarLogic.jump();
    }

    public String getText() {
        return jumpBox.getText();
    }

    public void clear() {
        jumpBox.clear();
    }

    public boolean isJumpBoxFocused() {
        return jumpBox.isFocused();
    }
}
