package tracko.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.TextAlignment;
import tracko.model.item.InventoryItem;

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

    public final InventoryItem inventoryItem;

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
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code ItemCode} with the given {@code Item} and index to display.
     */
    public ItemCard(InventoryItem inventoryItem, int displayedIndex) {
        super(FXML);
        this.inventoryItem = inventoryItem;
        id.setText(Integer.toString(displayedIndex));
        itemName.setText(inventoryItem.getItemName().value);
        itemName.setWrapText(true);
        itemName.setPadding(new Insets(0, 10, 0, 0));

        // If item name consists of more than 1 line, this will align the id to the first line.
        id.prefHeightProperty().bind(itemName.heightProperty());
        id.setAlignment(Pos.TOP_LEFT);

        quantity.setText(inventoryItem.getTotalQuantity().toString());
        quantityRow.setMaxSize(HBox.USE_PREF_SIZE, HBox.USE_PREF_SIZE);

        description.setText(inventoryItem.getDescription().value);
        description.setWrapText(true);
        description.setTextAlignment(TextAlignment.JUSTIFY);
        description.setPadding(new Insets(0, 10, 0, 0));

        sellPrice.setText(inventoryItem.getSellPrice().toString());
        sellPrice.setWrapText(true);
        sellPrice.setPadding(new Insets(0, 10, 0, 0));

        costPrice.setText(inventoryItem.getCostPrice().toString());
        costPrice.setWrapText(true);
        costPrice.setPadding(new Insets(0, 10, 0, 0));

        inventoryItem.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(constructTags(tag.tagName)));
        tags.setPadding(new Insets(0, 10, 5, 0));
    }

    /**
     * Constructs a label for {@code tagName} with customized styles.
     *
     * @param tagName Name of the tag.
     * @return The label containing the tag name.
     */
    public Label constructTags(String tagName) {
        Label tagLabel = new Label();
        tagLabel.setMaxWidth(300);
        tagLabel.setText(tagName);
        tagLabel.setWrapText(true);
        return tagLabel;
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
                && inventoryItem.equals(card.inventoryItem);
    }
}
