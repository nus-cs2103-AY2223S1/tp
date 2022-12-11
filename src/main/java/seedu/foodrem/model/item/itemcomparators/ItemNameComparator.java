package seedu.foodrem.model.item.itemcomparators;

import seedu.foodrem.model.item.Item;

/**
 * Comparator comparing between ItemNames in lexicographical order.
 */
public class ItemNameComparator implements ItemComparator {
    @Override
    public int compare(Item item1, Item item2) {
        return item1.getName().compareTo(item2.getName());
    }

    @Override
    public boolean equals(Object other) {
        return other == this;
    }
}
