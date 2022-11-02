package seedu.rc4hdb.ui;

import static seedu.rc4hdb.commons.util.CollectionUtil.requireAllNonNull;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.rc4hdb.model.venues.Venue;

/**
 * Controller for the venue tab ui component.
 */
public class VenueTabView extends UiPart<Region> {

    private static final String FXML = "VenueTabView.fxml";

    private BookingTableView bookingTableView;
    private VenueListView venueListView;

    @FXML
    private HBox venueListTableContainer;
    @FXML
    private StackPane bookingTableViewPlaceholder;
    @FXML
    private StackPane venueListViewPlaceholder;

    /**
     * Constructor for a VenueTabView instance.
     * @param venueList The list of venues to be displayed.
     * @param currentlyDisplayedVenue The venue whose bookings are to be displayed
     */
    public VenueTabView(ObservableList<Venue> venueList, ObservableItem<Venue> currentlyDisplayedVenue) {
        super(FXML);
        requireAllNonNull(venueList, currentlyDisplayedVenue);

        fillInnerParts(venueList, currentlyDisplayedVenue);
    }

    void fillInnerParts(ObservableList<Venue> venues, ObservableItem<Venue> currentlyDisplayedVenue) {
        bookingTableView = new BookingTableView(currentlyDisplayedVenue);
        bookingTableViewPlaceholder.getChildren().add(bookingTableView.getRoot());

        venueListView = new VenueListView(venues, currentlyDisplayedVenue);
        venueListViewPlaceholder.getChildren().add(venueListView.getRoot());
    }

}
