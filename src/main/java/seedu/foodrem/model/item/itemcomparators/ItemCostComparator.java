package seedu.foodrem.model.item.itemcomparators;

import seedu.foodrem.model.item.Item;

/**
 * Comparator comparing between ItemPrices
 */
public class ItemCostComparator implements ItemComparator {
    @Override
    public int compare(Item item1, Item item2) {
        return Double.compare(item1.getItemCost(), item2.getItemCost());
    }
}
