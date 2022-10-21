package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.TimeSlot;

/**
 * Panel containing the list of time slots.
 */
public class TimeSlotListPanel extends UiPart<Region> {

    private static final String FXML = "TimeSlotListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TimeSlotListPanel.class);

    @FXML
    private ListView<TimeSlot> timeSlotListView;

    /**
     * Creates a {@code TimeSlotListPanel} with the given {@code TimeSlotList}.
     */
    public TimeSlotListPanel(ObservableList<TimeSlot> timeSlotList) {
        super(FXML);
        timeSlotListView.setItems(timeSlotList);
        timeSlotListView.setCellFactory(listView -> new TimeSlotListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code TimeSlot} using a {@code TimeSlotCard}.
     */
    class TimeSlotListViewCell extends ListCell<TimeSlot> {
        @Override
        protected void updateItem(TimeSlot timeSlot, boolean empty) {
            super.updateItem(timeSlot, empty);

            if (empty || timeSlot == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TimeSlotCard(timeSlot).getRoot());
            }
        }
    }
}
