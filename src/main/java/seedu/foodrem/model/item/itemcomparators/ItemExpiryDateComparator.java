package seedu.foodrem.model.item.itemcomparators;

import seedu.foodrem.model.item.Item;

/**
 * Comparator comparing between ItemExpiryDates
 */
public class ItemExpiryDateComparator implements ItemComparator {
    @Override
    public int compare(Item item1, Item item2) {
        return item1.getExpiryDate().compareTo(item2.getExpiryDate());
    }

    @Override
    public boolean equals(Object other) {
        return other == this;
    }
}
