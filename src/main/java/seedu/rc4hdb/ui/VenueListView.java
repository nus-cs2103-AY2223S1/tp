package seedu.rc4hdb.ui;

import static java.util.Objects.requireNonNull;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.rc4hdb.model.venues.Venue;

/**
 * Controller for the venue list ui component.
 */
public class VenueListView extends UiPart<Region> {

    private static final String FXML = "VenueListView.fxml";

    @FXML
    private HBox currentVenueNameContainer;
    @FXML
    private Label currentVenueHeader;
    @FXML
    private ListView<Venue> venueListView;

    /**
     * Constructor for a VenueListView instance. The venue list is processed to remove expired bookings.
     * @param venueList The list of venues to be displayed.
     */
    public VenueListView(ObservableList<Venue> venueList, ObservableValue<Venue> currentlyDisplayedVenue) {
        super(FXML);
        requireNonNull(venueList);

        // Set up list of venues
        venueListView.setItems(venueList);
        venueListView.setCellFactory(listView -> new VenueListCell());
        setCurrentlyDisplayedVenueText(currentlyDisplayedVenue.getValue());
        configureListProperties();

        // Set up listener
        currentlyDisplayedVenue.addListener(this::updateCurrentVenue);
    }

    private class VenueListCell extends ListCell<Venue> {
        @Override
        public void updateItem(Venue venue, boolean empty) {
            super.updateItem(venue, empty);
            if (empty || venue == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new VenueListCard(venue).getRoot());
            }
        }
    }

    /**
     * Sets the currently displayed venue text in {@code currentVenueHeader}.
     */
    public void setCurrentlyDisplayedVenueText(Venue venue) {
        if (venue == null) {
            currentVenueHeader.setText("No venues in the list.");
        } else {
            currentVenueHeader.setText("Currently viewing bookings for: " + venue.getVenueName().value);
        }
    }

    /**
     * Listener for updating currently displayed venue.
     */
    public void updateCurrentVenue(ObservableValue<? extends Venue> observable, Venue oldValue, Venue newValue) {
        setCurrentlyDisplayedVenueText(newValue);
    }

    private void configureListProperties() {
        venueListView.setMinWidth(220.0);
        venueListView.setSelectionModel(new NoSelectionModel<Venue>());
    }

}
