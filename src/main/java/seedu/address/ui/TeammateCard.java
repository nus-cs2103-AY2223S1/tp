package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.teammate.Teammate;

/**
 * An UI component that displays information of a {@code Teammate}.
 */
public class TeammateCard extends UiPart<Region> {

    private static final String FXML = "TeammateListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Teammate teammate;

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
    private FlowPane tags;

    /**
     * Creates a {@code TeammateCard} with the given {@code Teammate} and index to display.
     */
    public TeammateCard(Teammate teammate, int displayedIndex) {
        super(FXML);
        this.teammate = teammate;
        id.setText(displayedIndex + ". ");
        name.setText(teammate.getName().fullName);
        phone.setText(teammate.getPhone().value);
        address.setText(teammate.getAddress().value);
        email.setText(teammate.getEmail().value);
        teammate.getTags().stream()
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
        if (!(other instanceof TeammateCard)) {
            return false;
        }

        // state check
        TeammateCard card = (TeammateCard) other;
        return id.getText().equals(card.id.getText())
                && teammate.equals(card.teammate);
    }
}
