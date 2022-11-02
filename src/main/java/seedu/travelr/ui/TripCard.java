package seedu.travelr.ui;

import javafx.fxml.FXML;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.travelr.model.trip.Trip;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class TripCard extends UiPart<Region> {

    private static final String FXML = "TripListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Trip trip;

    @FXML
    private HBox cardPane;
    @FXML
    private Label title;
    @FXML
    private Label id;
    @FXML
    private Label description;
    @FXML
    private Label eventCount;
    @FXML
    private Label tripLocation;
    @FXML
    private Label tripDate;
    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public TripCard(Trip trip, int displayedIndex, Image completed) {
        super(FXML);
        this.trip = trip;
        id.setText(displayedIndex + ". ");
        title.setText(trip.getTitle().fullTitle);
        if (trip.isDone()) {
            ImageView completedIcon = new ImageView(completed);
            setIconDimensions(completedIcon, 22);
            title.setGraphic(completedIcon);
            title.setContentDisplay(ContentDisplay.RIGHT);
        }
        description.setText(trip.getDescription().value);
        eventCount.setText(" Events: " + trip.getEvents().size() + " ");

        // location is a reserved keyword in FXML
        tripLocation.setText(trip.getLocation().locationName);
        ImageView locationIcon = new ImageView("/images/location.png");
        setIconDimensions(locationIcon, 15);
        tripLocation.setGraphic(locationIcon);
        tripDate.setText(trip.getDateField().toString());
        ImageView date = new ImageView(new Image("/images/calendar.png"));
        setIconDimensions(date, 15);
        tripDate.setGraphic(date);
    }

    private void setIconDimensions(ImageView img, int size) {
        img.setFitWidth(size);
        img.setFitHeight(size);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TripCard)) {
            return false;
        }

        // state check
        TripCard card = (TripCard) other;
        return id.getText().equals(card.id.getText())
                && trip.equals(card.trip);
    }
}
