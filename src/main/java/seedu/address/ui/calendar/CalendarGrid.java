package seedu.address.ui.calendar;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import seedu.address.ui.UiPart;

/**
 * A UI component that represents the grid of the Calendar.
 */
public class CalendarGrid extends UiPart<GridPane> {
    private static final String FXML = "CalendarGrid.fxml";
    @FXML
    private GridPane calendarGrid;

    public CalendarGrid() {
        super(FXML);
    }
}
