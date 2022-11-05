package seedu.realtime.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.realtime.commons.core.LogsCenter;
import seedu.realtime.model.listing.Listing;

/**
 * Panel containing the list of listings.
 */
public class ListingListPanel extends UiPart<Region> {

    private static final String FXML = "ListingListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ListingListPanel.class);

    @FXML
    private ListView<Listing> listingListView;

    /**
     * Creates a {@code ListingListPanel} with the given {@code ObservableList}.
     */
    public ListingListPanel(ObservableList<Listing> listingList) {
        super(FXML);
        listingListView.setItems(listingList);
        listingListView.setCellFactory(listView -> new ListingListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Listing} using a {@code ListingCard}.
     */
    class ListingListViewCell extends ListCell<Listing> {
        @Override
        protected void updateItem(Listing listing, boolean empty) {
            super.updateItem(listing, empty);

            if (empty || listing == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ListingCard(listing, getIndex() + 1).getRoot());
            }
        }
    }
}
