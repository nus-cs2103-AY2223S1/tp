package seedu.waddle.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import seedu.waddle.commons.core.LogsCenter;
import seedu.waddle.model.itinerary.Itinerary;

/**
 * Panel containing the list of Iineraries.
 */
public class ItineraryListPanel extends ListPanel {
    private static final String FXML = "ItineraryListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ItineraryListPanel.class);

    @FXML
    private ListView<Itinerary> itineraryListView;

    /**
     * Creates a {@code ItineraryListPanel} with the given {@code ObservableList}.
     */
    public ItineraryListPanel(ObservableList<Itinerary> itineraryList) {
        super(FXML);
        itineraryListView.setItems(itineraryList);
        itineraryListView.setCellFactory(listView -> new ItineraryListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Itinerary} using a {@code ItineraryCard}.
     */
    class ItineraryListViewCell extends ListCell<Itinerary> {
        @Override
        protected void updateItem(Itinerary itinerary, boolean empty) {
            super.updateItem(itinerary, empty);

            if (empty || itinerary == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ItineraryCard(itinerary, getIndex() + 1).getRoot());
            }
        }
    }

}
