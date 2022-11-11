package seedu.realtime.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.realtime.model.offer.Offer;


/**
 * A UI component that displays information of a {@code Offer}.
 */
public class OfferCard extends UiPart<Region> {

    private static final String FXML = "OfferListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Offer offer;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label listing;
    @FXML
    private Label offerPrice;

    /**
     * Creates a {@code OfferCode} with the given {@code Offer} and index to display.
     */
    public OfferCard(Offer offer, int displayedIndex) {
        super(FXML);
        this.offer = offer;
        id.setText(displayedIndex + ". ");
        name.setText(offer.getClient().fullName);
        listing.setText(offer.getListing().value);
        offerPrice.setText("S$" + offer.getOfferPrice().value);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof OfferCard)) {
            return false;
        }

        // state check
        OfferCard card = (OfferCard) other;
        return id.getText().equals(card.id.getText())
                && offer.equals(card.offer);
    }
}
