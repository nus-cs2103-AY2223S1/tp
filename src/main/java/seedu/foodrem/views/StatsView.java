package seedu.foodrem.views;

import java.util.List;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.Region;
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
        final Label statsHeader = new Label("FoodRem Statistics");

        // Section for amount wasted due to expired food
        final Label amountWasted = new Label("Total cost due to food wastage: " + stats.getAmountWasted());
        amountWasted.setWrapText(true);
        new Separator();

        // Section for top 3 most expensive items
        final Label expensiveItemsLabel =
                new Label("Your most expensive items are: \n");
        expensiveItemsLabel.setLineSpacing(SPACING_UNIT);
        final Region spacer = new Region();
        final VBox row3 = new VBox(expensiveItemsLabel,
                spacer,
                new VBox(buildTopThreeMostExpensiveItemsListFrom(stats)));
        new Separator();

        // Combine everything
        final VBox itemView = new VBox(statsHeader, amountWasted, row3);
        itemView.setSpacing(SPACING_UNIT);
        return itemView;
    }

    private static Node[] buildTopThreeMostExpensiveItemsListFrom(Stats stats) {
        List<Item> expensiveItems = stats.getTopThreeMostExpensiveItems();
        return expensiveItems.stream()
                .map(ItemView::from)
                .toArray(Node[]::new);
    }

}
