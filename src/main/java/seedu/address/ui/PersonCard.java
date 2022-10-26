package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

/**
 * An UI component that displays summarised information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {
    private static final String FXML = "PersonListCard.fxml";

    public final Person person;
    private MainDisplay mainDisplayListener;
    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label description;
    @FXML
    private FlowPane tags;
    @FXML
    private Label netWorth;
    @FXML
    private FlowPane meetingTimes;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().getFullName());
        phone.setText(person.getPhone().getDisplayValue());
        address.setText(person.getAddress().getDisplayValue());
        email.setText(person.getEmail().getDisplayValue());
        description.setText(person.getDescription().getDisplayValue());
        netWorth.setText(person.getNetWorth().getDisplayValue());
        person.getMeetingTimes().stream()
                .sorted(Comparator.comparing(meetingTime -> meetingTime.displayValue))
                .forEach(meetingTime -> meetingTimes.getChildren().add(new Label(meetingTime.displayValue)));
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        cardPane.setOnMouseClicked(event -> updateMainDisplay());
    }

    /**
     * Stores a reference to a main display instance that listens to on click events from PersonCard
     * @param mainDisplay the main display that changes on click
     */
    public void addMainDisplayListener(MainDisplay mainDisplay) {
        mainDisplayListener = mainDisplay;
    }

    /**
     * Updates the main display with the new person to display
     */
    private void updateMainDisplay() {
        mainDisplayListener.setPersonProfile(person);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonCard)) {
            return false;
        }

        // state check
        PersonCard card = (PersonCard) other;
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }
}
