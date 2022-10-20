package seedu.foodrem.model.item.itemcomparators;

import seedu.foodrem.model.item.Item;

/**
 * Comparator comparing between ItemPrices
 */
public class ItemPriceComparator implements ItemComparator {
    @Override
    public int compare(Item item1, Item item2) {
        return item1.getPrice().compareTo(item2.getPrice());
    }

    @Override
    public boolean equals(Object other) {
        return other == this;
    }
}
