package seedu.foodrem.model.item.itemcomparators;

import seedu.foodrem.model.item.Item;

/**
 * Comparator comparing between ItemBoughtDates
 */
public class ItemBoughtDateComparator implements ItemComparator {
    @Override
    public int compare(Item item1, Item item2) {
        return item1.getBoughtDate().compareTo(item2.getBoughtDate());
    }

    @Override
    public boolean equals(Object other) {
        return other == this;
    }
}
