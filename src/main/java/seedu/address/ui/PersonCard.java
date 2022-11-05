package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

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
    private FlowPane tags;
    @FXML
    private FlowPane meetingTimes;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        setCardFields();
        cardPane.setOnMousePressed(event -> updateMainDisplay());
    }

    /**
     * Sets all the fields in person card.
     */
    private void setCardFields() {
        setNameField();
        setMeetingsField();
        setTagsField();
    }

    /**
     * Sets name value in name if value is not empty.
     */
    private void setNameField() {
        if (person.getName().isEmpty()) {
            name.setManaged(false);
            return;
        }
        name.setText(person.getName().getFullName());
    }

    /**
     * Sets person meetingTimes in meetingTimes if meetingTimes is not empty.
     */
    private void setMeetingsField() {
        if (person.getMeetingTimes().isEmpty()) {
            meetingTimes.setManaged(false);
            return;
        }
        meetingTimes.getChildren().add(new Label(person.getEarliestMeeting().displayValue));
    }

    /**
     * Sets person tags in tags if tags is not empty.
     */
    private void setTagsField() {
        if (person.getTags().isEmpty()) {
            tags.setManaged(false);
            return;
        }
        Tag firstTag = person.getTags().iterator().next();
        setTagStyle(firstTag);
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    /**
     * Sets style of tags based on potential or secured client.
     * @param firstTag first tag in Tag Set
     */
    public void setTagStyle(Tag firstTag) {
        if (firstTag.isPotential()) {
            tags.setId("potentialTags");
        } else if (firstTag.isSecured()) {
            tags.setId("securedTags");
        }
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
