package seedu.realtime.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.realtime.model.listing.Listing;

/**
 * An UI component that displays information of a {@code Listing}.
 */
public class ListingCard extends UiPart<Region> {

    private static final String FXML = "ListingListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/realTime-level4/issues/336">The issue on realTime level 4</a>
     */

    public final Listing listing;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label listingId;
    @FXML
    private Label name;
    @FXML
    private Label address;
    @FXML
    private Label askingPrice;
    @FXML
    private FlowPane tags;


    /**
     * Creates a {@code ClientCode} with the given {@code Client} and index to display.
     */
    public ListingCard(Listing listing, int displayedIndex) {
        super(FXML);
        this.listing = listing;
        id.setText(displayedIndex + ". ");
        listingId.setText(listing.getId().value);
        name.setText(listing.getName().fullName);
        address.setText(listing.getAddress().value);
        askingPrice.setText(listing.getAskingPrice().value);
        listing.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ListingCard)) {
            return false;
        }

        // state check
        ListingCard card = (ListingCard) other;
        return id.getText().equals(card.id.getText())
                && listing.equals(card.listing);
    }
}
