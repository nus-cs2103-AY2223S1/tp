package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.calendar.CalendarEvent;

/**
 * Panel containing the list of persons.
 */
public class CalendarEventListPanel extends UiPart<Region> {
    private static final String FXML = "CalendarEventListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(CalendarEventListPanel.class);

    @FXML
    private ListView<CalendarEvent> calendarEventListView;

    /**
     * Creates a {@code CalendarEventListPanel} with the given {@code ObservableList}.
     */
    public CalendarEventListPanel(ObservableList<CalendarEvent> personList) {
        super(FXML);
        calendarEventListView.setItems(personList);
        calendarEventListView.setCellFactory(listView -> new CalendarEventListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code CalendarEvent} using a {@code CalendarEventCard}.
     */
    class CalendarEventListViewCell extends ListCell<CalendarEvent> {
        @Override
        protected void updateItem(CalendarEvent calendarEvent, boolean empty) {
            super.updateItem(calendarEvent, empty);

            if (empty || calendarEvent == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new CalendarEventCard(calendarEvent, getIndex() + 1).getRoot());
            }
        }
    }

}
