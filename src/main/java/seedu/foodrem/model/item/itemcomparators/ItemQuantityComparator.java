package seedu.foodrem.model.item.itemcomparators;

import seedu.foodrem.model.item.Item;

/**
 * Comparator comparing between ItemQuantities
 */
public class ItemQuantityComparator implements ItemComparator {
    @Override
    public int compare(Item item1, Item item2) {
        return item1.getQuantity().compareTo(item2.getQuantity());
    }

    @Override
    public boolean equals(Object other) {
        return other == this;
    }
}
