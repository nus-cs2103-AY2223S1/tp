package seedu.foodrem.model.item.itemcomparators;

import seedu.foodrem.model.item.Item;

/**
 * Comparator comparing between ItemPrices
 */
public class ItemPriceComparator implements ItemComparator {
    /**
     * Compares two items by price.
     * @param item1 the first item to compare.
     * @param item2 the second item to compare.
     * @return an integer representing the order of the items.
     */
    @Override
    public int compare(Item item1, Item item2) {
        return item1.getPrice().compareTo(item2.getPrice());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        return other == this;
    }
}
