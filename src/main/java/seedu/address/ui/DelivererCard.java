package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Deliverer;

/**
 * An UI component that displays information of a {@code Deliverer}.
 */
public class DelivererCard extends UiPart<Region> {

    private static final String FXML = "DelivererListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Deliverer deliverer;

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
    @FXML
    private Label orders;

    /**
     * Creates a {@code DelivererCode} with the given {@code Deliverer} and index to display.
     */
    public DelivererCard(Deliverer deliverer, int displayedIndex) {
        super(FXML);
        this.deliverer= deliverer;
        id.setText(displayedIndex + ". ");
        name.setText(deliverer.getName().fullName);
        phone.setText(deliverer.getPhone().value);
        address.setText(deliverer.getAddress().value);
        email.setText(deliverer.getEmail().value);
        deliverer.getTags().stream()
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
        if (!(other instanceof DelivererCard)) {
            return false;
        }

        // state check
        DelivererCard card = (DelivererCard) other;
        return id.getText().equals(card.id.getText())
                && deliverer.equals(card.deliverer);
    }
}