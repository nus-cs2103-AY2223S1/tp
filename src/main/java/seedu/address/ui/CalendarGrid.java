package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

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
