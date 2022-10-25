package seedu.address.ui;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import seedu.address.logic.CalendarLogic;
import seedu.address.ui.UiPart;


public class JumpText extends UiPart<Region> {
    private static final String FXML = "JumpText.fxml";
    private CalendarLogic calendarLogic;

    @FXML
    private TextField jumpText;

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
