package seedu.travelr.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import seedu.travelr.model.trip.Trip;

/**
 * Panel containing the list of Trips.
 */
public class CompletedTripListPanel extends UiPart<Region> {
    private static final String FXML = "CompletedTripListPanel.fxml";
    private final Image completed;

    @FXML
    private ListView<Trip> tripListView;

    /**
     * Creates a {@code TripListPanel} with the given {@code ObservableList}.
     */
    public CompletedTripListPanel(ObservableList<Trip> tripList, Image completed) {
        super(FXML);
        tripList = tripList.filtered(Trip::isDone);
        tripListView.setItems(tripList);
        tripListView.setCellFactory(listView -> new TripListViewCell());
        this.completed = completed;
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Trip} using a {@code TripCard}.
     */
    class TripListViewCell extends ListCell<Trip> {
        @Override
        protected void updateItem(Trip trip, boolean empty) {
            super.updateItem(trip, empty);

            if (empty || trip == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TripCard(trip, getIndex() + 1, completed).getRoot());
            }
        }
    }

}
