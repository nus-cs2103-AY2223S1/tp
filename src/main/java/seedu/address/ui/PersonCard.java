package seedu.address.ui;

import static seedu.address.logic.parser.ParserUtil.DATE_FORMAT_PATTERN;

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
        assert person != null : "Something went wrong in UI PersonCard";
        this.person = person;

        id.setText(displayedIndex + ". ");
        assert person.getName() != null : "Something went wrong in UI PersonCard name";
        name.setText(person.getName().fullName);
        assert person.getPhone() != null : "Something went wrong in UI PersonCard phone";
        phone.setText("\uD83D\uDCDE\t" + person.getPhone().value);
        assert person.getAddress() != null : "Something went wrong in UI PersonCard address";
        address.setText("\uD83C\uDFE0\t" + person.getAddress().value);
        assert person.getEmail() != null : "Something went wrong in UI PersonCard email";
        email.setText("\uD83D\uDCE7\t" + person.getEmail().value);
        assert person.getBirthday() != null : "Something went wrong in UI PersonCard birthday";
        birthday.setText("\uD83C\uDF82\t"
                + person.getBirthday().value.format(DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN)));

        assert person.getHealthInsurance() != null : "Something went wrong in UI PersonCard health insurance";
        assert person.getDisabilityInsurance() != null : "Something went wrong in UI PersonCard disability insurance";
        assert person.getCriticalIllnessInsurance() != null
                : "Something went wrong in UI PersonCard critical illness insurance";
        assert person.getLifeInsurance() != null : "Something went wrong in UI PersonCard life insurance";
        healthInsurance.setText((person.getHealthInsurance().getHasInsurance() ? "\u2705\t" : "\u274c\t")
                + "Health Policy");
        disabilityInsurance.setText((person.getDisabilityInsurance().getHasInsurance() ? "\u2705\t" : "\u274c\t")
                + "Disability Policy");
        criticalIllnessInsurance.setText((person.getCriticalIllnessInsurance().getHasInsurance()
                ? "\u2705\t" : "\u274c\t")
                + "Critical Illness Policy");
        lifeInsurance.setText((person.getLifeInsurance().getHasInsurance() ? "\u2705\t" : "\u274c\t")
                + "Life Policy");

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
