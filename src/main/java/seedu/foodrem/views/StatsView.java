package seedu.foodrem.views;

import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.FlowPane;
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

    /**
     * Creates a new detailed view of the given item.
     *
     * @param stats Stats viewmodel that contains FoodRem statistics.
     * @return the node to be displayed in the UI.
     */
    public static Node from(Stats stats) {

        // Statistics header on top left
        final Label statsHeader = new Label("Statistics");
        statsHeader.getStyleClass().add("stats-header");

        // Section for amount wasted due to expired food
        final Label amountWasted = new Label("Total cost incurred due to food wastage: $" + stats.getAmountWasted());
        amountWasted.setWrapText(true);

        // Section for top 3 most expensive items
        final Label expensiveItemsLabel =
                new Label("Your top 3 most expensive items are: \n");
        final Separator linedSeparator = new Separator();
        linedSeparator.getStyleClass().add("lined-separator");
        expensiveItemsLabel.setLineSpacing(SPACING_UNIT);
        final VBox expensiveItems = new VBox(expensiveItemsLabel,
                linedSeparator,
                new VBox(buildTopThreeMostExpensiveItemsListFrom(stats)));
        new Separator();

        // Section for Common Tags
        final FlowPane commonTags = new FlowPane(new Label("Top 3 common tags: "));
        commonTags.getChildren().addAll(TagsView.from(stats.getCommonTags()));
        commonTags.setAlignment(Pos.CENTER_LEFT);
        commonTags.setHgap(SPACING_UNIT);
        commonTags.setVgap(SPACING_UNIT);

        // Combine everything
        final VBox statsView = new VBox(statsHeader, new Separator(), amountWasted, commonTags, expensiveItems);

        return statsView;
    }

    private static Node[] buildTopThreeMostExpensiveItemsListFrom(Stats stats) {
        List<Item> expensiveItems = stats.getTopThreeMostExpensiveItems();
        return expensiveItems.stream()
                .map(ItemView::from)
                .toArray(Node[]::new);
    }

}
