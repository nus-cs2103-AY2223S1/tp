package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.GithubUsername;
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
    private ImageView gender;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    @FXML
    private Label locationAt;
    @FXML
    private Label githubUsername;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        setUsername();
        gender.setImage(getGenderImage(person));
        email.setText(person.getEmail().value);
        locationAt.setText(person.getLocation().value);
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    private void setUsername() {
        String username = person.getUsername().value;
        if (!username.equals(GithubUsername.DEFAULT_USERNAME)) {
            githubUsername.setText("@" + person.getUsername().value);
        } else {
            githubUsername.setMaxHeight(0.0);
            githubUsername.setMinHeight(0.0);
        }
    }


    private Image getGenderImage(Person person) {
        String gender = person.getGender().value;
        if (gender.equals("M")) {
            return new Image(this.getClass().getResourceAsStream("/images/maleicon.png"));
        } else {
            return new Image(this.getClass().getResourceAsStream("/images/femaleicon.png"));
        }
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
