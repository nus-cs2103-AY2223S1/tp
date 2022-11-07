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
public class PersonViewCard extends UiPart<Region> {

    private static final String FXML = "PersonViewCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved
     * keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The
     *      issue on AddressBook level 4</a>
     */

    private static final String PHONE = "Phone: ";
    private static final String EMAIL = "Email: ";
    private static final String ADDRESS = "Address: ";
    private static final String GENDER = "Gender: ";
    private static final String GRADUATION_DATE = "Graduation Date: ";
    private static final String CAP = "CAP: ";
    private static final String UNIVERSITY = "University: ";
    private static final String MAJOR = "Major: ";

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label job;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label gender;
    @FXML
    private Label graduationDate;
    @FXML
    private Label cap;
    @FXML
    private Label university;
    @FXML
    private Label major;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} to display.
     */
    public PersonViewCard(Person person) {
        super(FXML);
        this.person = person;
        name.setText(person.getName().fullName);
        job.setText(person.getJob().value);
        email.setText(EMAIL + person.getEmail().value);
        phone.setText(PHONE + person.getPhone().value);
        gender.setText(GENDER + person.getGender().value);
        graduationDate.setText(GRADUATION_DATE + person.getGraduationDate().value);
        cap.setText(CAP + person.getCap().toString());
        address.setText(ADDRESS + person.getAddress().value);
        university.setText(UNIVERSITY + person.getUniversity().value);
        major.setText(MAJOR + person.getMajor().value);
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
        if (!(other instanceof PersonViewCard)) {
            return false;
        }

        // state check
        PersonViewCard card = (PersonViewCard) other;
        return person.equals(card.person);
    }
}
