package tracko.ui;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.TextAlignment;
import tracko.model.items.Item;

/**
 * A UI component that displays information of an {@code Item}.
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
    private HBox quantityRow;
    @FXML
    private Label itemName;
    @FXML
    private Label id;
    @FXML
    private Label quantity;
    @FXML
    private Label description;
    @FXML
    private Label sellPrice;
    @FXML
    private Label costPrice;

    /**
     * Creates a {@code ItemCode} with the given {@code Item} and index to display.
     */
    public ItemCard(Item item, int displayedIndex) {
        super(FXML);
        this.item = item;
        id.setText(displayedIndex + ". ");
        itemName.setText(item.getItemName().itemName);
        itemName.setWrapText(true);
        itemName.setPadding(new Insets(0, 10, 0, 0));

        // If item name consists of more than 1 line, this will align the id to the first line.
        id.prefHeightProperty().bind(itemName.heightProperty());
        id.setAlignment(Pos.TOP_LEFT);

        quantity.setText(item.getQuantity().toString());
        quantityRow.setMaxSize(HBox.USE_PREF_SIZE, HBox.USE_PREF_SIZE);

        description.setText(item.getDescription().value);
        description.setWrapText(true);
        description.setTextAlignment(TextAlignment.JUSTIFY);
        description.setPadding(new Insets(0, 10, 0, 0));
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
