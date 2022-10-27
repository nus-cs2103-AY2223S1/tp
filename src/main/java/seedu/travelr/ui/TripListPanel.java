package seedu.travelr.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.travelr.commons.core.LogsCenter;
import seedu.travelr.model.trip.ObservableTrip;
import seedu.travelr.model.trip.Trip;

/**
 * Panel containing the list of persons.
 */
public class TripListPanel extends UiPart<Region> {
    private static final String FXML = "TripListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TripListPanel.class);

    private Image completed;
    private Image notCompleted;

    @FXML
    private StackPane selectedTripCardPlaceholder;
    @FXML
    private ListView<Trip> tripListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public TripListPanel(ObservableList<Trip> tripList, ObservableTrip selectedTrip, Image completed) {
        super(FXML);
        tripListView.setItems(tripList);
        tripListView.setCellFactory(listView -> new TripListViewCell());
        selectedTripCardPlaceholder.getChildren().add(new SelectedTripCard(selectedTrip, completed).getRoot());
        this.completed = completed;
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
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
