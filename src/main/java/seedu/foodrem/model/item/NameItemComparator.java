package seedu.foodrem.model.item;

import seedu.foodrem.model.FoodRemItemComparator;

/**
 * Comparator comparing between ItemNames in lexicographical order.
 */
public class NameItemComparator implements FoodRemItemComparator {
    @Override
    public int compare(Item item1, Item item2) {
        return item1.getName().compareTo(item2.getName());
    }

    @Override
    public boolean equals(Object other) {
        return other == this;
    }
}
