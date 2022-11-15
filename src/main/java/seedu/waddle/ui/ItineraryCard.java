package seedu.waddle.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.waddle.commons.core.Text;
import seedu.waddle.model.itinerary.Itinerary;

/**
 * An UI component that displays information of a {@code Itinerary}.
 */
public class ItineraryCard extends UiPart<Region> {

    private static final String FXML = "ItineraryListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Itinerary itinerary;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label country;
    @FXML
    private Label duration;
    @FXML
    private Label time;
    @FXML
    private Label people;
    @FXML
    private Label budget;


    /**
     * Creates a {@code ItineraryCode} with the given {@code Itinerary} and index to display.
     */
    public ItineraryCard(Itinerary itinerary, int displayedIndex) {
        super(FXML);
        this.itinerary = itinerary;
        id.setText(displayedIndex + ". ");
        name.setText(itinerary.getDescriptionString(Text.INDENT_NONE));
        country.setText(itinerary.getCountryString(Text.INDENT_NONE));
        time.setText(itinerary.getTimeString(Text.INDENT_NONE));
        duration.setText(itinerary.getDurationString(Text.INDENT_NONE));
        people.setText(itinerary.getPeopleString(Text.INDENT_NONE));
        budget.textProperty().bind(itinerary.getObservableBudgetString(Text.INDENT_NONE));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ItineraryCard)) {
            return false;
        }

        // state check
        ItineraryCard card = (ItineraryCard) other;
        return id.getText().equals(card.id.getText())
                && itinerary.equals(card.itinerary);
    }
}
