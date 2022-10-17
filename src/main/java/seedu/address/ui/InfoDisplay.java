package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

/**
 * A ui for the details of a specified person displayed at the right panel of TAB.
 */
public class InfoDisplay extends UiPart<Region> {

    private static final String FXML = "InfoDisplay.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    private Person person;

    @FXML
    private Label name;
    @FXML
    private Label position;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private Label address;

    public InfoDisplay() {
        super(FXML);
    }

    /**
     * Displays information of a specified person.
     */
    public void setInfo(Person person) {
        this.person = person;
        name.setText(person.getName().fullName);
        position.setText(person.getPosition().toString());
        phone.setText("Phone: " + person.getPhone().value);
        email.setText("Email: " + person.getEmail().value);
        address.setText("Address: " + person.getAddress().value);
    }

    /**
     * Clears any information being displayed.
     */
    public void clearInfo() {
        name.setText(null);
        position.setText(null);
        phone.setText(null);
        email.setText(null);
        address.setText(null);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InfoDisplay)) {
            return false;
        }

        // state check
        InfoDisplay current = (InfoDisplay) other;
        return person.equals(current.person);
    }
}
