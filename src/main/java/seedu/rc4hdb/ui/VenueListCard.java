package seedu.rc4hdb.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.rc4hdb.model.venues.Venue;

/**
 * A ui component that displays venue names.
 */
public class VenueListCard extends UiPart<Region> {

    private static final String FXML = "VenueListCard.fxml";

    @FXML
    private HBox venueListCard;
    @FXML
    private Label venueNameLabel;

    private final Venue venue;

    /**
     * Constructs a card that displays the given {@code venue}'s name.
     * @param venue The venue to be displayed.
     */
    public VenueListCard(Venue venue) {
        super(FXML);
        requireNonNull(venue);

        this.venue = venue;
        venueNameLabel.setText(venue.getVenueName().value);
    }

}
