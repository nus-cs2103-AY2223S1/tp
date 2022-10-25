package seedu.address.ui;


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
    private CalendarLogic calendarLogic;

    @FXML
    private TextField jumpText;

    /**
     * Creates a {@code JumpText} with the given {@code CalendarLogic}.
     */
    public JumpText(CalendarLogic calendarLogic) {
        super(FXML);
        this.calendarLogic = calendarLogic;
    }


    @FXML
    protected void handleOnAction(ActionEvent event) {
        calendarLogic.jump();
        jumpText.clear();
    }

    public String getText() {
        return jumpText.getText();
    }
}
