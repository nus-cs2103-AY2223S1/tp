package seedu.foodrem.views;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
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
        name.setWrapText(true);
        name.setMaxWidth(400);

        // Quantity and unit at the top left and top right respectively
        final Label quantityLabel = new Label("Quantity: " + buildItemQuantityAndUnitStringFrom(item));
        final Label priceLabel = new Label("Price: $" + item.getPrice().toString());
        final Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);
        final HBox quantityAndPrice = new HBox(quantityLabel, region, priceLabel);

        // Tag under quantity and price
        final FlowPane tags = new FlowPane(TagsView.from(item.getTagSet()));
        tags.setMaxWidth(400);
        tags.setHgap(SPACING_UNIT);
        tags.setVgap(SPACING_UNIT);

        // Remarks at the bottom
        final Label remarks = new Label(
                String.valueOf(item.getRemarks()).isBlank() ? "-" : item.getRemarks().toString());
        remarks.setWrapText(true);
        remarks.setMaxWidth(400);

        // Combine everything
        final VBox itemView = new VBox(
                name,
                quantityAndPrice,
                new Separator(),
                tags,
                new Separator(),
                new Label("Bought Date: " + buildBoughtDateStringFrom(item)),
                new Label("Expiry Date: " + buildExpiryDateStringFrom(item)),
                new Separator(),
                new Label("Remarks:"),
                remarks);
        itemView.setSpacing(SPACING_UNIT);
        return itemView;
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
