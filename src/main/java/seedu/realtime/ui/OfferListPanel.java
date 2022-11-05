package seedu.realtime.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.realtime.commons.core.LogsCenter;
import seedu.realtime.model.offer.Offer;

/**
 * Panel containing the list of offers.
 */
public class OfferListPanel extends UiPart<Region> {
    private static final String FXML = "OfferListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(OfferListPanel.class);

    @FXML
    private ListView<Offer> offerListView;

    /**
     * Creates a {@code OfferListPanel} with the given {@code ObservableList}.
     */
    public OfferListPanel(ObservableList<Offer> offerList) {
        super(FXML);
        offerListView.setItems(offerList);
        offerListView.setCellFactory(listView -> new OfferListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Offer} using a {@code OfferCard}.
     */
    class OfferListViewCell extends ListCell<Offer> {
        @Override
        protected void updateItem(Offer offer, boolean empty) {
            super.updateItem(offer, empty);

            if (empty || offer == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new OfferCard(offer, getIndex() + 1).getRoot());
            }
        }
    }

}
