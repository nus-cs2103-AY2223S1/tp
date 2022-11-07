package seedu.uninurse.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.uninurse.model.person.Person;

/**
 * An UI component that displays information of a Person.
 */
public class PersonListCard extends UiPart<Region> {
    private static final String FXML = "PersonListCard.fxml";
    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on UninurseBook level 4</a>
     */
    private final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private VBox personDetails;
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
    private FlowPane tags;
    @FXML
    private Label header;

    /**
     * Creates a PersonListCard with the given Person and index to display.
     */
    public PersonListCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;

        this.cardPane.setSpacing(1);
        this.cardPane.setId("person_list_card");

        this.id.setText(displayedIndex + ". ");
        this.name.setText(person.getName().getValue());
        this.phone.setText(person.getPhone().getValue());
        this.address.setText(person.getAddress().getValue());
        this.email.setText(person.getEmail().getValue());
        person.getTags().getInternalList()
                .forEach(tag -> tags.getChildren().add(new Label(tag.getValue())));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonListCard)) {
            return false;
        }

        // state check
        PersonListCard card = (PersonListCard) other;
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }
}
