package seedu.foodrem.views;

import java.text.DecimalFormat;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import seedu.foodrem.model.item.Item;
import seedu.foodrem.viewmodels.Stats;

/**
 * A view of an {@code Item}. This can be displayed.
 *
 * @author Mai Ting Kai
 */
public class StatsView {
    private static final double SPACING_UNIT = 8;
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");
    /**
     * Creates a new detailed view of the given item.
     *
     * @param stats Stats viewmodel that contains FoodRem statistics.
     * @return the node to be displayed in the UI.
     */
    public static Node from(Stats stats) {

        // Statistics header on top left
        final Label statsHeader = new Label("Statistics");
        statsHeader.setWrapText(true);
        statsHeader.getStyleClass().addAll("stats-header", "bold");

        // Section for amount wasted due to expired food
        final Label amountWastedLabel = new Label("Total cost incurred due to food wastage:");
        String amountWasted = DECIMAL_FORMAT.format(stats.getAmountWasted());
        final Label amountWastedValue = new Label("$" + amountWasted);
        amountWastedValue.getStyleClass().add("bold");
        amountWastedLabel.setWrapText(true);

        // Section for top 3 most expensive items
        final Label expensiveItemsLabel = new Label("Your top 3 items with highest value are as follows:");
        expensiveItemsLabel.setWrapText(true);

        // Section for Common Tags
        final Label commonTagsLabel = new Label("Top 3 common tags:");
        final FlowPane commonTagsValue = new FlowPane(TagsView.from(stats.getCommonTags()));
        commonTagsValue.setAlignment(Pos.CENTER_LEFT);
        commonTagsValue.setHgap(SPACING_UNIT);
        commonTagsValue.setVgap(SPACING_UNIT);

        // Combine everything
        final VBox statsView = new VBox(statsHeader,
                amountWastedLabel,
                amountWastedValue,
                commonTagsLabel,
                commonTagsValue,
                expensiveItemsLabel,
                new Separator());
        statsView.getChildren().addAll(buildTopThreeMostExpensiveItemsListFrom(stats));
        statsView.setSpacing(SPACING_UNIT);
        HBox.setHgrow(statsView, Priority.ALWAYS);
        return statsView;
    }

    private static Node[] buildTopThreeMostExpensiveItemsListFrom(Stats stats) {
        List<Item> expensiveItems = stats.getTopThreeMostExpensiveItems();
        Node[] nodes = expensiveItems.stream().map(ItemView::from)
                .peek(node -> node.setStyle("-fx-padding: 20 0 0 0")).toArray(Node[]::new);
        nodes[0].setStyle("-fx-padding: 0");
        return nodes;
    }

}
