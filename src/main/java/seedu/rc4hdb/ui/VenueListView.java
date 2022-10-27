package seedu.rc4hdb.ui;

import static java.util.Objects.requireNonNull;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.rc4hdb.model.venues.Venue;
import seedu.rc4hdb.model.venues.VenueName;

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

    private ObservableList<Venue> venues;
    private ObservableItem<VenueName> currentlyDisplayedVenueName;

    /**
     * Constructor for a VenueListView instance. The venue list is processed to remove expired bookings.
     * @param venueList The list of venues to be displayed.
     */
    public VenueListView(ObservableList<Venue> venueList, ObservableItem<VenueName> currentlyDisplayedVenueName) {
        super(FXML);
        requireNonNull(venueList);

        this.venues = venueList;
        this.currentlyDisplayedVenueName = currentlyDisplayedVenueName;

        venues.addListener((ListChangeListener<? super Venue>) c -> updateVenueList());
        currentlyDisplayedVenueName.addListener(c -> updateCurrentVenueName());

        venueListView.setCellFactory(this::populateList);

        updateVenueList();
        updateCurrentVenueName();
    }

    private ListCell<Venue> populateList(ListView<Venue> venueListView) {
        ListCell<Venue> listCell = new ListCell<>() {
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
        };
        listCell.applyCss();
        return listCell;
    }

    public void updateVenueList() {
        venueListView.setItems(venues);
    }

    public void updateCurrentVenueName() {
        currentVenueHeader.setText("Currently viewing bookings for: " + currentlyDisplayedVenueName.getValue());
    }

}
