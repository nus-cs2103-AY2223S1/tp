package seedu.foodrem.model.item.itemcomparators;

import seedu.foodrem.model.item.Item;

/**
 * Comparator comparing between ItemValues
 */
public class ItemValueComparator implements ItemComparator {
    /**
     * Compares two items by value.
     * @param item1 the first item to compare.
     * @param item2 the second item to compare.
     * @return an integer representing the order of the items.
     */
    @Override
    public int compare(Item item1, Item item2) {
        return Double.compare(item1.getItemValue(), item2.getItemValue());
    }
}
