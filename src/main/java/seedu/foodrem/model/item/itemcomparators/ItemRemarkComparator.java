package seedu.foodrem.model.item.itemcomparators;

import seedu.foodrem.model.item.Item;

/**
 * Comparator comparing between ItemRemarks in lexicographical order.
 */
public class ItemRemarkComparator implements ItemComparator {
    @Override
    public int compare(Item item1, Item item2) {
        return item1.getRemarks().compareTo(item2.getRemarks());
    }

    @Override
    public boolean equals(Object other) {
        return other == this;
    }
}
