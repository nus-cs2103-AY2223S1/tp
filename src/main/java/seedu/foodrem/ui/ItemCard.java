package seedu.foodrem.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.foodrem.model.item.Item;

/**
 * A UI component that displays information of a {@code Item}.
 */
public class ItemCard extends UiPart<Region> {

    private static final String FXML = "ItemListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Item item;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label quantityAndUnit;
    @FXML
    private Label bought;
    @FXML
    private Label expiry;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code ItemCode} with the given {@link Item} and index to display.
     */
    public ItemCard(Item item, int displayedIndex) {
        super(FXML);
        this.item = item;
        id.setText(displayedIndex + ". ");
        name.setText(String.valueOf(item.getName()));
        quantityAndUnit.setText(item.getQuantity() + " " + item.getUnit());
        bought.setText(String.format("(Bought Date: %s)",
                String.valueOf(item.getBoughtDate()).isBlank() ? "Not Set" : item.getBoughtDate()));
        expiry.setText(String.format("(Expiry Date: %s)",
                String.valueOf(item.getExpiryDate()).isBlank() ? "Not Set" : item.getExpiryDate()));
        item.getTagSet().stream()
                .sorted(Comparator.comparing(tag -> tag.getName()))
                .forEach(tag -> tags.getChildren().add(new Label(tag.getName())));

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ItemCard)) {
            return false;
        }

        // state check
        ItemCard card = (ItemCard) other;
        return id.getText().equals(card.id.getText())
                && item.equals(card.item);
    }
}
