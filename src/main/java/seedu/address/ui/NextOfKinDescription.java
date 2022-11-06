package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.nextofkin.NextOfKin;


/**
 * An UI component that displays information of a {@code NextOfKin}.
 */
public class NextOfKinDescription extends UiPart<Region> {

    private static final String FXML = "NextOfKinDescription.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final NextOfKin nok;
    @FXML
    protected HBox cardPane;
    @FXML
    protected Label name;
    @FXML
    protected Label phone;
    @FXML
    protected Label address;
    @FXML
    protected Label email;
    @FXML
    protected FlowPane nokTags;

    /**
     * Creates a {@code NextOfKinCode} with the given {@code NextOfKin} and index to display.
     */
    public NextOfKinDescription(NextOfKin nok) {
        super(FXML);
        this.nok = nok;
        name.setText(nok.getName().fullName);
        phone.setText(nok.getPhone().value);
        address.setText(nok.getAddress().value);
        email.setText(nok.getEmail().value);
        nokTags.getChildren().add(new Label(nok.getRelationship().relationship));
        nok.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> nokTags.getChildren().add(new Label(tag.tagName)));
    }

    public NextOfKin getDisplayedNextOfKin() {
        return this.nok;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NextOfKinDescription)) {
            return false;
        }

        // state check
        NextOfKinDescription card = (NextOfKinDescription) other;
        return nok.equals(card.nok);
    }
}
