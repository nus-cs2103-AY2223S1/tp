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
     * @see <a href="https://github.com/se-edu/massLinkers-level4/issues/336">The issue on MassLinkers level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private HBox phoneContainer;
    @FXML
    private Label phone;
    @FXML
    private HBox emailContainer;
    @FXML
    private Label email;
    @FXML
    private HBox telegramContainer;
    @FXML
    private Label telegram;
    @FXML
    private HBox githubContainer;
    @FXML
    private Label github;
    @FXML
    private FlowPane interests;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        telegram.setText(person.getTelegram().handle);
        if (person.getPhone() != null) {
            phone.setText(person.getPhone().value);
        } else {
            phoneContainer.getChildren().clear();
        }
        if (person.getEmail() != null) {
            email.setText(person.getEmail().value);
        } else {
            emailContainer.getChildren().clear();
        }
        if (person.getGitHub() != null) {
            github.setText(person.getGitHub().username);
        } else {
            githubContainer.getChildren().clear();
        }

        person.getInterests()
                .stream()
                .sorted(Comparator.comparing(interest -> interest.interestName))
                .forEach(interest -> interests.getChildren().add(new Label(interest.interestName)));
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
