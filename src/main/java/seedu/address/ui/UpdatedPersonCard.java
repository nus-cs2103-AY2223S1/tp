package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Patient;

/**
 * An UI component that displays information of a {@code Patient} without index.
 * UpdatedPersonCard to be used for output panel when adding, editing, or deleting a patient.
 */
public class UpdatedPersonCard extends UiPart<Region> {

    private static final String FXML = "UpdatedPersonCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Patient person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
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
     * Creates a {@code PersonCode} with the given {@code Patient}.
     */
    public UpdatedPersonCard(Patient person, String headerString) {
        super(FXML);
        cardPane.setSpacing(2);
        cardPane.setStyle("-fx-padding: 2;" + "-fx-border-style: solid inside;"
                + "-fx-border-width: 2;" + "-fx-border-insets: 2;"
                + "-fx-border-radius: 2;" + "-fx-border-color: black;");
        this.person = person;
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        header.setText(headerString);

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UpdatedPersonCard)) {
            return false;
        }

        // state check
        UpdatedPersonCard card = (UpdatedPersonCard) other;
        return person.equals(card.person);
    }
}
