package seedu.address.ui.calendar;

import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import seedu.address.ui.UiPart;

/**
 * A UI component that represents the header of the Calendar.
 */
public class TopCalendar extends UiPart<FlowPane> {
    private static final String FXML = "TopCalendar.fxml";
    @FXML
    private FlowPane topCalendar;

    public TopCalendar() {
        super(FXML);
    }
}
