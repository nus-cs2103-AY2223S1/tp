package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.event.Event;


/**
 * A UI component that displays information of an {@code Event}.
 */
public class EventCard extends UiPart<Region> {

    private static final String FXML = "EventListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */
    public final Event event;

    @FXML
    private HBox cardPane;
    @FXML
    private Label eventTitle;
    @FXML
    private Label date;
    @FXML
    private Label time;
    @FXML
    private Label purpose;
    @FXML
    private Label id;
    @FXML
    private Label personNames;


    /**
     * Creates a {@code EventCard} with the given {@code Event} and index to display.
     */
    public EventCard(Event event, int displayedIndex) {
        super(FXML);
        this.event = event;
        setField(id, displayedIndex + ".");
        setField(eventTitle, event.getEventTitle().toString());
        setField(date, event.getStartDate().toString());
        setField(time, event.getStartTime().toString());
        setField(purpose, event.getPurpose().toString());
        setField(personNames, event.getUids().getPersonNames());
    }

    /**
     * Sets a field in the EventCard with the specified {@code Label} with the specified {@code String}.
     */
    private void setField(Label label, String input) {
        label.setText(input);
        label.setWrapText(true);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EventCard)) {
            return false;
        }

        // state check
        EventCard card = (EventCard) other;
        return id.getText().equals(card.id.getText())
                && event.equals(card.event);
    }
}
