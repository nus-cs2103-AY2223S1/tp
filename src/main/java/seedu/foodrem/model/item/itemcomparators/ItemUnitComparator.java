package seedu.foodrem.model.item.itemcomparators;

import seedu.foodrem.model.item.Item;

/**
 * Comparator comparing between ItemUnits
 */
public class ItemUnitComparator implements ItemComparator {
    @Override
    public int compare(Item item1, Item item2) {
        return item1.getUnit().compareTo(item2.getUnit());
    }

    @Override
    public boolean equals(Object other) {
        return other == this;
    }
}
