package seedu.foodrem.model.item.itemcomparators;

import java.util.Comparator;

import seedu.foodrem.model.item.Item;

/**
 * A common Comparator interface for comparators used in the Sort
 * command to inherit from. Allows for sorting of Items.
 */
public interface ItemComparator extends Comparator<Item> {
    boolean equals(Object other);
}
