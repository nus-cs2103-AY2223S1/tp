package seedu.foodrem.views;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        statsHeader.setTextAlignment(TextAlignment.LEFT);

        final VBox col1 = new VBox(statsHeader);
        col1.setSpacing(SPACING_UNIT);
        final HBox row1 = new HBox(col1);
        HBox.setHgrow(col1, Priority.ALWAYS);
        row1.setSpacing(SPACING_UNIT);

        // Section for amount wasted due to expired food
        final Label amountWasted = new Label("Total cost due to food wastage: " + stats.getAmountWasted());
        final HBox row2 = new HBox(amountWasted);
        new Separator();

        // Section for top 3 most expensive items
        final Label expensiveItems =
                new Label("Your most expensive items are: \n" + buildTopThreeMostExpensiveItemsListFrom(stats));
        final HBox row3 = new HBox(expensiveItems);
        new Separator();

        // Section for top 3 most commonly used tags
        final List<Node> commonTagsList = new ArrayList<>();
        commonTagsList.add(new Label("Your most commonly used tags are: \n"));
        stats.getCommonTags().stream().sorted(Comparator.comparing(Tag::getName))
                .forEach(tag -> commonTagsList.add(buildTagNodeFrom(tag.getName())));
        if (commonTagsList.size() == 1) {
            commonTagsList.add(new Label("-"));
        }
        final HBox row4 = new HBox(commonTagsList.toArray(Node[]::new));
        row4.setAlignment(Pos.CENTER_LEFT);
        row4.setSpacing(SPACING_UNIT);

        // Combine everything
        final VBox itemView = new VBox(row1, row2, row3, row4);
        itemView.setSpacing(SPACING_UNIT);
        return itemView;
    }

    private static String buildTopThreeMostExpensiveItemsListFrom(Stats stats) {
        List<Item> expensiveItems = stats.getTopThreeMostExpensiveItems();
        return IntStream.range(1, expensiveItems.size() + 1)
                .mapToObj(i -> expensiveItems.get(i - 1))
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
    }

    private static Node buildTagNodeFrom(String tagName) {
        final Label label = new Label(tagName);
        label.getStyleClass().add("item-detail-tag");
        return label;
    }

}
