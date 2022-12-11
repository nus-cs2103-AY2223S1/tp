package seedu.foodrem.viewmodels;

import java.util.List;
import java.util.Objects;

import seedu.foodrem.model.item.Item;
import seedu.foodrem.model.tag.Tag;

/**
 * A view model for generating a view with related FoodRem statistics.
 *
 * @author Mai Ting Kai
 */
public class Stats {
    private final double amountWasted;
    private final List<Item> expensiveItems;
    private final List<Tag> commonTags;

    /**
     * Creates a view model containing the relevant FoodRem statistics to display to the user.
     *
     * @param amountWasted   Amount wasted due to expired food.
     * @param expensiveItems List of top 3 most expensive items.
     * @param commonTags     List of top 3 most common tags.
     */
    public Stats(double amountWasted, List<Item> expensiveItems, List<Tag> commonTags) {
        this.amountWasted = amountWasted;
        this.expensiveItems = expensiveItems;
        this.commonTags = commonTags;
    }

    public List<Item> getTopThreeMostExpensiveItems() {
        return expensiveItems;
    }

    public double getAmountWasted() {
        return amountWasted;
    }

    public List<Tag> getCommonTags() {
        return commonTags;
    }

    // TODO: Fixme
    @Override
    public boolean equals(Object other) {
        return this == other
                || (other instanceof Stats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amountWasted, expensiveItems);
    }
}
