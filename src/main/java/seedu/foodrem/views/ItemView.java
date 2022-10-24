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
import seedu.foodrem.model.tag.Tag;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
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
        final List<Node> tagsList = new ArrayList<>();
        tagsList.add(new Label("Tags: "));
        item.getTagSet().stream().sorted(Comparator.comparing(Tag::getName))
                .forEach(tag -> tagsList.add(buildTagNodeFrom(tag.getName())));
        if (tagsList.size() == 1) {
            tagsList.add(new Label("-"));
        }
        final HBox tags = new HBox(tagsList.toArray(Node[]::new));
        tags.setAlignment(Pos.CENTER_LEFT);
        tags.setSpacing(SPACING_UNIT);

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

        // Combine everything
        final VBox itemView = new VBox(
                row1,
                new Label("$" + item.getPrice().toString()),
                new Separator(),
                new Label("Bought Date: " + buildBoughtDateStringFrom(item)),
                new Label("Expiry Date: " + buildExpiryDateStringFrom(item)),
                new Separator(),
                new Label("Remarks:"),
                new Label(String.valueOf(item.getRemarks()).isBlank() ? "-" : item.getRemarks().toString()));
        itemView.setSpacing(SPACING_UNIT);
        return itemView;
    }

    private static Node buildTagNodeFrom(String tagName) {
        final Label label = new Label(tagName);
        label.getStyleClass().add("item-detail-tag");
        return label;
    }

    public static String buildItemQuantityAndUnitStringFrom(Item item) {
        final String unit = String.valueOf(item.getUnit()).isBlank() ? "" : " " + item.getUnit().toString();
        return String.format("%s%s", item.getQuantity(), unit);
    }

    public static String buildBoughtDateStringFrom(Item item) {
        return String.valueOf(item.getBoughtDate())
                .isBlank() ? "Not Set" : item.getBoughtDate().toString();
    }

    public static String buildExpiryDateStringFrom(Item item) {
        return String.valueOf(item.getExpiryDate())
                .isBlank() ? "Not Set" : item.getExpiryDate().toString();
    }
}
