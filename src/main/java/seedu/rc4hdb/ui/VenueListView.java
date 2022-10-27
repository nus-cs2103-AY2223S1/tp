package seedu.rc4hdb.ui;

import static java.util.Objects.requireNonNull;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.rc4hdb.model.venues.Venue;

/**
 * Controller for the venue list ui component.
 */
public class VenueListView extends UiPart<Region> {

    private static final String FXML = "VenueListView.fxml";

    @FXML
    private ListView<Venue> venueListView;

    private ObservableList<Venue> venues;

    /**
     * Constructor for a VenueListView instance. The venue list is processed to remove expired bookings.
     * @param venueList The list of venues to be displayed.
     */
    public VenueListView(ObservableList<Venue> venueList) {
        super(FXML);
        requireNonNull(venueList);

        this.venues = venueList;
        venueList.addListener((ListChangeListener<? super Venue>) c -> updateVenueList(c.getList()));
        venueListView.setCellFactory(this::populateList);
        updateVenueList(venueList);
    }

    private ListCell<Venue> populateList(ListView<Venue> venueListView) {
        return new ListCell<>() {
            @Override
            public void updateItem(Venue venue, boolean empty) {
                super.updateItem(venue, empty);
                if (venue == null || empty) {
                    setText(null);
                } else {
                    setText(venue.getVenueName().toString());
                }
            }
        };
    }

    public void updateVenueList(ObservableList<? extends Venue> venues) {
        venueListView.setItems((ObservableList<Venue>) venues);
    }

}
