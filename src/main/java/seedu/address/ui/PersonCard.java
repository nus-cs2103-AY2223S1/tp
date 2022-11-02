package seedu.address.ui;

import java.util.Comparator;
import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

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
    private Label income;
    @FXML
    private Label meetingDate;
    @FXML
    private Label meetingLocation;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        Meeting meeting = person.getMeeting();
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        income.setText(person.getIncome().value);
        meetingDate.setText(meeting.getMeetingDate().get());
        meetingLocation.setText(meeting.getMeetingLocation().getVirtualStatus());
        person.getTags().stream()
            .sorted(Comparator.comparing(tag -> tag.tagName))
            .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex, Boolean hidden) {
        super(FXML);
        this.person = person;
        Meeting meeting = person.getMeeting();
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(mask(person.getPhone().value));
        address.setText(mask(person.getAddress().value));
        email.setText(mask(person.getEmail().value));
        income.setText(mask(person.getIncome().value));
        meetingDate.setText(meeting.getMeetingDate().get());
        meetingLocation.setText(meeting.getMeetingLocation().getVirtualStatus());
        person.getTags().stream()
            .sorted(Comparator.comparing(tag -> tag.tagName))
            .forEach(tag -> tags.getChildren().add(new Label(mask(tag.tagName))));

    }

    /**
     * Masks sensitive client data.
     *
     * @param clientData
     * @return String of censored client data.
     */
    public String mask(String clientData) {
        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i < clientData.length(); i++) {
            strBuilder.append("*");
        }
        return strBuilder.toString();
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
