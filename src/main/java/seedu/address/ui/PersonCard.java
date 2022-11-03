package seedu.address.ui;

import java.time.format.DateTimeFormatter;
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
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    private static final String DATE_FORMAT = "y-M-d";

    private static final int WRAP_STRING_LENGTH = 30;

    private static final String ID_DONE = "done";
    private static final String ID_NOT_DONE = "notDone";

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
    private FlowPane surveys;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex, boolean isExpanded) {
        super(FXML);
        this.person = person;
        this.isExpanded = isExpanded;
        if (this.isExpanded) {
            showLabels();
        } else {
            hideLabels();
        }

        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        person.getSurveys().stream()
                .sorted(Comparator.comparing(survey -> survey.survey))
                .forEach(survey -> {
                    Label label = new Label(survey.survey);
                    label.setId(survey.isDone ? ID_DONE : ID_NOT_DONE);
                    label.setWrapText(true);
                    if (survey.survey.length() > WRAP_STRING_LENGTH) {
                        label.setPrefWidth(350);
                    }
                    surveys.getChildren().add(label);
                });
        phone.setText("Phone: " + person.getPhone().value);
        address.setText("Address: " + person.getAddress().value);
        email.setText("Email: " + person.getEmail().value);
        gender.setText("Gender: " + person.getGender().gender);
        birthdate.setText("Birthdate: "
                + person.getBirthdate().birthdate.format(DateTimeFormatter.ofPattern(DATE_FORMAT)));
        race.setText("Race: " + person.getRace().race);
        religion.setText("Religion: " + person.getReligion().religion);
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> {
                    Label label = new Label(tag.tagName);
                    label.setWrapText(true);
                    if (tag.tagName.length() > WRAP_STRING_LENGTH) {
                        label.setPrefWidth(350);
                    }
                    tags.getChildren().add(label);
                });
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
