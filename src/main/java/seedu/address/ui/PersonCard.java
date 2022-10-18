package seedu.address.ui;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    private static final String DATE_FORMAT = "y-M-d";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    private boolean isExpanded;

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
    private Label gender;
    @FXML
    private Label birthdate;
    @FXML
    private Label race;
    @FXML
    private Label religion;
    @FXML
    private Label survey;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        isExpanded = false;
        hideLabels();

        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        survey.setText(person.getSurvey().survey);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        gender.setText(person.getGender().gender);
        birthdate.setText(person.getBirthdate().birthdate.format(DateTimeFormatter.ofPattern(DATE_FORMAT)));
        race.setText(person.getRace().race);
        religion.setText(person.getReligion().religion);
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @FXML
    private void handleMouseClicked() {
        if (isExpanded) {
            isExpanded = false;
            hideLabels();
        } else {
            isExpanded = true;
            showLabels();
        }
    }

    private void hideLabels() {
        phone.setVisible(false);
        phone.setManaged(false);
        address.setVisible(false);
        address.setManaged(false);
        email.setVisible(false);
        email.setManaged(false);
        gender.setVisible(false);
        gender.setManaged(false);
        birthdate.setVisible(false);
        birthdate.setManaged(false);
        race.setVisible(false);
        race.setManaged(false);
        religion.setVisible(false);
        religion.setManaged(false);
        tags.setVisible(false);
        tags.setManaged(false);
    }

    private void showLabels() {
        phone.setVisible(true);
        phone.setManaged(true);
        address.setVisible(true);
        address.setManaged(true);
        email.setVisible(true);
        email.setManaged(true);
        gender.setVisible(true);
        gender.setManaged(true);
        birthdate.setVisible(true);
        birthdate.setManaged(true);
        race.setVisible(true);
        race.setManaged(true);
        religion.setVisible(true);
        religion.setManaged(true);
        tags.setVisible(true);
        tags.setManaged(true);
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
