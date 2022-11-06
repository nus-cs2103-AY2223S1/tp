package seedu.foodrem.model.item.itemcomparators;

import seedu.foodrem.model.item.Item;

/**
 * Comparator comparing between ItemRemarks in lexicographical order.
 */
public class ItemRemarkComparator implements ItemComparator {
    /**
     * Compares two items by remark.
     * @param item1 the first item to compare.
     * @param item2 the second item to compare.
     * @return an integer representing the order of the items.
     */
    @Override
    public int compare(Item item1, Item item2) {
        return item1.getRemarks().compareTo(item2.getRemarks());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        return other == this;
    }
}
