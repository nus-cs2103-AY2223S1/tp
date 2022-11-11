package swift.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import swift.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class TaskPersonCard extends UiPart<Region> {

    private static final String FXML = "TaskPersonCard.fxml";

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
    private Label email;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public TaskPersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        email.setText(person.getEmail().value);
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> {
                    Label label = new Label(tag.tagName.toUpperCase());
                    setStyle(label);
                    tags.getChildren().add(label);
                });
    }

    private void setStyle(Label... labels) {
        for (Label label: labels) {
            label.setStyle("-fx-background-color:transparent; -fx-text-fill: #AFB4FF;"
                    + "-fx-font-family: Arial; -fx-font-size: 10; -fx-font-weight: bold;");
        }
    }
    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TaskPersonCard)) {
            return false;
        }

        // state check
        TaskPersonCard card = (TaskPersonCard) other;
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }
}
