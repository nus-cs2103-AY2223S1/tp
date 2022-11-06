package seedu.foodrem.views;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import seedu.foodrem.model.item.Item;

/**
 * A view of an {@code Item}. This can be displayed.
 *
 * @author Richard Dominick
 */
public class ItemView {
    private static final double SPACING_UNIT = 8;

    private ItemView() {
    } // Prevents instantiation

    /**
     * Creates a new detailed view of the given item.
     *
     * @param item the item to be displayed.
     * @return the node to be displayed in the UI.
     */
    public static Node from(Item item) {
        // Name
        final Label name = new Label(item.getName().toString());
        name.getStyleClass().add("item-detail-name");
        name.prefWidth(Double.MAX_VALUE);
        name.setWrapText(true);

        // Tags
        final FlowPane tags = new FlowPane(new Label("Tags: "));
        tags.getChildren().addAll(TagsView.from(item.getTagSet()));
        tags.setAlignment(Pos.CENTER_LEFT);
        tags.setHgap(SPACING_UNIT);
        tags.setVgap(SPACING_UNIT);

        // Quantity/unit and price row
        final Label quantityLabel = new Label("Quantity Remaining: ");
        final Label quantityValue = new Label(buildItemQuantityAndUnitStringFrom(item));
        quantityValue.getStyleClass().add("bold");
        final Label priceLabel = new Label("Price: $");
        final Label priceValue = new Label(item.getPrice().toString());
        priceValue.getStyleClass().add("bold");
        final Label costLabel = new Label("Total Cost: $");
        final Label costValue = new Label(String.format("%.2f", item.getItemValue()));
        costValue.getStyleClass().add("bold");

        final Label remarks = new Label(item.getRemarks().toString().isBlank() ? "-" : item.getRemarks().toString());
        remarks.setWrapText(true);

        // Combine everything
        final Separator linedSeparator = new Separator();
        linedSeparator.getStyleClass().add("lined-separator");
        final VBox itemView = new VBox(
                name,
                new HBox(quantityLabel, quantityValue),
                new HBox(priceLabel, priceValue),
                new HBox(costLabel, costValue),
                new Separator(),
                new Label("Bought Date: " + buildBoughtDateStringFrom(item)),
                new Label("Expiry Date: " + buildExpiryDateStringFrom(item)),
                new Separator(),
                new Label("Remarks:"),
                remarks,
                linedSeparator,
                tags);
        itemView.setSpacing(SPACING_UNIT);
        return itemView;
    }

    /**
     * Builds the string representation of the item's quantity attached to its units.
     *
     * @param item the item whose quantity is to be formatted.
     * @return the string representation of the item's quantity and units.
     */
    public static String buildItemQuantityAndUnitStringFrom(Item item) {
        final String unit = String.valueOf(item.getUnit()).isBlank() ? "" : " " + item.getUnit().toString();
        return String.format("%s%s", item.getQuantity(), unit);
    }

    /**
     * Builds the string representation of the item's bought date.
     *
     * @param item the item whose bought date is to be formatted.
     * @return the string representation of the item's bought date.
     */
    public static String buildBoughtDateStringFrom(Item item) {
        return item.getBoughtDate().isNotSet() ? "Not Set" : item.getBoughtDate().toString();
    }

    /**
     * Builds the string representation of the item's expiry date.
     *
     * @param item the item whose expiry date is to be formatted.
     * @return the string representation of the item's expiry date.
     */
    public static String buildExpiryDateStringFrom(Item item) {
        return item.getExpiryDate().isNotSet() ? "Not Set" : item.getExpiryDate().toString();
    }
}
