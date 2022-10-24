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
    private Label birthday;
    @FXML
    private Label healthInsurance;
    @FXML
    private Label disabilityInsurance;
    @FXML
    private Label criticalIllnessInsurance;
    @FXML
    private Label lifeInsurance;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText("\uD83D\uDCDE\t" + person.getPhone().value);
        address.setText("\uD83C\uDFE0\t" + person.getAddress().value);
        email.setText("\uD83D\uDCE7\t" + person.getEmail().value);

        // replace birthday and insurance once implemented.
        // insurance.setText("\uD83D\uDCC4\t" + "something something");
        healthInsurance.setText("Health               "
                + (person.getHealthInsurance().getHasInsurance() ? "\u2705" : "\u274e"));
        disabilityInsurance.setText("Disability           "
                + (person.getDisabilityInsurance().getHasInsurance() ? "\u2705" : "\u274e"));
        criticalIllnessInsurance.setText("Critical Illness    "
                + (person.getCriticalIllnessInsurance().getHasInsurance() ? "\u2705" : "\u274e"));
        lifeInsurance.setText("Life                    "
                + (person.getLifeInsurance().getHasInsurance() ? "\u2705" : "\u274e"));
        birthday.setText("\uD83C\uDF82\t"
                + person.getBirthday().value);
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
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
