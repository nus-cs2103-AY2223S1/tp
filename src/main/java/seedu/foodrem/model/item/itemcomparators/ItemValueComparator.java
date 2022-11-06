package seedu.foodrem.model.item.itemcomparators;

import seedu.foodrem.model.item.Item;

/**
 * Comparator comparing between ItemValues
 */
public class ItemValueComparator implements ItemComparator {
    @Override
    public int compare(Item item1, Item item2) {
        return Double.compare(item1.getItemValue(), item2.getItemValue());
    }
}
