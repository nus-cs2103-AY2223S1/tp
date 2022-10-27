package seedu.foodrem.views;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import seedu.foodrem.model.item.Item;

/**
 * A view of an {@code Item}. This can be displayed.
 * @author Richard Dominick
 */
public class ItemView {
    private static final double SPACING_UNIT = 8;

    private ItemView() {} // Prevents instantiation

    /**
     * Creates a new detailed view of the given item.
     * @param item the item to be displayed.
     * @return the node to be displayed in the UI.
     */
    public static Node from(Item item) {
        final Label name = new Label(item.getName().toString());
        name.getStyleClass().add("item-detail-name");
        name.prefWidth(Double.MAX_VALUE);

        // Name and tags at the top left
        final HBox tags = new HBox(new Label("Tags: "), TagsView.from(item.getTagSet()));
        tags.setAlignment(Pos.CENTER_LEFT);

        // Quantity and unit at the top right
        final Label quantityLabel = new Label("Quantity\nRemaining:");
        quantityLabel.setTextAlignment(TextAlignment.RIGHT);
        final Label quantityAndUnit = new Label(buildItemQuantityAndUnitStringFrom(item));
        quantityAndUnit.getStyleClass().add("item-detail-quantity");
        final VBox quantityBox = new VBox(quantityLabel, quantityAndUnit);
        quantityBox.setAlignment(Pos.CENTER_RIGHT);

        // Set up top half
        final VBox col1 = new VBox(name, tags);
        col1.setSpacing(SPACING_UNIT);
        final HBox row1 = new HBox(col1, quantityBox);
        HBox.setHgrow(col1, Priority.ALWAYS);
        row1.setSpacing(SPACING_UNIT);

        final Label remarks = new Label(
                String.valueOf(item.getRemarks()).isBlank() ? "-" : item.getRemarks().toString());
        remarks.setWrapText(true);

        // Combine everything
        final VBox itemView = new VBox(
                row1,
                new Label("$" + item.getPrice().toString()),
                new Separator(),
                new Label("Bought Date: " + buildBoughtDateStringFrom(item)),
                new Label("Expiry Date: " + buildExpiryDateStringFrom(item)),
                new Separator(),
                new Label("Remarks:"),
                remarks);
        itemView.setSpacing(SPACING_UNIT);
        return itemView;
    }

    private static Node buildTagNodeFrom(String tagName) {
        final Label label = new Label(tagName);
        label.getStyleClass().add("item-detail-tag");
        return label;
    }

    /**
     * Builds the string representation of the item's quantity attached to its units.
     * @param item the item whose quantity is to be formatted.
     * @return the string representation of the item's quantity and units.
     */
    public static String buildItemQuantityAndUnitStringFrom(Item item) {
        final String unit = String.valueOf(item.getUnit()).isBlank() ? "" : " " + item.getUnit().toString();
        return String.format("%s%s", item.getQuantity(), unit);
    }

    /**
     * Builds the string representation of the item's bought date.
     * @param item the item whose bought date is to be formatted.
     * @return the string representation of the item's bought date.
     */
    public static String buildBoughtDateStringFrom(Item item) {
        return item.getBoughtDate().isNotSet() ? "Not Set" : item.getBoughtDate().toString();
    }

    /**
     * Builds the string representation of the item's expiry date.
     * @param item the item whose expiry date is to be formatted.
     * @return the string representation of the item's expiry date.
     */
    public static String buildExpiryDateStringFrom(Item item) {
        return item.getExpiryDate().isNotSet() ? "Not Set" : item.getExpiryDate().toString();
    }
}
