package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonProfile extends UiPart<Region> {
    private static final String FXML = "PersonProfile.fxml";
    public static final String EMPTY_DISPLAY_VALUE = "-";

    public final Person person;

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    @FXML
    private HBox profilePane;
    @FXML
    private Label name;
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
    public PersonProfile(Person person) {
        super(FXML);
        this.person = person;
        name.setText(person.getName().getFullDisplayName());
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
    }

    private String getDisplayValue() {
        return "";
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonProfile)) {
            return false;
        }

        // state check
        PersonProfile profile = (PersonProfile) other;
        return person.equals(profile.person);
    }
}
